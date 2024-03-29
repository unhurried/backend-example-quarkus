package io.github.unhurried.example.backend.quarkus.entity;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;


import io.github.unhurried.example.backend.quarkus.entity.TodoEntity.Category;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.configuration.ProfileManager;

/** Insert test records only when the application is run in dev mode. */
@ApplicationScoped
public class DBInit {

    void onStart(@Observes StartupEvent ev) {
        if (ProfileManager.getActiveProfile().equals("dev")) {
            initdb();
        }
    }

    private void initdb() {
        var t = new TodoEntity();
        t.setTitle("test item");
        t.setCategory(Category.one);
        t.setContent("This is a test item.");
        t.setUserId("sub-for-test");
        Panache.<TodoEntity>withTransaction(t::persist).await().indefinitely();
    }
}
