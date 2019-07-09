package com.splitstupid;

import java.util.HashMap;
import java.util.Map;

public class Splitter {

    public void split(final Map<String, Double> expenses) {
        System.out.println("----------------------------------------------");
        System.out.println(expenses);

        final int numberOfParticipants = expenses.size();
        System.out.println("Number of participants: " + numberOfParticipants);
        final double totalExpenses = expenses.values().stream().reduce(0d, (a,b) -> a+b);
        System.out.println("Total expenses: " + totalExpenses);
        final double average = totalExpenses/numberOfParticipants;
        System.out.println("Average expense: " + average);
        System.out.println("----------------------------------------------");

        final Map<String, Double> depts = new HashMap<>();
        for(Map.Entry<String, Double> expense: expenses.entrySet()) {
            depts.put(expense.getKey(), expense.getValue() - average);
        }

        while(isDebt(depts)) {
            Map.Entry<String, Double> min = null;
            for (Map.Entry<String, Double> entry : depts.entrySet()) {
                if (min == null || min.getValue() > entry.getValue()) {
                    min = entry;
                }
            }

            Map.Entry<String, Double> max = null;
            for (Map.Entry<String, Double> entry : depts.entrySet()) {
                if (max == null || max.getValue() < entry.getValue()) {
                    max = entry;
                }
            }

            if(max.getValue()+min.getValue() >0) {
                System.out.println(min.getKey() + " pays to " + max.getKey() + " " +  Math.abs(min.getValue()));
                max.setValue(max.getValue()+min.getValue());
                min.setValue(0d);
            } else {
                System.out.println(min.getKey() + " pays to " + max.getKey() + " " +  max.getValue());
                min.setValue(max.getValue()+min.getValue());
                max.setValue(0d);
            }
        }
    }

    private boolean isDebt(Map<String, Double> depts) {
        return depts.values().stream()
            .anyMatch(value -> value < 0);
    }
}
