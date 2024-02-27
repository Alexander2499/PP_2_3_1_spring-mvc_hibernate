package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import web.model.Car;
import web.model.CarsList;
import web.service.CarService;
import web.service.CarServiseImpl;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarController {
    CarServiseImpl carServiseImpl = new CarServiseImpl();


    @GetMapping(value = "/cars")
    public String showCars(Model model) {
        List<Car> carsList = carServiseImpl.showCars(5);
        model.addAttribute("cars", carsList);
        return "cars";
    }

    @RequestMapping(value = "/cars",params = {"count"})
    public String showCountCars(@RequestParam(value = "count") int count, Model model) {
        System.out.println(count);
        List<Car> carsList = carServiseImpl.showCars(count);
        model.addAttribute("cars", carsList);
        return "cars";
    }
}
