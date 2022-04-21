package com.taogen.demo.common;


import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public interface BaseCrudTest<T> {
    public <T> T addRandomEntity(Function<T, Integer> addEntityFunction, Function<T, Integer> getEntityIdFunction);

    public void testDeleteEntityById(Function<Integer, Integer> deleteEntityFunction, Integer id, Function<Integer, T> getEntityFunction);

    public void testListEntities(Supplier<List<T>> listEntityFunction);
}
