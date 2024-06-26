package tgits.concurrent.counter.examples.counter;

public class NotSynchronizedLongCounter implements LongCounter {

    private long counter;
    private final String name;

    public NotSynchronizedLongCounter(String name) {
        this.counter = 0;
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public void increment() {
        counter ++;
    }

    @Override
    public long value() {
        return counter;
    }
}
