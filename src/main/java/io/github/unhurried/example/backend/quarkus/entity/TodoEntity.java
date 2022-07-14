package io.github.unhurried.example.backend.quarkus.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;

@Entity
// Set @Table(name = "todo") when you need to specify the table name.
@Table(indexes = @Index(columnList = "userId"))
public class TodoEntity extends PanacheEntityBase {
    @Id
    @GeneratedValue
    public UUID id;
    public String title;
    public Category category;
    public String content;
    public String userId;

    public enum Category {
        one, two, three
    }
}
