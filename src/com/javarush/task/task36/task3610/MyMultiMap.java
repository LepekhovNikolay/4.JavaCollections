package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;//database
    private int repeatCount;//количество значений по ключу

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        //напишите тут ваш код
        return values().size();
    }

    @Override
    public V put(K key, V value) {
        //напишите тут ваш код
        List<V> listValue = map.get(key);
        if (listValue == null) {//по данному ключу еще ничего не лежало
            listValue = new ArrayList<>(repeatCount);
            listValue.add(value);
            map.put(key, listValue);
            return null;
        } else if (listValue.size() == repeatCount) //при количестве значений равному количеству значений по ключу
            listValue.remove(0);
        //теперь для случая переполненного листа и не заполненного ситуация одинаковая
        listValue.add(value);
        return listValue.get(listValue.size() - 2);
    }

    @Override
    public V remove(Object key) {
        //напишите тут ваш код
        List<V> listValue = map.get(key);
        if (listValue == null)
            return null;//такой пары ключ/значение нет
        if (listValue.size() == 1) {//если значение по ключу одно, то удаляем связку ключ/значение
            map.remove(key);
        }
        return listValue.remove(0);
    }

    @Override
    public Set<K> keySet() {
        //напишите тут ваш код
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        //напишите тут ваш код
        Collection<V> allValues = new ArrayList<>();
        for (K key : keySet()) {
            allValues.addAll(map.get(key));
        }
        return allValues;
    }

    @Override
    public boolean containsKey(Object key) {
        //напишите тут ваш код
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        //напишите тут ваш код
        return values().contains(value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}