package org.example.petProject.api;

import org.example.petProject.JUnitSpringBootBase;
import org.example.petProject.model.Car;
import org.example.petProject.model.ConstructionGroup;
import org.example.petProject.model.RepairData;
import org.example.petProject.repository.CarRepository;
import org.example.petProject.repository.ConstructionGroupRepository;
import org.example.petProject.repository.RepairDataRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

class RepairDataControllerTest extends JUnitSpringBootBase {
    @Autowired
    WebTestClient webTestClient;
    @Autowired
    RepairDataRepository repairRepository;
    @Autowired
    CarRepository carRepository;
    @Autowired
    ConstructionGroupRepository groupRepository;

    @Test
    void testGetAllRepairDataOK() {
        List<RepairData> expected = repairRepository.saveAll(List.of(
                new RepairData(
                        carRepository.findById(1L).orElse(null),
                        groupRepository.findById(1L).orElse(null),
                        "Требуется...", "Шпунтиков", LocalDate.now()),
                new RepairData(
                        carRepository.findById(4L).orElse(null),
                        groupRepository.findById(7L).orElse(null),
                        "Требуется...", "Винтиков", LocalDate.now()),
                new RepairData(
                        carRepository.findById(6L).orElse(null),
                        groupRepository.findById(11L).orElse(null),
                        "Требуется...", "Торопунькин", LocalDate.now())
        ));

        List<RepairData> responseBody = webTestClient.get()
                .uri("/repair")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<RepairData>>() {
                })
                .returnResult().getResponseBody();

