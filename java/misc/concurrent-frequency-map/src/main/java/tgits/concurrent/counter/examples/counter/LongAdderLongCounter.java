package tgits.concurrent.counter.examples.counter;

import java.util.concurrent.atomic.LongAdder;

public class LongAdderLongCounter implements LongCounter {

    private final LongAdder counter;
    private final String name;

    public LongAdderLongCounter(String name) {
        this.counter = new LongAdder();
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public void increment() {
        this.counter.increment();
    }

    @Override
    public long value() {
        return counter.longValue();
    }
}
