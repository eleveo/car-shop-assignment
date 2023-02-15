package com.eleveo.carshophomework.shopper;

import com.eleveo.carshop.client.ApiClient;
import com.eleveo.carshop.client.DefaultApi;
import com.eleveo.carshop.client.model.Car;
import com.eleveo.carshop.client.model.Order;

import java.util.UUID;

public class DummyCarShopper implements CarShopper {

	@Override
	public void selectAndBuyCars(String serverUri) {
		//TODO implement better
		DefaultApi defaultApi = new DefaultApi(new ApiClient().setBasePath(serverUri));
		Car car = defaultApi.getCars().block().getCars().get(0);
		Order order = new Order();
		order.setCarId(car.getCarId());
		defaultApi.orderCar(order).block();
	}
}
