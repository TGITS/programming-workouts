package tgits.opentracingsimplepoc.hello;

import com.google.common.collect.ImmutableMap;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class HelloController {

    @Autowired
    private Tracer tracer;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/hello")
    public Hello greet(@RequestParam(value = "name", defaultValue = "is it me you're looking for ?") String name) {
        Span span = tracer.buildSpan("Get /hello").asChildOf(tracer.activeSpan()).start();
        span.setTag("greeting-to-name", name);
        Hello hello = new Hello(counter.incrementAndGet(), String.format(template, name));
        span.log(ImmutableMap.of("event", "string-format", "value", hello.toString()));
        span.finish();
        return hello;
    }
}
