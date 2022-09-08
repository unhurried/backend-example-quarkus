package io.github.unhurried.example.backend.quarkus.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
// Set @Table(name = "todo") when you need to specify the table name.
@Table(indexes = @Index(columnList = "userId"))
public class TodoEntity extends PanacheEntityBase {
    @Id
    @GeneratedValue
    private UUID id;
    private String title;
    private Category category;
    private String content;
    private String userId;

    public enum Category {
        one, two, three
    }
}
