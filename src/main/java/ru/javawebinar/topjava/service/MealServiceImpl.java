package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoInMemoryImpl;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public class MealServiceImpl implements MealService {
    private final MealDao MEAL_DAO = new MealDaoInMemoryImpl();

    @Override
    public List<Meal> getAllMeals() {
        return MEAL_DAO.getAllMeals();
    }

    @Override
    public Meal getMeal(Integer id) {
        return MEAL_DAO.getMeal(id);
    }

    @Override
    public void addMeal(LocalDateTime dateTime, String description, int calories) {
        MEAL_DAO.addMeal(dateTime, description, calories);
    }

    @Override
    public void updateMeal(Integer id, LocalDateTime dateTime, String description, int calories) {
        MEAL_DAO.updateMeal(id, dateTime, description, calories);
    }

    @Override
    public void deleteMeal(Integer id) {
        MEAL_DAO.deleteMeal(id);
    }
}
