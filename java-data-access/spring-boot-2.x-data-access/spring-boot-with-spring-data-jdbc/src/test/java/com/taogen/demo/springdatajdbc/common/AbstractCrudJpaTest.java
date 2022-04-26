package com.taogen.demo.springdatajdbc.common;

import com.taogen.demo.common.test.AbstractCrudTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Taogen
 */
@Slf4j
public abstract class AbstractCrudJpaTest<T> extends AbstractCrudTest<T> {

    public <T> T addRandomEntityJpa(Function<T, T> addEntityFunction, Function<T, Integer> getEntityIdFunction) {
        T t = buildEntityWithoutId();
        T result = addEntityFunction.apply(t);
        assertNotNull(result);
        assertNotNull(getEntityIdFunction.apply(t));
        log.info("Added user: {}", t);
        return t;
    }

    public void testDeleteEntityByIdJpa(Consumer<Integer> deleteEntityFunction,
                                        Integer id,
                                        Function<Integer, Optional<T>> getEntityFunction) {
        deleteEntityFunction.accept(id);
        Optional<T> fetchEntity = getEntityFunction.apply(id);
        assertFalse(fetchEntity.isPresent());
    }

    public void testUpdateEntityByIdJpa(Function<Integer, Optional<T>> getEntityFunction,
                                        Integer id,
                                        BiConsumer<T, String> setUpdateFieldConsumer,
                                        Function<T, T> updateEntityFunction,
                                        Function<T, String> getUpdateFieldSupplier) {
        Optional<T> entity = getEntityFunction.apply(id);
        String newFieldValue = "newFieldValue" + System.currentTimeMillis();
        log.info("newFieldValue: {}", newFieldValue);
        setUpdateFieldConsumer.accept(entity.get(), newFieldValue);
        T result = updateEntityFunction.apply(entity.get());
        assertNotNull(result);
        Optional<T> fetchEntity = getEntityFunction.apply(id);
        assertEquals(newFieldValue, getUpdateFieldSupplier.apply(fetchEntity.get()));
    }

    public void testGetEntityByIdJpa(Function<Integer, Optional<T>> getEntityFunction, Integer id) {
        Optional<T> entity = getEntityFunction.apply(id);
        assertTrue(entity.isPresent());
        log.info("entity: {}", entity.get());
    }

    public void testListEntitiesJpa(Function<Pageable, Page<T>> listEntityBiFunction, Pageable page) {
        Page<T> list = listEntityBiFunction.apply(page);
        assertNotNull(list);
        assertFalse(list.isEmpty());
        log.info("list size: {}", list.getSize());
    }
}
