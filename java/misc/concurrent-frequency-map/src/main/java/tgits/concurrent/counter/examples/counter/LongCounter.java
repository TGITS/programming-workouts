package tgits.concurrent.counter.examples.counter;

public interface LongCounter {

    String name();
    void increment();
    long value();
}
