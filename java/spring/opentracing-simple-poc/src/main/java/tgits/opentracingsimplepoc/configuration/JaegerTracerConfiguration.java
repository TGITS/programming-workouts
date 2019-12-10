package tgits.opentracingsimplepoc.configuration;

import io.jaegertracing.internal.samplers.ConstSampler;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JaegerTracerConfiguration {

    @Value("${service.name.for.tracing}")
    private String serviceName;

    @Bean
    public io.jaegertracing.Configuration.SamplerConfiguration samplerConfiguration() {
        return io.jaegertracing.Configuration.SamplerConfiguration.fromEnv()
                .withType(ConstSampler.TYPE)
                .withParam(1);
    }

    @Bean
    public io.jaegertracing.Configuration.ReporterConfiguration reporterConfiguration() {
        return io.jaegertracing.Configuration.ReporterConfiguration.fromEnv()
                .withLogSpans(true);
    }

    @Bean
    public io.jaegertracing.Configuration tracerConfiguration(String serviceName) {
        return new io.jaegertracing.Configuration(serviceName)
                .withSampler(samplerConfiguration())
                .withReporter(reporterConfiguration());
    }

    @Bean
    public Tracer tracer() {
        return tracerConfiguration(serviceName).getTracer();
    }

}
