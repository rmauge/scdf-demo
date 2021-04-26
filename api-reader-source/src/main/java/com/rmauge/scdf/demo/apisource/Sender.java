package com.rmauge.scdf.demo.apisource;

import com.rmauge.scdf.demo.lib.models.Signal;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

@EnableScheduling
@EnableBinding(Source.class)
public class Sender {

    private final Source source;
    private final RestTemplate restTemplate;
    private final String polledURL;
    private Log log = LogFactory.getLog(Sender.class);

    public Sender(final Source source, final RestTemplateBuilder restTemplateBuilder, @Value("${polledURL}") final String polledURI) {
        this.source = source;
        this.restTemplate = restTemplateBuilder.build();
        this.polledURL = polledURI;
        log.info("polledURL:" + polledURI);
    }

    @Scheduled(fixedDelay = 1000)
    public void sendEvents() {
        Signal signal = restTemplate.getForObject(polledURL, Signal.class);

        this.source.output().send(MessageBuilder.withPayload(signal).build());
    }
}
