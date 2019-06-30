package com.javarush.task.task28.task2810.model;


import com.javarush.task.task28.task2810.vo.Vacancy;

import java.util.List;

//будет отвечать за получение данных с сайта.
public interface Strategy {
    public List<Vacancy> getVacancies(String searchString);
}
