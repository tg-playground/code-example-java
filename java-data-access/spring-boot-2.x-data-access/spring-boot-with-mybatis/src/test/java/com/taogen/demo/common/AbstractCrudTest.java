package com.taogen.demo.common;

import com.taogen.demo.vo.Page;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Taogen
 */
@Slf4j
public abstract class AbstractCrudTest<T> implements BaseCrudTest<T> {
    @Override
    public <T> T addRandomEntity(Function<T, Integer> addEntityFunction, Function<T, Integer> getEntityIdFunction) {
        T t = buildEntityWithoutId();
        int result = addEntityFunction.apply(t);
        assertEquals(1, result);
        assertNotNull(getEntityIdFunction.apply(t));
        log.info("Added user: {}", t);
        return t;
    }

    protected abstract <T> T buildEntityWithoutId();

    @Override
    public void testDeleteEntityById(Function<Integer, Integer> deleteEntityFunction,
                                     Integer id,
                                     Function<Integer, T> getEntityFunction) {
        int result = deleteEntityFunction.apply(id);
        deleteEntityFunction.apply(id);
        assertEquals(1, result);
        T fetchEntity = getEntityFunction.apply(id);
        assertNull(fetchEntity);
    }


    @Override
    public void testUpdateEntityById(Function<Integer, T> getEntityFunction,
                                     Integer id,
                                     BiConsumer<T, String> setUpdateFieldConsumer,
                                     Function<T, Integer> updateEntityFunction,
                                     Function<T, String> getUpdateFieldSupplier) {
        T entity = getEntityFunction.apply(id);
        String newFieldValue = "newFieldValue" + System.currentTimeMillis();
        log.info("newFieldValue: {}", newFieldValue);
        setUpdateFieldConsumer.accept(entity, newFieldValue);
        Integer result = updateEntityFunction.apply(entity);
        assertEquals(1, result);
        T fetchEntity = getEntityFunction.apply(id);
        assertEquals(newFieldValue, getUpdateFieldSupplier.apply(fetchEntity));
    }

    @Override
    public void testGetEntityById(Function<Integer, T> getEntityFunction, Integer id) {
        T entity = getEntityFunction.apply(id);
        assertNotNull(entity);
        log.info("entity: {}", entity);
    }

    @Override
    public void testListEntities(BiFunction<Page, T, List<T>> listEntityBiFunction) {
        List<T> list = listEntityBiFunction.apply(new Page(1, 10), null);
        assertNotNull(list);
        assertFalse(list.isEmpty());
        log.info("list size: {}", list.size());
    }
}
