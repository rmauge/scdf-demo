package com.rmauge.scdf.demo.signalprocessor;

import com.rmauge.scdf.demo.lib.models.Signal;
import com.rmauge.scdf.demo.lib.models.UserSignalDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;

@Configuration
public class SignalProcessor {

    private static Map<Integer, String> custMap = new HashMap<>();

    static {
        custMap.put(1001, "crmCust1");
        custMap.put(1002, "crmCust2");
        custMap.put(1003, "crmCust3");
    }

    @Bean
    public Function<Signal, UserSignalDetails> processSignal() {
        return signal -> {
            UserSignalDetails userSignalDetails = new UserSignalDetails();
            userSignalDetails.setUserId(signal.getCustId());
            userSignalDetails.setSalesForceId(custMap.get(signal.getCustId()));
            userSignalDetails.setScore(calScore(signal.getCustId(), signal.getValue()));

            return userSignalDetails;
        };
    }

    /**
     * Fictional Score calculator
     * @param custId
     * @param term
     * @return
     */
    private int calScore(final int custId, String term) {
        return new Random(custId).nextInt(100) +
                new Random(term.length()).nextInt(50);
    }
}