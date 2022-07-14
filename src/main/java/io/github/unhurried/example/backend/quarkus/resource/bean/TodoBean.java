package io.github.unhurried.example.backend.quarkus.resource.bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

import io.github.unhurried.example.backend.quarkus.entity.TodoEntity.Category;
import io.github.unhurried.example.backend.quarkus.validator.EnumString;

public class TodoBean {
    @Null
    public String id;

    @NotNull
    @Pattern(regexp = "^\\p{Print}{1,32}$")
    public String title;

    @NotNull
    @EnumString(enumeration = Category.class)
    public String category;

    @Pattern(regexp = "^\\p{Print}{1,256}$")
    public String content;
}
