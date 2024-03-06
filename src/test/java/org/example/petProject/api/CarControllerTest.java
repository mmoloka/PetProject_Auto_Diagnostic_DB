package org.example.petProject.api;

import org.example.petProject.JUnitSpringBootBase;
import org.example.petProject.model.Car;
import org.example.petProject.repository.CarRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.Objects;


@ActiveProfiles(value = "IntegrationTest")
class CarControllerTest extends JUnitSpringBootBase {

    @Autowired
    WebTestClient webTestClient;
    @Autowired
    CarRepository carRepository;

    @Test
    void testGetAllCarsOK() {
        List<Car> expected = carRepository.saveAll(List.of(
                new Car("WDC1668821A123456", "A001AA97", "Иванов"),
                new Car("WDD17604221J123456", "A002AA97", "Петров"),
                new Car("WDC1660451A123456", "A003AA97", "Сидоров")
        ));

        List<Car> responseBody = webTestClient.get()
                .uri("/car")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<Car>>() {
                })
                .returnResult().getResponseBody();

        Assertions.assertEquals(expected.size(), Objects.requireNonNull(responseBody).size());
        for (Car car : responseBody) {
            boolean found = expected.stream()
                    .filter(it -> Objects.equals(it.getId(), car.getId()))
                    .filter(it -> Objects.equals(it.getVin(), car.getVin()))
                    .filter(it -> Objects.equals(it.getRegistrationNumber(), car.getRegistrationNumber()))
                    .allMatch(it -> Objects.equals(it.getOwnerName(), car.getOwnerName()));
            Assertions.assertTrue(found);
        }
    }

    @Test
    void testGetAllCarsNotFound() {
        webTestClient.get()
                .uri("/car")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testGetCarByIdOK() {
        Car expected = carRepository.save(
                new Car("WDD1692331J123456", "C007CC97", "Печкин"));

        Car responseBody = webTestClient.get()
                .uri("/car/" + expected.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Car.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertEquals(expected.getId(), responseBody.getId());
        Assertions.assertEquals(expected.getVin(), responseBody.getVin());
        Assertions.assertEquals(expected.getRegistrationNumber(), responseBody.getRegistrationNumber());
        Assertions.assertEquals(expected.getOwnerName(), responseBody.getOwnerName());
    }

    @Test
    void testGetCarByIdNotFound() {
        webTestClient.get()
                .uri("/car/0")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testCreateCarOK() {
        Car request = new Car("WDD2452331J123456", "C008CC97", "Пупкин");

        Car responseBody = webTestClient.post()
                .uri("/car")
                .bodyValue(request)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Car.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertNotNull(responseBody.getId());
        Assertions.assertTrue(carRepository.findById(responseBody.getId()).isPresent());
        Assertions.assertEquals(responseBody.getVin(), request.getVin());
        Assertions.assertEquals(responseBody.getRegistrationNumber(), request.getRegistrationNumber());
        Assertions.assertEquals(responseBody.getOwnerName(), request.getOwnerName());
    }

    @Test
    void testCreateCarBadRequest() {
        webTestClient.post()
                .uri("/car")
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testUpdateCarOK() {
        Car request = new Car("WDD2452331J654321", "C009CC97", "Васин");
        Car car = carRepository.save(new Car());

        Car responseBody = webTestClient.put()
                .uri("/car/" + car.getId())
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Car.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertEquals(responseBody.getId(), car.getId());
        Assertions.assertEquals(responseBody.getVin(), request.getVin());
        Assertions.assertEquals(responseBody.getRegistrationNumber(), request.getRegistrationNumber());
        Assertions.assertEquals(responseBody.getOwnerName(), request.getOwnerName());
    }

    @Test
    void testUpdateCarNotFound() {
        Car request = new Car();

        webTestClient.put()
                .uri("/car/0")
                .bodyValue(request)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDeleteCar() {
        Car request = carRepository.save(
                new Car("WDD1760321J654321", "C010CC97", "Ванин"));

        webTestClient.delete()
                .uri("/car/" + request.getId())
                .exchange()
                .expectStatus().isNoContent();

        Assertions.assertFalse(carRepository.findById(request.getId()).isPresent());
    }

    @Test
    void testGetCarByVinOK() {
        Car expected = carRepository.save(
                new Car("WDD1692331J654321", "C011CC97", "Пенкин"));

        Car responseBody = webTestClient.get()
                .uri("/car/vin/" + expected.getVin())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Car.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertEquals(expected.getId(), responseBody.getId());
        Assertions.assertEquals(expected.getVin(), responseBody.getVin());
        Assertions.assertEquals(expected.getRegistrationNumber(), responseBody.getRegistrationNumber());
        Assertions.assertEquals(expected.getOwnerName(), responseBody.getOwnerName());
    }

    @Test
    void testGetCarByVinNotFound() {
        webTestClient.get()
                .uri("/car/vin/ABC")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testGetCarByRegistrationNumberOK() {
        Car expected = carRepository.save(
                new Car("WDD2462331J654321", "C012CC97", "Чикин"));

        Car responseBody = webTestClient.get()
                .uri("/car/num/" + expected.getRegistrationNumber())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Car.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertEquals(expected.getId(), responseBody.getId());
        Assertions.assertEquals(expected.getVin(), responseBody.getVin());
        Assertions.assertEquals(expected.getRegistrationNumber(), responseBody.getRegistrationNumber());
        Assertions.assertEquals(expected.getOwnerName(), responseBody.getOwnerName());
    }

    @Test
    void testGetCarByRegistrationNumberNotFound() {
        webTestClient.get()
                .uri("/car/num/123")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testGetCarByOwnerNameOK() {
        Car expected = carRepository.save(
                new Car("WDD1762331J654321", "C013CC97", "Аськин"));

        Car responseBody = webTestClient.get()
                .uri("/car/own/" + expected.getOwnerName())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Car.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertEquals(expected.getId(), responseBody.getId());
        Assertions.assertEquals(expected.getVin(), responseBody.getVin());
        Assertions.assertEquals(expected.getRegistrationNumber(), responseBody.getRegistrationNumber());
        Assertions.assertEquals(expected.getOwnerName(), responseBody.getOwnerName());
    }

    @Test
    void testGetCarByOwnerNameNotFound() {
        webTestClient.get()
                .uri("/car/own/123")
                .exchange()
                .expectStatus().isNotFound();
    }

    @BeforeEach
    void cleanUpDataBase() {
        carRepository.deleteAll();
    }
}