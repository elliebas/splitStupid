package com.splitstupid;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Holds info about the participant in total expenses and its participation.
 */
public class Expense implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Name of the participant", required = true)
    private final String participant;
    @ApiModelProperty(value = "Sum spent by the participant", required = true)
    private final Double participation;

    public Expense(final String participant, final Double participation) {
        this.participant = participant;
        this.participation = participation;
    }

    public String getParticipant() {
        return participant;
    }

    public Double getParticipation() {
        return participation;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Expense expense = (Expense) o;
        return participant.equals(expense.participant) &&
                   participation.equals(expense.participation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(participant, participation);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Expense.class.getSimpleName() + "[", "]")
                   .add(participant + " : " + participation)
                   .toString();
    }
}
