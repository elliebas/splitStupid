package com.splitstupid;

import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Responsible for splitting the given expenses.
 */
@Service
public class Splitter {

    public String split(final Set<Expense> expenses) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        printWriter.println("----------------------------------------------");
        printWriter.println(expenses);

        final int numberOfParticipants = expenses.size();
        printWriter.println("Number of participants: " + numberOfParticipants);

        double totalExpenses = getTotalExpenses(expenses);
        printWriter.println("Total expenses: " + totalExpenses);

        final double average = totalExpenses / numberOfParticipants;
        printWriter.println("Average expense: " + average);
        printWriter.println("----------------------------------------------");

        final Map<String, Double> depts = getDepts(expenses, average);

        while (isDebt(depts)) {
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

            if (max.getValue() + min.getValue() > 0) {
                printWriter.println(min.getKey() + " pays to " + max.getKey() + " " + Math.abs(min.getValue()));
                max.setValue(max.getValue() + min.getValue());
                min.setValue(0d);
            } else {
                printWriter.println(min.getKey() + " pays to " + max.getKey() + " " + max.getValue());
                min.setValue(max.getValue() + min.getValue());
                max.setValue(0d);
            }
        }
        return stringWriter.toString();
    }

    private double getTotalExpenses(final Set<Expense> expenses) {
        double totalExpenses = 0;
        for (Expense expense : expenses) {
            totalExpenses += expense.getParticipation();
        }
        return totalExpenses;
    }

    private Map<String, Double> getDepts(final Set<Expense> expenses, final double average) {
        final Map<String, Double> depts = new HashMap<>();
        for (Expense expense : expenses) {
            depts.put(expense.getParticipant(), expense.getParticipation() - average);
        }
        return depts;
    }

    private boolean isDebt(Map<String, Double> depts) {
        return depts.values().stream()
                   .anyMatch(value -> value < 0);
    }
}
