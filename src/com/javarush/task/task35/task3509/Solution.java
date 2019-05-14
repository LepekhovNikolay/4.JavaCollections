package com.javarush.task.task35.task3509;

import java.util.*;


/* 
Collections & Generics
*/
public class Solution {

    public static void main(String[] args) {
        ArrayList<String> list = Solution.newArrayList("1", "2", "3", "4");
        list.forEach(System.out::println);
        Solution.<String>newHashSet("1", "2", "3", "4").forEach(System.out::println);
    }

    public static <T> ArrayList<T> newArrayList(T ... elements) {
        //напишите тут ваш код
        ArrayList<T> list = new ArrayList<>(elements.length);
            list.addAll(Arrays.asList(elements));
        return list;
    }

    public static <T> HashSet<T> newHashSet(T... elements) {
        //напишите тут ваш код
        HashSet<T> result = new HashSet<T>(elements.length);
        result.addAll(Arrays.asList(elements));
        return result;
    }

    public static <K,V> HashMap<K,V> newHashMap(List<? extends K> keys, List<? extends V> values) {
        //напишите тут ваш код
        if (keys.size() != values.size())
            throw new IllegalArgumentException();
        HashMap<K,V> resultMap = new HashMap<>(keys.size());
        for (int i = 0; i < keys.size(); i++) {
            resultMap.put(keys.get(i), values.get(i));
        }
        return resultMap;
    }
}
