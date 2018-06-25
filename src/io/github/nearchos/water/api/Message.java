package io.github.nearchos.water.api;

import io.github.nearchos.water.data.DayStatistics;

import java.io.Serializable;

public class Message implements Serializable {

    private Status status;
    private Serializable payload;

    private Message(final Status status, final Serializable payload) {
        this.status = status;
        this.payload = payload;
    }

    public Status getStatus() {
        return status;
    }

    public Serializable getPayload() {
        return payload;
    }

    static Message getErrorMessage(final String message) {
        return new Message(Status.ERROR, message);
    }

    static Message getDayStatisticsMessage(final DayStatistics dayStatistics) {
        return new Message(Status.OK, dayStatistics);
    }
}