        Assertions.assertEquals(expected.size(), Objects.requireNonNull(responseBody).size());
        for (RepairData repairData : responseBody) {
            boolean found = expected.stream()
                    .filter(it -> Objects.equals(it.getId(), repairData.getId()))
                    .filter(it -> Objects.equals(it.getCar(), repairData.getCar()))
                    .filter(it -> Objects.equals(it.getGroup(), repairData.getGroup()))
                    .filter(it -> Objects.equals(it.getRepairReport(), repairData.getRepairReport()))
                    .filter(it -> Objects.equals(it.getExecutorName(), repairData.getExecutorName()))
                    .allMatch(it -> Objects.equals(it.getRepairDate(), repairData.getRepairDate()));
            Assertions.assertTrue(found);
        }
    }

    @Test
    void testGetAllRepairDataNotFound() {
        webTestClient.get()
                .uri("/repair")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testGetRepairDataByIdOK() {
        RepairData expected = repairRepository.save(
                new RepairData(
                        carRepository.findById(1L).orElse(null),
                        groupRepository.findById(7L).orElse(null),
                        "Требуется...", "Шпунтиков", LocalDate.now()));

        RepairData responseBody = webTestClient.get()
                .uri("/repair/" + expected.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(RepairData.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertEquals(expected.getId(), responseBody.getId());
        Assertions.assertEquals(expected.getCar(), responseBody.getCar());
        Assertions.assertEquals(expected.getGroup(), responseBody.getGroup());
        Assertions.assertEquals(expected.getRepairReport(), responseBody.getRepairReport());
        Assertions.assertEquals(expected.getExecutorName(), responseBody.getExecutorName());
        Assertions.assertEquals(expected.getRepairDate(), responseBody.getRepairDate());
    }

    @Test
    void testGetRepairDataByIdNotFound() {
        webTestClient.get()
                .uri("/repair/0")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testCreateRepairDataOK() {
        RepairData request = new RepairData(
                carRepository.findById(1L).orElse(null),
                groupRepository.findById(12L).orElse(null),
                "Требуется...", "Винтиков", LocalDate.now());

        RepairData responseBody = webTestClient.post()
                .uri("/repair")
                .bodyValue(request)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(RepairData.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertNotNull(responseBody.getId());
        Assertions.assertTrue(repairRepository.findById(responseBody.getId()).isPresent());
        Assertions.assertEquals(responseBody.getCar(), request.getCar());
        Assertions.assertEquals(responseBody.getGroup(), request.getGroup());
        Assertions.assertEquals(responseBody.getRepairReport(), request.getRepairReport());
        Assertions.assertEquals(responseBody.getExecutorName(), request.getExecutorName());
        Assertions.assertEquals(responseBody.getRepairDate(), request.getRepairDate());
    }

    @Test
    void testCreateRepairDataNotFound() {
        webTestClient.post()
                .uri("/repair")
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testUpdateRepairDataOK() {
        RepairData request = new RepairData(
                carRepository.findById(4L).orElse(null),
                groupRepository.findById(7L).orElse(null),
                "Требуется...", "Винтиков", LocalDate.now());
        RepairData repair = repairRepository.save(new RepairData());

        RepairData responseBody = webTestClient.put()
                .uri("/repair/" + repair.getId())
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody(RepairData.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertEquals(responseBody.getId(), repair.getId());
        Assertions.assertEquals(responseBody.getCar(), request.getCar());
        Assertions.assertEquals(responseBody.getGroup(), request.getGroup());
        Assertions.assertEquals(responseBody.getRepairReport(), request.getRepairReport());
        Assertions.assertEquals(responseBody.getExecutorName(), request.getExecutorName());
        Assertions.assertEquals(responseBody.getRepairDate(), request.getRepairDate());
    }

    @Test
    void testUpdateRepairDataNotFound() {
        RepairData request = new RepairData();

        webTestClient.put()
                .uri("/repair/0")
                .bodyValue(request)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDeleteRepairData() {
        RepairData request = repairRepository.save(
                new RepairData(
                        carRepository.findById(4L).orElse(null),
                        groupRepository.findById(7L).orElse(null),
                        "Требуется...", "Торопунькин", LocalDate.now()));

        webTestClient.delete()
                .uri("/repair/" + request.getId())
                .exchange()
                .expectStatus().isNoContent();

        Assertions.assertFalse(repairRepository.findById(request.getId()).isPresent());
    }

    @Test
    void testGetRepairDataByCarVinOK() {
        Car car = carRepository.save(new Car("WDC1668821A123456", "A001AA97", "Иванов"));
        List<RepairData> expected = repairRepository.saveAll(List.of(
                new RepairData(car, groupRepository.findById(5L).orElse(null),
                        "Требуется...", "Пупкин", LocalDate.now()),
                new RepairData(car, groupRepository.findById(8L).orElse(null),
                        "Требуется...", "Пипкин", LocalDate.now()),
                new RepairData(car, groupRepository.findById(9L).orElse(null),
                        "Требуется...", "Папкин", LocalDate.now())
        ));

        List<RepairData> responseBody = webTestClient.get()
                .uri("/repair/vin/" + car.getVin())
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<RepairData>>() {
                })
                .returnResult().getResponseBody();

        Assertions.assertEquals(expected.size(), Objects.requireNonNull(responseBody).size());
        for (RepairData repairData : responseBody) {
            boolean found = expected.stream()
                    .filter(it -> Objects.equals(it.getId(), repairData.getId()))
                    .filter(it -> Objects.equals(it.getCar(), repairData.getCar()))
                    .filter(it -> Objects.equals(it.getGroup(), repairData.getGroup()))
                    .filter(it -> Objects.equals(it.getRepairReport(), repairData.getRepairReport()))
                    .filter(it -> Objects.equals(it.getExecutorName(), repairData.getExecutorName()))
                    .allMatch(it -> Objects.equals(it.getRepairDate(), repairData.getRepairDate()));
            Assertions.assertTrue(found);
        }
    }

    @Test
    void testGetRepairDataByCarVinNotFound() {
        webTestClient.get()
                .uri("/repair/vin/ABC")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testGetRepairDataByCarVinAndConstructionGroupNumberOK() {
        Car car = carRepository.save(new Car("WDC1668821A123456", "A001AA97", "Иванов"));
        ConstructionGroup group = groupRepository.save(new ConstructionGroup(72, "Двери"));
        List<RepairData> expected = repairRepository.saveAll(List.of(
                new RepairData(car, group, "Требуется...", "Акин", LocalDate.now()),
                new RepairData(car, group, "Требуется...", "Якин", LocalDate.now()),
                new RepairData(car, group, "Требуется...", "Малкин", LocalDate.now())
        ));

        List<RepairData> responseBody = webTestClient.get()
                .uri("/repair/vin/" + car.getVin() + "/group/" + group.getGroupNumber())
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<RepairData>>() {
                })
                .returnResult().getResponseBody();

        Assertions.assertEquals(expected.size(), Objects.requireNonNull(responseBody).size());
        for (RepairData repairData : responseBody) {
            boolean found = expected.stream()
                    .filter(it -> Objects.equals(it.getId(), repairData.getId()))
                    .filter(it -> Objects.equals(it.getCar(), repairData.getCar()))
                    .filter(it -> Objects.equals(it.getGroup(), repairData.getGroup()))
                    .filter(it -> Objects.equals(it.getRepairReport(), repairData.getRepairReport()))
                    .filter(it -> Objects.equals(it.getExecutorName(), repairData.getExecutorName()))
                    .allMatch(it -> Objects.equals(it.getRepairDate(), repairData.getRepairDate()));
            Assertions.assertTrue(found);
        }
    }

    @Test
    void testGetRepairDataByCarVinAndConstructionGroupNumberNotFound() {
        webTestClient.get()
                .uri("/repair/vin/ABC/group/-1")
                .exchange()
                .expectStatus().isNotFound();
    }

    @BeforeEach
    void cleanUpDataBase() {
        repairRepository.deleteAll();
        carRepository.deleteAll();
        groupRepository.deleteAll();
    }
}