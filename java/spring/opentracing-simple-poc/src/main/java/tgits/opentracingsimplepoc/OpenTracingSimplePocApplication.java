package tgits.opentracingsimplepoc;

import io.opentracing.Tracer;
import io.opentracing.util.GlobalTracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class OpenTracingSimplePocApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenTracingSimplePocApplication.class, args);
    }
}

@Component
class StartupApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(StartupApplicationListener.class);

    @Autowired
    private Tracer tracer;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        boolean registerGlobalTracer = GlobalTracer.registerIfAbsent(tracer);
        LOGGER.info("GlobalTracer registered : {}", registerGlobalTracer);
    }

}