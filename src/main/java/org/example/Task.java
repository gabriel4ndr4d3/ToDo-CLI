package org.example;

import com.google.gson.annotations.SerializedName;

public class Task {

    @SerializedName("title")
    private final String title;

    @SerializedName("description")
    private final String description;

    @SerializedName("done")
    private boolean done;

    public Task(
            String title,
            String description
    ) {

        if (title.isBlank()) {
            throw new IllegalArgumentException("This title is invalid");
        }

        this.title = title;
        this.description = description;
    }

    public void done() {
        this.done = true;
    }

    public boolean getDone() {

        return done;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {

        String status;

        if (this.done) {
            status = "[x]";
        } else {
            status = "[ ]";
        }

        return "%s %s".formatted(status, title);
    }
}
