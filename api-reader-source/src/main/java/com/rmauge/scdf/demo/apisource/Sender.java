package com.rmauge.scdf.demo.apisource;

import com.rmauge.scdf.demo.lib.models.Signal;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.function.Supplier;

@Configuration
public class Sender {
    private final Log log = LogFactory.getLog(Sender.class);

    @Bean
    public Supplier<Signal> signalSource(final RestTemplateBuilder restTemplateBuilder, @Value("${polledURL}") final String polledURL) {
        final RestTemplate restTemplate = restTemplateBuilder.build();
        return () -> {
            log.info("Sending signal");
            final Signal signal = restTemplate.getForObject(polledURL,
                    Signal.class);
            return signal;
        };
    }
}
