package io.github.unhurried.example.backend.quarkus.resource.bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

public class TodoBean {
    @Null
    public String id;

    @NotNull
    @Pattern(regexp = "^\\p{Print}{1,32}$")
    public String title;

    @NotNull
    public Category category;

    @Pattern(regexp = "^\\p{Print}{1,256}$")
    public String content;

    public enum Category {
        one, two, three
    }
}
