package ru.zhenik.spring.rest.hello.two;

import com.uber.jaeger.Configuration;

import io.opentracing.Tracer;
import com.uber.jaeger.samplers.ProbabilisticSampler;
import io.opentracing.util.GlobalTracer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ApplicationTwo {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

    @Bean
    public Tracer jaegerTracer() {
        Tracer tracer = new Configuration("Home", new Configuration.SamplerConfiguration(ProbabilisticSampler.TYPE, 1),
                new Configuration.ReporterConfiguration())
                .getTracer();
        if (GlobalTracer.isRegistered()){ GlobalTracer.register(tracer); }
        return tracer;
    }

    public static void main(String[] args) {
        SpringApplication.run(ApplicationTwo.class, args);
    }
}