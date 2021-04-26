package com.rmauge.scdf.demo.signalprocessor;

import com.rmauge.scdf.demo.lib.models.Signal;
import com.rmauge.scdf.demo.lib.models.UserSignalDetails;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@EnableBinding(Processor.class)
public class SignalProcessor {

    private static int[] customers = {1001, 1002, 1003};

    private static Map<Integer, String> custMap = new HashMap<>();

    static {
        custMap.put(1001, "crmCust1");
        custMap.put(1002, "crmCust2");
        custMap.put(1003, "crmCust3");
    }

    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public UserSignalDetails processSignal(Signal signal) {
        UserSignalDetails userSignalDetails = new UserSignalDetails();
        userSignalDetails.setUserId(signal.getCustId());
        userSignalDetails.setSalesForceId(custMap.get(signal.getCustId()));
        userSignalDetails.setScore(calScore(signal.getCustId(), signal.getValue()));

        return userSignalDetails;
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