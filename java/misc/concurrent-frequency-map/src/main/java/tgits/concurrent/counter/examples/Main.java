package tgits.concurrent.counter.examples;

import org.apache.commons.lang3.time.StopWatch;
import tgits.concurrent.counter.examples.counter.*;
import tgits.concurrent.counter.examples.worker.AdderWorker;
import tgits.concurrent.counter.examples.worker.BadLetterCounterWorker;
import tgits.concurrent.counter.examples.worker.LetterCounterWorker;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

public class Main {

    public static void main(String... args) throws InterruptedException {
        System.out.println("Code examples with updated counter in concurrent environment");
        LongCounter notSynchronizedLongCounter = new NotSynchronizedLongCounter("notSynchronizedLongCounter");
        runFirstExperiment(notSynchronizedLongCounter);

        LongCounter synchronizedLongCounter = new SynchronizedLongCounter("synchronizedLongCounter");
        runFirstExperiment(synchronizedLongCounter);

        LongCounter longAdderLongCounter = new LongAdderLongCounter("longAdderLongCounter");
        runFirstExperiment(longAdderLongCounter);

        LongCounter atomicLongLongCounter = new AtomicLongLongCounter("atomicLongLongCounter");
        runFirstExperiment(atomicLongLongCounter);

        //final List<String> strings = List.of("A", "B", "C", "A", "B", "A", "D", "E", "F", "A", "G", "H", "I", "J", "K", "L");
        String letters = "ABCDEFGHIJKMNLOPQRSTUVWXYZABCDEFGHIJKMNLOPQRSTUVWXYZABCDEFGHIJKMNLOPQRSTUVWXYZABCABCABCAAA";
        final List<String> strings = Arrays.asList(letters.split(""));
        Map<String, Long> simpleHashmapFrequencies = new HashMap<>();
        runSecondExperiment(simpleHashmapFrequencies, strings);

        Map<String, LongAdder> frequencies = new HashMap<>();
        runThirdExperiment(frequencies, strings);

        Map<String, LongAdder> concurrentFrequencies = new ConcurrentHashMap<>();
        runThirdExperiment(concurrentFrequencies, strings);
    }

    public static void runFirstExperiment(LongCounter longCounter) throws InterruptedException {
        System.out.println(longCounter.name() + " initial value : " + longCounter.value());
        Thread t1 = new Thread(new AdderWorker(longCounter));
        Thread t2 = new Thread(new AdderWorker(longCounter));
        Thread t3 = new Thread(new AdderWorker(longCounter));
        StopWatch watch = new StopWatch();
        watch.start();
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        watch.stop();
        System.out.println(longCounter.name() + " value after counting with workers ends : " + longCounter.value());
        System.out.println("Time Elapsed: " + watch.getTime());
        System.out.println("\n---------------------\n");
    }

    public static void runSecondExperiment(Map<String, Long> frequencies, List<String> strings) throws InterruptedException {
        Thread t1 = new Thread(new BadLetterCounterWorker(frequencies, strings));
        Thread t2 = new Thread(new BadLetterCounterWorker(frequencies, strings));
        Thread t3 = new Thread(new BadLetterCounterWorker(frequencies, strings));
        StopWatch watch = new StopWatch();
        watch.start();
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        watch.stop();
        for (String key : frequencies.keySet()) {
            System.out.println(key + ": " + frequencies.get(key));
        }
        System.out.println("Time Elapsed: " + watch.getTime());
        System.out.println("\n---------------------\n");

    }

    public static void runThirdExperiment(Map<String, LongAdder> frequencies, List<String> strings) throws InterruptedException {
        Thread t1 = new Thread(new LetterCounterWorker(frequencies, strings));
        Thread t2 = new Thread(new LetterCounterWorker(frequencies, strings));
        Thread t3 = new Thread(new LetterCounterWorker(frequencies, strings));
        StopWatch watch = new StopWatch();
        watch.start();
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        watch.stop();
        for (String key : frequencies.keySet()) {
            System.out.println(key + ": " + frequencies.get(key));
        }
        System.out.println("Time Elapsed: " + watch.getTime());
        System.out.println("\n---------------------\n");

    }
}