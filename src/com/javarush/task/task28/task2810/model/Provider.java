package com.javarush.task.task28.task2810.model;


import com.javarush.task.task28.task2810.vo.Vacancy;

import java.util.List;

//класс будет обобщать способ получения данных о вакансиях
public class Provider {
    private Strategy strategy;

    public Provider(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public List<Vacancy> getJavaVacancies(String searchString) {
        try {
            return strategy.getVacancies(searchString);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return null;
    }
}
