package web.model;

import java.util.ArrayList;
import java.util.List;

public class CarsList {

    private static List<Car> carsList = new ArrayList<>();


    public static List<Car> createListCars() {

        Car car1 = new Car("Mazda 6",3,2014);

        Car car2 = new Car("Cadillac Eldorado",7,1977);

        Car car3 = new Car("Cadillac Escalade",4,2015);

        Car car4 = new Car("Cadillac Escalade",3,2010);

        Car car5 = new Car("Land Rover Discovery",4,2013);


        carsList.add(car1);
        carsList.add(car2);
        carsList.add(car3);
        carsList.add(car4);
        carsList.add(car5);
        return carsList;
    }
}
