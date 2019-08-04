package com.javarush.task.task26.task2613;


import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;
import org.apache.commons.collections4.map.LinkedMap;

import java.util.*;

public class CurrencyManipulator {
    private String currencyCode;//код валюты, состоит из трех символов, например USD

    private Map<Integer, Integer> denominations = new HashMap<>();//номинал, количество

    public boolean hasMoney() {
        return !denominations.isEmpty();
    }

    public int getTotalAmount() {
        return denominations.entrySet().stream().mapToInt(c -> c.getValue() * c.getKey()).sum();
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return getTotalAmount() >= expectedAmount;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException{
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(denominations.entrySet());
        Collections.sort(list, (o1, o2) -> o2.getKey().compareTo(o1.getKey()));
        int curAmount = 0;
        boolean isEnough = false;
        int key;
        int value;
        Map<Integer, Integer> resultMap = new LinkedMap<>();
        for (int i = 0; i < list.size(); i++) {
            key = list.get(i).getKey();
            value = 0;
            for (int j = 0; j < list.get(i).getValue(); j++) {
                if (curAmount + list.get(i).getKey() > expectedAmount)
                    break;
                value++;
                curAmount+= list.get(i).getKey();
                if (curAmount == expectedAmount) {
                    isEnough = true;
                    break;
                }
            }
            if (value != 0)
                resultMap.put(key, value);
            if (isEnough)
                break;
        }
        if (curAmount != expectedAmount)
            throw new NotEnoughMoneyException();

        for (Map.Entry<Integer, Integer> entry : resultMap.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            if (denominations.get(key).equals(value))
                denominations.remove(key);
            else
                denominations.put(key, denominations.get(key) - value);
        }

        return resultMap;

    }

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public void addAmount(int denomination, int count) {
        if (denominations.containsKey(denomination)) {
            count += denominations.get(denomination);
        }
        denominations.put(denomination, count);
    }

    public String getCurrencyCode() {
        return currencyCode;
    }
}
