package com.controller;

import com.model.Answer;
import com.model.Order;
import com.model.Timetable;
import com.service.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping(value = "/cinema")
public class MainController {
    private Service service = new Service();

    //просмотреть расписание;
    @RequestMapping(value= "/timetable",method = RequestMethod.GET)
    @ResponseBody
    public Object getTimetable() {
        Answer answer = new Answer();
        List<Timetable> list = service.getTimetable();
        answer.setAnswer("nil");
        return list;
    }

    // отменить бронь
    @RequestMapping(value= "/undo/{number}",method = RequestMethod.GET)
    @ResponseBody
    public Answer undoOrder(@PathVariable String number) {
        Answer answer = new Answer();
        answer.setAnswer(service.undoOrder(number));
        return answer;
    }

    //получить информацию по номеру своего заказа
    @RequestMapping(value= "/order/info/{number}",method = RequestMethod.GET)
    @ResponseBody
    public Object getInfoAboutOrder(@PathVariable String number) {
        Order order = service.getOrderById(number);
        if(order == null){
            Answer answer = new Answer();
            answer.setAnswer("Order by this number does not exist");
            return answer;
        }
        return order;
    }

    // забронировать одно или несколько мест на сеансе;
    @RequestMapping(value = "/order/add",method = RequestMethod.POST)
    @ResponseBody
    public Answer createOrder(@RequestParam("name")String name, @RequestParam("date")String date,
                              @RequestParam("time")String time, @RequestParam("count")String count) {
        Answer answer = new Answer();
        answer.setAnswer(service.addOrder(name,date,time,count));
        return answer;
    }
}
