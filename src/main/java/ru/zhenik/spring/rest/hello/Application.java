package ru.zhenik.spring.rest.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.uber.jaeger.Configuration;
import com.uber.jaeger.samplers.ProbabilisticSampler;


/**
 * hello
 * NIK on 09/10/2017
 */
@SpringBootApplication
public class Application {

    @Bean
    public io.opentracing.Tracer tracer() {
        return new Configuration("spring-boot", new Configuration.SamplerConfiguration(ProbabilisticSampler.TYPE, 1),
                new Configuration.ReporterConfiguration())
                .getTracer();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}