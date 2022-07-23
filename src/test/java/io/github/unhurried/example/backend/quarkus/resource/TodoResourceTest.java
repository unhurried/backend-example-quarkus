package io.github.unhurried.example.backend.quarkus.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import java.security.Principal;
import java.util.UUID;

import javax.inject.Inject;
import javax.ws.rs.core.SecurityContext;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import io.github.unhurried.example.backend.quarkus.entity.TodoEntity;
import io.github.unhurried.example.backend.quarkus.entity.TodoEntity.Category;
import io.quarkus.hibernate.reactive.panache.PanacheQuery;
import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.quarkus.test.security.TestSecurity;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.helpers.test.UniAssertSubscriber;

@QuarkusTest
public class TodoResourceTest {
    @Inject
    TodoResource todoResource;

    @InjectMock
    SecurityContext sContext;

    @Test
    @TestSecurity(authorizationEnabled = false)
    public void test() {
        var mockUserId = UUID.randomUUID().toString();
        var mockEntity = new TodoEntity();
        mockEntity.id = UUID.randomUUID();
        mockEntity.title = "Test item";
        mockEntity.category = Category.one;
        mockEntity.content = "This is a test item";
        mockEntity.userId = mockUserId;
        Uni<TodoEntity> mockUniEntity = Uni.createFrom().item(mockEntity);

        @SuppressWarnings("unchecked")
        PanacheQuery<TodoEntity> mockQuery = (PanacheQuery<TodoEntity>) Mockito.mock(PanacheQuery.class);
        Mockito.when(mockQuery.firstResult()).thenReturn(mockUniEntity);

        PanacheMock.mock(TodoEntity.class);
        Mockito.when(TodoEntity.<TodoEntity>find(anyString(), any(Object.class))).thenReturn(mockQuery);

        var mockPrincipal = Mockito.mock(Principal.class);
        Mockito.when(mockPrincipal.getName()).thenReturn(mockUserId);
        Mockito.when(sContext.getUserPrincipal()).thenReturn(mockPrincipal);

        var actual = todoResource.get(mockUserId)
            .subscribe().withSubscriber(UniAssertSubscriber.create()).assertCompleted().getItem();
        assertTrue(actual.id.matches("[a-z0-9-]{36}"));
        assertEquals(mockEntity.title, actual.title);
        assertEquals(mockEntity.category.toString(), actual.category);
        assertEquals(mockEntity.content, actual.content);
    }
}
