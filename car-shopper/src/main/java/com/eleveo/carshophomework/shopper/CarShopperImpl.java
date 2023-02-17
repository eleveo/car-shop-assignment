package com.eleveo.carshophomework.shopper;

import com.eleveo.carshop.client.ApiClient;
import com.eleveo.carshop.client.DefaultApi;
import com.eleveo.carshop.client.model.Car;
import com.eleveo.carshop.client.model.CarsList;
import com.eleveo.carshop.client.model.Order;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class CarShopperImpl implements CarShopper {
    private static final Comparator<Car> CARS_BY_POWER_COMPARATOR = Comparator
            .comparingInt(Car::getPowerWatts)
            .reversed();
    private static final int CARS_AMOUNT_TO_ORDER = 5;
    private static final int CARS_AMOUNT_PER_ENGINE_TO_ORDER = 2;

    @Override
    public void selectAndBuyCars(String serverUri) {
        DefaultApi api = new DefaultApi(new ApiClient().setBasePath(serverUri));

        Map<Car.EngineTypeEnum, List<Car>> carsByEngineTypeMap = this.retrieveCarsByEngineType(api);
        Set<Car> carsToOrder = this.getTopFiveCarsByPower(carsByEngineTypeMap);
        this.sendOrderForCars(carsToOrder, api);
    }

    /**
     * Retrieves all cars to iterate by and grouping them by {@link Car.EngineTypeEnum engine type.}
     */
    private Map<Car.EngineTypeEnum, List<Car>> retrieveCarsByEngineType(DefaultApi api) {
        return api.getCars()
                .flux()
                .map(CarsList::getCars)
                .toStream()
                .flatMap(Collection::stream)
                .sorted(CARS_BY_POWER_COMPARATOR)
                .collect(Collectors.groupingBy(Car::getEngineType));
    }

    /**
     * Returns top 5 cars by the engine power.
     * There is restriction by the amount of {@link Car.EngineTypeEnum engine type.}
     * Each engine type should be included in the result at most 2 times.
     *
     * @return set with top 5 cars by engine power with the described restrictions.
     */
    private Set<Car> getTopFiveCarsByPower(Map<Car.EngineTypeEnum, List<Car>> carsByEngineTypeMap) {
        TreeSet<Car> resultingCars = new TreeSet<>(CARS_BY_POWER_COMPARATOR);
        for (Car.EngineTypeEnum value : Car.EngineTypeEnum.values()) {
            List<Car> carsByEngine = carsByEngineTypeMap.get(value);
            if (CollectionUtils.isEmpty(carsByEngine)) {
                continue;
            }
            List<Car> topCarsByEngine = carsByEngine.size() >= CARS_AMOUNT_PER_ENGINE_TO_ORDER
                    ? carsByEngine.subList(0, CARS_AMOUNT_PER_ENGINE_TO_ORDER)
                    : carsByEngine;
            for (Car car : topCarsByEngine) {
                if (resultingCars.size() < CARS_AMOUNT_TO_ORDER) {
                    resultingCars.add(car);
                } else if (resultingCars.last().getPowerWatts() < car.getPowerWatts()) {
                    resultingCars.remove(resultingCars.last());
                    resultingCars.add(car);
                }
            }
        }
        return resultingCars;
    }

    private void sendOrderForCars(Set<Car> carsToOrder, DefaultApi api) {
        for (Car resultingCar : carsToOrder) {
            Order order = new Order();
            order.setCarId(resultingCar.getCarId());
            api.orderCarWithHttpInfo(order).block(Duration.of(1, ChronoUnit.SECONDS));
        }
    }
}
