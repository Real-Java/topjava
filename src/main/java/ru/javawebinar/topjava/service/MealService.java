package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface MealService {
    List<Meal> getAllMeals();

    Meal getMeal(Integer id);

    void addMeal(LocalDateTime dateTime, String description, int calories);

    void updateMeal(Integer id, LocalDateTime dateTime, String description, int calories);

    void deleteMeal(Integer id);
}
