package com.controller;

import com.model.Answer;
import com.model.Order;
import com.model.Timetable;
import com.service.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping(value = "/food-delivery")
public class MainController {
    private Service service = new Service();

    //просмотреть расписание;
    @RequestMapping(value= "/timetable",method = RequestMethod.GET)
    @ResponseBody
    public Object getTimetable() {
        Answer answer = new Answer();
        List<Timetable> list = service.getTimetable();
        answer.setId("nil");
        return list;
    }

    // отменить бронь
    @RequestMapping(value= "/undo/{number}",method = RequestMethod.GET)
    @ResponseBody
    public Answer undoOrder(@PathVariable String number) {
        Answer answer = new Answer();
        answer.setId(service.undoOrder(number));
        return answer;
    }

    //получить информацию по номеру своего заказа
    @RequestMapping(value= "/order/info/{number}",method = RequestMethod.GET)
    @ResponseBody
    public Object getInfoAboutOrder(@PathVariable String number) {
        Order order = service.getOrderById(number);
        if(order == null){
            Answer answer = new Answer();
            answer.setId("Order by this number does not exist");
            return answer;
        }
        return order;
    }

    // забронировать одно или несколько мест на сеансе;

    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    @ResponseBody
    public Answer createUser(@RequestParam("login")String login, @RequestParam("password")String password) {
        Answer answer = new Answer();
        service.init();
        answer.setId(String.valueOf(service.updateUser(login, password)));
        return answer;
    }
}
