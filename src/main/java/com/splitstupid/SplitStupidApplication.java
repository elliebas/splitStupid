package com.splitstupid;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.SpringApplication;

public class SplitStupidApplication {

    public static void main(String[] args) {
        SpringApplication.run(SplitStupidApplication.class, args);

        final Splitter splitter = new Splitter();
        splitter.split(buildExpenses());
    }

    private static Map<String, Double> buildExpenses() {
        final Map<String, Double> expenses = new HashMap<>();
        expenses.put("Stanislav", 1000d);
        expenses.put("Miroslav", 700d);
        expenses.put("Dina", 500d);
        expenses.put("Anastasia", 400d);
        expenses.put("Elena",300d);

        return expenses;
    }
}
