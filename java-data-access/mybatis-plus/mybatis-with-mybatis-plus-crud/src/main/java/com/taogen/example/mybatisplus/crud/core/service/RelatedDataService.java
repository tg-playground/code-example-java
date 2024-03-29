package com.taogen.example.mybatisplus.crud.core.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.taogen.example.mybatisplus.crud.core.annotation.Related;
import com.taogen.example.mybatisplus.crud.core.util.SpringUtils;
import com.taogen.example.mybatisplus.crud.core.vo.IdName;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Related Data Service
 * <p>
 * Warning
 * 1. The generic type of Map<K, V> should be <String, Object>.
 * 2. Get value from map should using map.get(String.valueOf(key))
 */
@Slf4j
public class RelatedDataService {

    public static void setRelatedDataForList(List list, Class cls) {
        if (list == null || list.isEmpty() || cls == null) {
            return;
        }

        // get all fields of the class and its parent class
        List<Field> fields = getAllFieldsOfClassAndItsParent(cls);
        if (fields == null || fields.isEmpty()) {
            return;
        }

        // get fields have @Related annotation
        List<Field> relatedFieldList = fields.stream()
            .filter(item -> item.isAnnotationPresent(Related.class))
            .collect(Collectors.toList());

        for (Field field : relatedFieldList) {
            try {
                setRelatedFieldForList(list, field);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    private static void setRelatedFieldForList(List list, Field field) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        Set<String> relatedFieldValueSet = getFieldValuesFromList(list, field);
        List relatedEntityList = getRelatedEntityList(field.getAnnotation(Related.class), relatedFieldValueSet);
        setRelatedFieldDataToList(list, field, relatedEntityList);
    }

    /**
     * @param relatedAnnotation
     * @param relatedFieldValueSet
     * @return if RelatedType is LEVEL only return List<entityClass>, others RelatedType can return List<IdName> or List<entityClass>
     */
    private static List getRelatedEntityList(Related relatedAnnotation, Set<String> relatedFieldValueSet) {
        if (relatedFieldValueSet == null || relatedFieldValueSet.isEmpty()) {
            return Collections.emptyList();
        }
        List relatedEntityList = null;
        IService service = (IService) SpringUtils.getBean(relatedAnnotation.serviceClass());
        QueryWrapper queryWrapper = new QueryWrapper();
        if (IdName.class.equals(relatedAnnotation.returnType()) &&
            !Related.RelatedType.LEVEL.equals(relatedAnnotation.relatedType())) {
            queryWrapper.select(relatedAnnotation.idColumn(), relatedAnnotation.nameColumn());
        }
        queryWrapper.in(relatedAnnotation.idColumn(), relatedFieldValueSet);
        relatedEntityList = service.list(queryWrapper);
        if (relatedEntityList == null || relatedEntityList.isEmpty()) {
            return relatedEntityList;
        }
        if (Related.RelatedType.LEVEL.equals(relatedAnnotation.relatedType())) {
            Set<String> totalIds = relatedFieldValueSet;
            Set<String> parentIds = (Set<String>) relatedEntityList.stream()
                .map(item -> getObjectField(item, relatedAnnotation.parentIdFieldName()))
                .filter(item -> item != null)
                .map(Object::toString)
                .collect(Collectors.toCollection(LinkedHashSet::new));
            parentIds.removeAll(totalIds);
            totalIds.addAll(parentIds);
            while (parentIds != null && !parentIds.isEmpty()) {
                queryWrapper = new QueryWrapper();
                queryWrapper.in(relatedAnnotation.idColumn(), parentIds);
                List parentEntityList = service.list(queryWrapper);
                if (parentEntityList != null) {
                    relatedEntityList.addAll(parentEntityList);
                    parentIds = (Set<String>) parentEntityList.stream()
                        .map(item -> getObjectField(item, relatedAnnotation.parentIdFieldName()))
                        .filter(item -> item != null)
                        .map(Object::toString)
                        .collect(Collectors.toCollection(LinkedHashSet::new));
                    parentIds.removeAll(totalIds);
                    totalIds.addAll(parentIds);
                }
            }

        }
        return relatedEntityList;
    }

    /**
     * @param list              table data entity list
     * @param field
     * @param relatedEntityList
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     */
    private static void setRelatedFieldDataToList(List list, Field field, List relatedEntityList) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        if (list == null || list.isEmpty() || relatedEntityList == null || relatedEntityList.isEmpty()) {
            return;
        }
        Related relatedAnnotation = field.getAnnotation(Related.class);
        Map<String, Object> relatedEntityMap = new LinkedHashMap<>(relatedEntityList.size());
        for (Object relatedEntity : relatedEntityList) {
            relatedEntityMap.put(String.valueOf(getObjectField(relatedEntity, relatedAnnotation.idColumn())), relatedEntity);
        }
        for (Object entity : list) {
            Object relatedFieldValue = getObjectField(entity, relatedAnnotation.relatedFieldName());
            if (relatedFieldValue != null) {
                if (Related.RelatedType.SINGLE.equals(relatedAnnotation.relatedType())) {
                    Object fieldValue = relatedEntityMap.get(String.valueOf(relatedFieldValue));
                    if (IdName.class.equals(relatedAnnotation.returnType())) {
                        IdName idName = new IdName();
                        setObjectField(idName, relatedAnnotation.idColumn(),
                            parseLongToInteger(getObjectField(fieldValue, relatedAnnotation.idColumn()), null));
                        setObjectField(idName, relatedAnnotation.nameColumn(),
                            getObjectField(fieldValue, relatedAnnotation.nameColumn()));
                        fieldValue = idName;
                    }
                    setObjectField(entity, field.getName(), fieldValue);
                } else if (Related.RelatedType.MULTIPLE.equals(relatedAnnotation.relatedType())) {
                    Set<String> relatedFieldValues = Arrays.stream(relatedFieldValue.toString().split(","))
                        .map(String::trim)
                        .collect(Collectors.toCollection(LinkedHashSet::new));
                    if (relatedFieldValues == null || relatedFieldValues.isEmpty()) {
                        return;
                    }
                    List fieldValue = relatedFieldValues.stream()
                        .map(item -> {
                            Object itemValue = relatedEntityMap.get(String.valueOf(item));
                            if (IdName.class.equals(relatedAnnotation.returnType())) {
                                IdName idName = new IdName();
                                setObjectField(idName, relatedAnnotation.idColumn(),
                                    parseLongToInteger(getObjectField(itemValue, relatedAnnotation.idColumn()), null));
                                setObjectField(idName, relatedAnnotation.nameColumn(),
                                    getObjectField(itemValue, relatedAnnotation.nameColumn()));
                                itemValue = idName;
                            }
                            return itemValue;
                        })
                        .collect(Collectors.toList());
                    setObjectField(entity, field.getName(), fieldValue);
                } else if (Related.RelatedType.LEVEL.equals(relatedAnnotation.relatedType())) {
                    List fieldValue = new ArrayList();
                    Object fieldItem = relatedEntityMap.get(String.valueOf(relatedFieldValue));
                    while (fieldItem != null) {
                        fieldValue.add(fieldItem);
                        fieldItem = relatedEntityMap.get(String.valueOf(getObjectField(fieldItem, relatedAnnotation.parentIdFieldName())));
                    }
                    setObjectField(entity, field.getName(), fieldValue);
                }
            }
        }
    }

    /**
     * @param list
     * @param field
     * @return You can consider to return two data types:
     * 1) HashSet: for higher efficiency.
     * 2) LinkedHashSet: for keeping insertion order.
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     */
    private static Set<String> getFieldValuesFromList(List list, Field field) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        if (list == null || list.isEmpty()) {
            return Collections.emptySet();
        }
        Related relatedAnnotation = field.getAnnotation(Related.class);
        Set<String> ids = new LinkedHashSet<>();
        for (Object obj : list) {
            Object relatedFieldValue = getObjectField(obj, relatedAnnotation.relatedFieldName());
            if (relatedFieldValue != null) {
                if (Related.RelatedType.SINGLE.equals(relatedAnnotation.relatedType()) ||
                    Related.RelatedType.LEVEL.equals(relatedAnnotation.relatedType())) {
                    ids.add(relatedFieldValue.toString());
                } else if (Related.RelatedType.MULTIPLE.equals(relatedAnnotation.relatedType())) {
                    ids.addAll(Arrays.stream(relatedFieldValue.toString().split(","))
                        .map(String::trim)
                        .collect(Collectors.toList()));
                }
            }
        }
        return ids != null ? ids : Collections.emptySet();
    }

