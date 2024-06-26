package tgits.concurrent.counter.examples.worker;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class BadLetterCounterWorker implements Runnable {
    private final Map<String, Long> frequencies;
    private final List<String> strings;

    public BadLetterCounterWorker(Map<String, Long> frequencies, List<String> strings) {
        this.frequencies = frequencies;
        this.strings = strings;
    }

    @Override
    public void run() {
        for (String s : strings) {
            frequencies.put(s, frequencies.computeIfAbsent(s, k -> 0L) + 1L);
            try {
                Thread.sleep(Duration.ofMillis(100));
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
}
