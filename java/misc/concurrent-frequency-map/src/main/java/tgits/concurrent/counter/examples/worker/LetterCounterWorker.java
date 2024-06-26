package tgits.concurrent.counter.examples.worker;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.LongAdder;

public class LetterCounterWorker implements Runnable {

    private final Map<String, LongAdder> frequencies;
    private final List<String> strings;

    public LetterCounterWorker(Map<String, LongAdder> frequencies, List<String> strings) {
        this.frequencies = frequencies;
        this.strings = strings;
    }

    @Override
    public void run() {
        for (String s : strings) {
            frequencies.computeIfAbsent(s, k -> new LongAdder()).increment();
            try {
                Thread.sleep(Duration.ofMillis(100));
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
}