    public static Object getObjectField(Object obj, String fieldName) {
        if (obj == null) {
            return null;
        }
        Object returnObject = null;
        String methodName = "get" + firstLetterToUpperCase(fieldName);
        Class classNode = obj.getClass();
        boolean isDone = false;
        while (!Object.class.equals(classNode)) {
            List<Method> methodList = Arrays.asList(classNode.getDeclaredMethods());
            for (Method method : methodList) {
                if (method.getName().equals(methodName)) {
                    try {
                        returnObject = method.invoke(obj);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    isDone = true;
                    break;
                }
            }
            if (isDone) {
                break;
            }
            classNode = classNode.getSuperclass();
        }
        return returnObject;
    }

    public static void setObjectField(Object obj, String fieldName, Object fieldValue) {
        if (obj == null) {
            return;
        }
        String methodName = "set" + firstLetterToUpperCase(fieldName);
        Class classNode = obj.getClass();
        boolean isDone = false;
        while (!Object.class.equals(classNode)) {
            List<Method> methodList = Arrays.asList(classNode.getDeclaredMethods());
            for (Method method : methodList) {
                if (method.getName().equals(methodName)) {
                    try {
                        method.invoke(obj, fieldValue);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    isDone = true;
                    break;
                }
            }
            if (isDone) {
                break;
            }
            classNode = classNode.getSuperclass();
        }
    }

    public static String firstLetterToUpperCase(String source) {
        if (source == null || source.isEmpty()) {
            return source;
        }
        return source.substring(0, 1).toUpperCase() + source.substring(1);
    }

    private static List<Field> getAllFieldsOfClassAndItsParent(Class cls) {
        List<Field> fields = new ArrayList<>();
        fields.addAll(Arrays.asList(cls.getDeclaredFields()));
        Class node = cls;
        while (!Object.class.equals(node.getSuperclass())) {
            node = node.getSuperclass();
            fields.addAll(Arrays.asList(node.getDeclaredFields()));
        }
        return fields;
    }

    private static List<Method> getAllMethodsOfClassAndItsParent(Class cls) {
        List<Method> methods = new ArrayList<>();
        methods.addAll(Arrays.asList(cls.getDeclaredMethods()));
        Class node = cls;
        while (!Object.class.equals(node.getSuperclass())) {
            node = node.getSuperclass();
            methods.addAll(Arrays.asList(node.getDeclaredMethods()));
        }
        return methods;
    }

    private static Integer parseLongToInteger(Object longValue, Integer defaultIntValue) {
        if (longValue == null) {
            return defaultIntValue;
        }
        return Integer.parseInt(String.valueOf(longValue));
    }
}
