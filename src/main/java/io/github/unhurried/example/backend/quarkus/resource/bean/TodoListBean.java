package io.github.unhurried.example.backend.quarkus.resource.bean;

import java.util.List;

import lombok.Data;

@Data
public class TodoListBean {
    private Long total;
    private List<TodoBean> items;
}
