package com.eleveo.carshophomework;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@WireMockTest(httpPort = 8444)
class CarShopHomeworkApplicationTests {

    private WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:8444/")
            .build();

    @Test
    void contextLoads() {
        stubFor(get("/cars").willReturn(ok()));
        ClientResponse response = webClient.get()
                .uri("/cars")
                .exchange()
                .block();
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK);
    }

}
