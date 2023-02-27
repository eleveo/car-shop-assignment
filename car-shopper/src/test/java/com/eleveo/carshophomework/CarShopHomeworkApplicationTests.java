package com.eleveo.carshophomework;

import com.eleveo.carshophomework.shopper.CarShopper;
import com.eleveo.carshophomework.shopper.DummyCarShopper;
import com.eleveo.carshophomework.shopper.SelectiveCarShopper;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest
@WireMockTest(httpPort = 8444)
class CarShopHomeworkApplicationTests {

	private static final String BASE_URL = "http://localhost:8444/";
	CarShopper carShopper = new DummyCarShopper();

	CarShopper selectiveCarShopper = new SelectiveCarShopper();

	@Test
	void singleCarOrder() {
		stubFor(get("/cars").willReturn(ok()
				.withHeader("Content-Type", "application/json")
				.withBodyFile("single-car.json")
		));
		stubFor(post("/orders").willReturn(noContent()));
		carShopper.selectAndBuyCars(BASE_URL);
		verify(postRequestedFor(urlPathEqualTo("/orders"))
				.withRequestBody(equalToJson("{\"carId\": \"79c7faaf-d0af-304e-94aa-6e426ae1d07a\"}")));

	}

	@Test
	void selectiveCarOrder() {
		stubFor(get("/cars").willReturn(ok()
				.withHeader("Content-Type", "application/json")
				.withBodyFile("cars.json")
		));
		stubFor(post("/orders").willReturn(noContent()));
		selectiveCarShopper.selectAndBuyCars(BASE_URL);
		verify(5, postRequestedFor(urlEqualTo("/orders")));
		verify(postRequestedFor(urlPathEqualTo("/orders"))
				.withRequestBody(equalToJson("{\"carId\": \"23c49169-c73a-328e-9b92-d5018d8a235e\"}")));
		verify(postRequestedFor(urlPathEqualTo("/orders"))
				.withRequestBody(equalToJson("{\"carId\": \"29b64088-2805-368f-b50a-b35b7a6b9655\"}")));
		verify(postRequestedFor(urlPathEqualTo("/orders"))
				.withRequestBody(equalToJson("{\"carId\": \"94733846-bfa6-3aaf-8b1d-199a1dc187b7\"}")));
		verify(postRequestedFor(urlPathEqualTo("/orders"))
				.withRequestBody(equalToJson("{\"carId\": \"c5e70584-9505-3acb-a23a-1891fec4d223\"}")));
		verify(postRequestedFor(urlPathEqualTo("/orders"))
				.withRequestBody(equalToJson("{\"carId\": \"26a2664c-a44f-3b86-91d5-bbd4cf3cdda0\"}")));
	}

}
