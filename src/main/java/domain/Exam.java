package domain;

import json.*;

public class Exam implements Jsonable {
    private final String course;
    private final int mark;
    private final boolean passed;

    public Exam(String course, int mark) {
        this.course = course;
        this.mark = mark;
        if (mark > 2) {
            this.passed = true;
        } else {
            this.passed = false;
        }
    }

    public String getCourse() {
        return course;
    }

    public int getMark() {
        return mark;
    }

    public boolean isPassed() {
        return passed;
    }

    @Override
    public JsonObject toJsonObject() {
        return new JsonObject(
                new JsonPair("course", new JsonString(getCourse())),
                new JsonPair("mark", new JsonNumber(getMark())),
                new JsonPair("passed", new JsonBoolean(isPassed()))
        );
    }
}
