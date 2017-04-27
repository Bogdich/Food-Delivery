package com.controller;

import com.entity.Answer;
import com.entity.UserInfo;
import com.service.DAOService;
import com.service.Service;
import com.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping(value = "/food-delivery")
public class MainController {
    private Service service = new Service();
    private DAOService daoService = new DAOService();

//    //просмотреть расписание;
//    @RequestMapping(value= "/timetable",method = RequestMethod.GET)
//    @ResponseBody
//    public Object getTimetable() {
//        Answer answer = new Answer();
//        List<Timetable> list = service.getTimetable();
//        answer.setId("nil");
//        return list;
//    }
//
//    // отменить бронь
//    @RequestMapping(value= "/undo/{number}",method = RequestMethod.GET)
//    @ResponseBody
//    public Answer undoOrder(@PathVariable String number) {
//        Answer answer = new Answer();
//        answer.setId(service.undoOrder(number));
//        return answer;
//    }
//
//    //получить информацию по номеру своего заказа
//    @RequestMapping(value= "/order/info/{number}",method = RequestMethod.GET)
//    @ResponseBody
//    public Object getInfoAboutOrder(@PathVariable String number) {
//        Order order = service.getOrderById(number);
//        if(order == null){
//            Answer answer = new Answer();
//            answer.setId("Order by this number does not exist");
//            return answer;
//        }
//        return order;
//    }


    @RequestMapping(value= "/user/getInfo/{userId}",method = RequestMethod.GET)
    @ResponseBody
    public Object getInfoAboutOrder(@PathVariable int userId) {

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

        UserService userService = new UserService();
        userService.insertUser(login, password, name, surname, address, number, email);

        Answer answer = new Answer();
        answer.setError("OK");
        return answer;
    }
}
