package com.eleveo.carshophomework;

import com.eleveo.carshophomework.shopper.CarShopper;
import com.eleveo.carshophomework.shopper.DummyCarShopper;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.noContent;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;

@SpringBootTest
@WireMockTest(httpPort = 8444)
class CarShopHomeworkApplicationTests {

	private static final String BASE_URL = "http://localhost:8444/";
	CarShopper carShopper = new DummyCarShopper();

	//language=json
	String car = """
			{
			  "cars": [
			    {
			      "carId": "822ac97f-1202-4399-95f5-a4a4a183f20a",
			      "manufacturer": "Tesla",
			      "model": "Model X Plaid",
			      "engineType": "ELECTRIC",
			      "powerWatts": 373746,
			      "heightMeters": 1.1,
			      "widthMeters": 2.1,
			      "lengthMeters": 5.5,
			      "price": 299000,
			      "priceCurrency": "USD"
			    }
			  ]
			}
					""";

	@Test
	void singleCarOrder() {
		stubFor(get("/cars").willReturn(okJson(car)));
		stubFor(post("/orders").willReturn(noContent()));
		carShopper.selectAndBuyCars(BASE_URL);
		verify(postRequestedFor(urlPathEqualTo("/orders"))
				.withRequestBody(equalToJson("{\"carId\": \"822ac97f-1202-4399-95f5-a4a4a183f20a\"}")));

	}

}
