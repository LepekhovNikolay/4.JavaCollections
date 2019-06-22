package com.javarush.task.task28.task2810.model;


//класс будет обобщать способ получения данных о вакансиях
public class Provider {
    private Strategy strategy;

    public Provider(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}
