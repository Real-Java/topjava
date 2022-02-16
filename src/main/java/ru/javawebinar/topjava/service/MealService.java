package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

import java.util.Collection;

@Service
public class MealService {

    private final MealRepository repository;

    @Autowired
    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal save(Meal meal) {
        return repository.save(meal);
    }

    public boolean delete(int id, Integer userId) {
        boolean isDelete = repository.delete(id, userId);
        checkNotFoundWithId(isDelete, id);
        return isDelete;
    }

    public Meal get(int id, Integer userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    public Collection<Meal> getAll(Integer userId) {
        return repository.getAll(userId);
    }
}