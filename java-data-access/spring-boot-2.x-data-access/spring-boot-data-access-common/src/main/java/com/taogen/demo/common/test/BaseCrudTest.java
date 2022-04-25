package com.taogen.demo.common.test;


import com.taogen.demo.common.vo.Page;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author Taogen
 */
public interface BaseCrudTest<T> {
    <T> T addRandomEntity(Function<T, Integer> addEntityFunction,
                          Function<T, Integer> getEntityIdFunction);

    void testDeleteEntityById(Function<Integer, Integer> deleteEntityFunction,
                              Integer id,
                              Function<Integer, T> getEntityFunction);

    void testUpdateEntityById(Function<Integer, T> getEntityFunction,
                              Integer id,
                              BiConsumer<T, String> setUpdateFieldConsumer,
                              Function<T, Integer> updateEntityFunction,
                              Function<T, String> getUpdateFieldSupplier);

    void testGetEntityById(Function<Integer, T> getEntityFunction,
                           Integer id);

    void testListEntities(BiFunction<Page, T, List<T>> listEntityBiFunction);
}
