package web.service;

import web.model.Car;
import web.model.CarsList;

import java.util.ArrayList;
import java.util.List;

public class CarServiseImpl implements CarService {

    @Override
    public List<Car> showCars(int count) {
        if (count > 5) count = 5;
        List<Car> cars = CarsList.createListCars();
        List<Car> resultListCars = new ArrayList<>();
        for (int i= 0; i < count; i ++) {
            resultListCars.add(cars.get(i));
        }
        return resultListCars;
    }
}
