package tgits.concurrent.counter.examples.counter;

public class SynchronizedLongCounter implements LongCounter {

    private long counter;
    private final String name;

    public SynchronizedLongCounter(String name) {
        this.counter = 0;
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public synchronized void increment() {
        counter ++;
    }

    @Override
    public synchronized long value() {
        return counter;
    }
}
