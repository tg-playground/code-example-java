package com.taogen.demo.common;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Taogen
 */
@Slf4j
public abstract class AbstractCrudTest<T> implements BaseCrudTest<T>{
    @Override
    public <T> T addRandomEntity(Function<T, Integer> addEntityFunction, Function<T, Integer> getEntityIdFunction) {
        T t = buildEntityWithoutId();
        int result = addEntityFunction.apply(t);
        assertEquals(1, result);
        assertNotNull(getEntityIdFunction.apply(t));
        log.info("user: {}", t);
        return t;
    }

    protected abstract <T> T buildEntityWithoutId();

    @Override
    public void testDeleteEntityById(Function<Integer, Integer> deleteEntityFunction, Integer id, Function<Integer, T> getEntityFunction) {
        int result = deleteEntityFunction.apply(id);
        deleteEntityFunction.apply(id);
        assertEquals(1, result);
        T fetchEntity = getEntityFunction.apply(id);
        assertNull(fetchEntity);
    }

    public void testUpdateEntityById() {

    }

    public void testGetEntityById() {

    }

    @Override
    public void testListEntities(Supplier<List<T>> listEntityFunction) {
        List<T> list = listEntityFunction.get();
        assertNotNull(list);
        assertFalse(list.isEmpty());
        log.info("list size: {}", list.size());
    }
}
