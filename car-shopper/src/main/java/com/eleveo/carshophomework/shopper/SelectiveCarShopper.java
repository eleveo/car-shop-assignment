package com.eleveo.carshophomework.shopper;

import com.eleveo.carshop.client.ApiClient;
import com.eleveo.carshop.client.DefaultApi;
import com.eleveo.carshop.client.model.Car;
import com.eleveo.carshop.client.model.Order;

import java.util.*;

public class SelectiveCarShopper implements CarShopper {

    @Override
    public void selectAndBuyCars(String serverUri) {
        DefaultApi defaultApi = new DefaultApi(new ApiClient().setBasePath(serverUri));

        //find cars that fit the client's request
        List<Car> cars = defaultApi.getCars().block().getCars();
        List<Car> selectedCars = getSelectiveCarsFromList(cars);

        //create orders based on the found cars
        for (Car car : selectedCars) {
            Order order = new Order();
            order.setCarId(car.getCarId());
            defaultApi.orderCar(order).block();
        }
    }

    /**
     * Finds five cars which have the most engine power and up to two of them have the same engine type
     *
     * @param cars
     * @return car list
     */
    private List<Car> getSelectiveCarsFromList(List<Car> cars) {
        List<Car> result = new ArrayList<>();
        Map<Car.EngineTypeEnum, Integer> carQuotaPerType = new EnumMap<>(Car.EngineTypeEnum.class);

        //sort given car list by engine power
        List<Car> sortedCarList = cars.stream().sorted(Comparator.comparing(Car::getPowerWatts).reversed()).toList();

        for (Car car : sortedCarList) {
            Integer carCountByType = carQuotaPerType.getOrDefault(car.getEngineType(), 0);
            if (carCountByType < 2) {
                result.add(car);
                carQuotaPerType.put(car.getEngineType(), carCountByType + 1);
            }
            if (result.size() == 5) {
                return result;
            }
        }
        return result;
    }
}
