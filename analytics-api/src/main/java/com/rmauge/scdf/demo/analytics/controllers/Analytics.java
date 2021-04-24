package com.rmauge.scdf.demo.analytics.controllers;

import com.rmauge.scdf.demo.lib.models.Signal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/api")
public class Analytics {
    private static String[] values = {"TERM", "PERM", "EASY"};
    private static int[] customers = {1001, 1002, 1003};
    private Random random = new Random();

    @GetMapping("/signal")
    public Signal getLatestSignal() {
        return new Signal("c_product",
                values[random.nextInt(values.length)],
                customers[random.nextInt(customers.length)]);
    }
}