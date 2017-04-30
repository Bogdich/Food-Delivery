package com.controller;

import com.entity.Answer;
import com.entity.Category;
import com.entity.Dish;
import com.entity.UserInfo;
import com.service.DAOService;
import com.service.DishAndCategoryService;
import com.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping(value = "/food-delivery")
public class MainController {

    private DAOService daoService = new DAOService();

    @RequestMapping(value = "/category/insertCategory",method = RequestMethod.POST)
    @ResponseBody
    public Answer createCategory(@RequestParam("name")String name) {

        daoService.init();
        Answer answer = new Answer();

        DishAndCategoryService dishAndCategoryService = new DishAndCategoryService();
        int id = dishAndCategoryService.insertCategory(name);

        if (id != 0) answer.setError("OK");
        else answer.setError("SOMETHING WRONG");

        return answer;
    }

    @RequestMapping(value= "/category/getCategories",method = RequestMethod.GET)
    @ResponseBody
    public Object getAllCategories() {

        daoService.init();

        List<Category> categories;

        DishAndCategoryService dishAndCategoryService = new DishAndCategoryService();
        categories = dishAndCategoryService.getAllCategories();

        if(categories.isEmpty()){
            Answer answer = new Answer();
            answer.setError("CATEGORIES NOT EXIST");
            return answer;
        }
        return categories;
    }

    @RequestMapping(value= "/dish/getInfo/{dishId}",method = RequestMethod.GET)
    @ResponseBody
    public Object getDishById(@PathVariable int dishId) {

        daoService.init();

        Dish dish;

        DishAndCategoryService dishAndCategoryService = new DishAndCategoryService();
        dish = dishAndCategoryService.getDish(dishId);

        if(dish == null){
            Answer answer = new Answer();
            answer.setError("DISH NOT EXIST");
            return answer;
        }
        return dish;
    }

    @RequestMapping(value= "/dish/getDishes/{categoryId}",method = RequestMethod.GET)
    @ResponseBody
    public Object getDishesByCategoryId(@PathVariable int categoryId) {

        daoService.init();

        List<Dish> dishes;

        DishAndCategoryService dishAndCategoryService = new DishAndCategoryService();
        dishes = dishAndCategoryService.getDishesForCategory(categoryId);

        if(dishes.isEmpty()){
            Answer answer = new Answer();
            answer.setError("DISHES FOR THIS CATEGORY NOT EXIST");
            return answer;
        }
        return dishes;
    }

    @RequestMapping(value = "/dish/insertDish",method = RequestMethod.POST)
    @ResponseBody
    public Answer createDish(@RequestParam("name")String name,
                             @RequestParam("description")String description,
                             @RequestParam("weight")int weight,
                             @RequestParam("price")BigDecimal price,
                             @RequestParam("categoryId")int categoryId) {

        daoService.init();
        Answer answer = new Answer();

        DishAndCategoryService dishAndCategoryService = new DishAndCategoryService();
        int id = dishAndCategoryService.insertDish(name, description, weight, price, categoryId);


        if (id != 0) answer.setError("OK");
        else answer.setError("SOMETHING WRONG");

        return answer;
    }

    @RequestMapping(value= "/user/getInfo/{userId}",method = RequestMethod.GET)
    @ResponseBody
    public Object getInfoAboutUser(@PathVariable int userId) {

        daoService.init();

        UserInfo userInfo = new UserInfo();

        UserService userService = new UserService();
        userInfo = userService.getUserInfo(userId);

        if(userInfo == null){
            Answer answer = new Answer();
            answer.setError("USER NOT EXIST");
            return answer;
        }
        return userInfo;
    }

    @RequestMapping(value = "/user/insertUser",method = RequestMethod.POST)
    @ResponseBody
    public Answer createUser(@RequestParam("login")String login,
                             @RequestParam("password")String password,
                             @RequestParam("name")String name,
                             @RequestParam("surname")String surname,
                             @RequestParam("address")String address,
                             @RequestParam("number")String number,
                             @RequestParam("email")String email) {

        daoService.init();
        Answer answer = new Answer();

        UserService userService = new UserService();
        int id = userService.insertUser(login, password, name, surname, address, number, email);

        if (id != 0) {

            answer.setResponseID(id);
            answer.setError("OK");
        }
        else answer.setError("SOMETHING WRONG");

        return answer;
    }
}
