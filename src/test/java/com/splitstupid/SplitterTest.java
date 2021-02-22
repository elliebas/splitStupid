package com.splitstupid;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class SplitterTest {

    public Splitter splitter;

    @BeforeMethod
    public void before() {
        splitter = new Splitter();
    }

    @Test
    public void split() {
        final Set<Expense> expenses = new HashSet<>();
        expenses.add(new Expense("John", 500d));
        expenses.add(new Expense("Jack", 700d));

        final String result = splitter.split(expenses);

        System.out.println(result);
        assertThat(result.contains("Total expenses: 1200.0")).isTrue();
        assertThat(result.contains("John pays to Jack 100.0")).isTrue();
    }

    @Test
    public void split1() {
        final Set<Expense> expenses = new HashSet<>();
        expenses.add(new Expense("John", 500d));
        expenses.add(new Expense("Jack", 700d));
        expenses.add(new Expense("Jasper", 0d));

        final String result = splitter.split(expenses);

        System.out.println(result);
        assertThat(result.contains("Total expenses: 1200.0")).isTrue();
        assertThat(result.contains("Jasper pays to Jack 300.0")).isTrue();
        assertThat(result.contains("Jasper pays to John 100.0")).isTrue();
    }
}