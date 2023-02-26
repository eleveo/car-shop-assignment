package com.eleveo.carshophomework.shopper;

import com.eleveo.carshop.client.ApiClient;
import com.eleveo.carshop.client.DefaultApi;
import com.eleveo.carshop.client.model.Car;
import com.eleveo.carshop.client.model.CarsList;
import com.eleveo.carshop.client.model.Order;

import java.util.Comparator;
import java.util.EnumMap;
import java.util.Optional;


public class DummyCarShopper implements CarShopper {

	@Override
	public void selectAndBuyCars(String serverUri) {
		DefaultApi defaultApi = new DefaultApi(new ApiClient().setBasePath(serverUri));
		var markedCarsWithEngineType = new EnumMap<Car.EngineTypeEnum, Integer>(Car.EngineTypeEnum.class);
		defaultApi.getCars()
			.flatMapIterable(CarsList::getCars)
			.sort(Comparator.comparing(Car::getPowerWatts, Comparator.reverseOrder()))
			.filter(innerCar -> Optional.ofNullable(markedCarsWithEngineType.get(innerCar.getEngineType())).orElse(0) < 2)
			.doOnNext(innerCar -> markedCarsWithEngineType.put(innerCar.getEngineType(), Optional.ofNullable(markedCarsWithEngineType.get(innerCar.getEngineType())).orElse(0) + 1))
			.take(5)
			.map(selectedCar -> {
				var order = new Order();
				order.setCarId(selectedCar.getCarId());
				return order;
			})
			.doOnNext(defaultApi::orderCar)
			.subscribe();
	}
}
