package io.github.unhurried.example.backend.quarkus.resource.bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class TodoBean {
    @Null
    public String id;

    @NotNull
    public String title;

    @NotNull
    public Category category;

    public String content;

    public enum Category {
        one, two, three
    }
}
