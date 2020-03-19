package com.envy.collections;

import org.junit.Test;

import java.sql.Struct;
import java.util.*;

import static org.junit.Assert.*;

public class MapTest {

    public static final String KEY_1 = "key_1";
    public static final String KEY_2 = "key_2";
    public static final String VALUE_1 = "value_1";
    public static final String VALUE_2 = "value_2";
    public static final String NOT_EXISTING_KEY = "not_existing_key";

    @Test
    public void addElementsToMap(){
        Map<String, String> map = getTestMap();

        /*
           equal to
           Map<String, String> map2 = new HashMap<>(map);
        */
        Map<String, String> map2 = new HashMap<>();
        map2.putAll(map);
    }

    private Map<String, String> getTestMap() {
        Map<String, String> map = new HashMap<>();

        map.put(KEY_1, VALUE_1);
        map.put(KEY_2, VALUE_2);
        return map;
    }

    @Test
    public void  remove(){
        Map<String, String> map = getTestMap();

        String value = map.remove(KEY_1);
        assertEquals(VALUE_1, value);

        value = map.remove(NOT_EXISTING_KEY);
        assertNull(value);

        boolean remove = map.remove(KEY_1, VALUE_2);
        assertFalse(remove);

        remove = map.remove(KEY_2, VALUE_2);
        assertTrue(remove);
    }

    @Test
    public void findByKey(){
        Map<String, String> testMap = getTestMap();
        String value = testMap.get(KEY_1);
        assertEquals(VALUE_1, value);

//        In case when we cannot find element map will return null
        String value2 = testMap.get(NOT_EXISTING_KEY);
        assertNull(value2);

//        since JAVA 1.8
        String defaultValue = "default";
        String s = testMap.getOrDefault(KEY_1, defaultValue);
        assertEquals(s, VALUE_1);

        String s1 = testMap.getOrDefault(NOT_EXISTING_KEY, defaultValue);
        assertEquals(s1, defaultValue);
    }

    @Test
    public void findByValue(){
        Map<String, String> testMap = getTestMap();
        boolean b = testMap.containsValue(VALUE_1);
        assertTrue(b);
    }

    @Test
    public void filter(){
        Map<String, String> testMap = getTestMap();

        Iterator<Map.Entry<String, String>> iterator = testMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String> next = iterator.next();
            if (next.getKey().equals(KEY_1))
                iterator.remove();
        }

        assertFalse(testMap.containsKey(KEY_1));
        assertTrue(testMap.containsKey(KEY_2));

    }

    public void filterAfterJava8(){
        Map<String, String> testMap = getTestMap();
        testMap.entrySet().removeIf(entry -> entry.getKey().equals(KEY_1));

        assertFalse(testMap.containsKey(KEY_1));
        assertTrue(testMap.containsKey(KEY_2));
    }
}
