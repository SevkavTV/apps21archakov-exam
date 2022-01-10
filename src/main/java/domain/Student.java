package domain;

import json.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    private final List<Exam> exams;

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);
        this.exams = new ArrayList<>();
        for (Tuple<String, Integer> exam: exams) {
            this.exams.add(new Exam(exam.key, exam.value));
        }
    }

    @Override
    public JsonObject toJsonObject() {
        JsonObject baseJsonObj = super.toJsonObject();
        JsonPair examsJson = examsToJson();
        baseJsonObj.add(examsJson);
        return baseJsonObj;
    }

    private JsonPair examsToJson() {
        List<JsonObject> examsJsonObj = new ArrayList<>();
        for (Exam exam: exams) {
            examsJsonObj.add(exam.toJsonObject());
        }
        JsonArray examsJsonArr = new JsonArray(examsJsonObj.toArray(new JsonObject[0]));
        return new JsonPair("exams", examsJsonArr);
    }
}