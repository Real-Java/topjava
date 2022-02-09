package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.MealServiceImpl;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private final MealService mealService = new MealServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward;
        String action = request.getParameter("action");
        if (action == null) {
            log.debug("forward to meals");
            List<MealTo> mealsTo = MealsUtil.filteredByStreams(mealService.getAllMeals(), LocalTime.MIN, LocalTime.MAX, MealsUtil.CALORIES_PER_DAY);
            request.setAttribute("mealsTo", mealsTo);
            forward = "meals.jsp";
        } else if (action.equals("update")) {
            log.debug("forward to update");
            forward = "add_or_edit.jsp";
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("meal", mealService.getMeal(id));

        } else if (action.equals("delete")) {
            log.debug("delete meal");
            forward = "meals.jsp";
            int id = Integer.parseInt(request.getParameter("id"));
            mealService.deleteMeal(id);
            List<MealTo> mealsTo = MealsUtil.filteredByStreams(mealService.getAllMeals(), LocalTime.MIN, LocalTime.MAX, MealsUtil.CALORIES_PER_DAY);
            request.setAttribute("mealsTo", mealsTo);
        } else {
            log.debug("forward to update");
            forward = "add_or_edit.jsp";
        }
        request.getRequestDispatcher(forward).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String stringDate = request.getParameter("date");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(stringDate, dtf);
        int calories = Integer.parseInt(request.getParameter("calories"));
        String id = request.getParameter("id");
        String description = request.getParameter("description");
        if (id == null || id.isEmpty()) {
            mealService.addMeal(localDateTime, description, calories);
        } else {
            mealService.updateMeal(Integer.parseInt(id), localDateTime, description, calories);
        }
        List<MealTo> mealsTo = MealsUtil.filteredByStreams(mealService.getAllMeals(), LocalTime.MIN, LocalTime.MAX, MealsUtil.CALORIES_PER_DAY);
        request.setAttribute("mealsTo", mealsTo);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}
