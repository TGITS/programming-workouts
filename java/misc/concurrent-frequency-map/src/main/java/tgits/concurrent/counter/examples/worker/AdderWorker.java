package tgits.concurrent.counter.examples.worker;

import tgits.concurrent.counter.examples.counter.LongCounter;

import java.time.Duration;

public class AdderWorker implements Runnable{

    private final LongCounter counter;

    public AdderWorker(LongCounter counter){
        this.counter = counter;
    }

    @Override
    public void run(){
        for(int i=0; i < 100; i++) {
            counter.increment();
            try {
                Thread.sleep(Duration.ofMillis(10));
            }
            catch(InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
}
