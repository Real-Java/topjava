package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDaoInMemoryImpl implements MealDao {
    private static final AtomicInteger AUTO_ID = new AtomicInteger(0);
    private static final ConcurrentMap<Integer, Meal> db = new ConcurrentHashMap<>();

    static {
        db.put(AUTO_ID.incrementAndGet(), new Meal(AUTO_ID.get(), LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        db.put(AUTO_ID.incrementAndGet(), new Meal(AUTO_ID.get(), LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        db.put(AUTO_ID.incrementAndGet(), new Meal(AUTO_ID.get(), LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        db.put(AUTO_ID.incrementAndGet(), new Meal(AUTO_ID.get(), LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        db.put(AUTO_ID.incrementAndGet(), new Meal(AUTO_ID.get(), LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        db.put(AUTO_ID.incrementAndGet(), new Meal(AUTO_ID.get(), LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        db.put(AUTO_ID.incrementAndGet(), new Meal(AUTO_ID.get(), LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    @Override
    public List<Meal> getAllMeals() {
        List<Meal> mealList = new ArrayList<>();
        for (Map.Entry<Integer, Meal> pair : db.entrySet()) {
            mealList.add(pair.getValue());
        }
        return mealList;
    }

    @Override
    public Meal getMeal(Integer id) {
        return db.get(id);
    }

    @Override
    public void addMeal(LocalDateTime dateTime, String description, int calories) {
        Meal meal = new Meal(AUTO_ID.incrementAndGet(), dateTime, description, calories);
        db.put(meal.getId(), meal);
    }

    @Override
    public void updateMeal(Integer id, LocalDateTime dateTime, String description, int calories) {
        if (db.containsKey(id)) {
            Meal meal = new Meal(id, dateTime, description, calories);
            db.put(id, meal);
        }
    }

    @Override
    public void deleteMeal(Integer id) {
        db.remove(id);
    }
}
