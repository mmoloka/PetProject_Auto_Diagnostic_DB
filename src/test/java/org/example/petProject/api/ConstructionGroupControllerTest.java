package org.example.petProject.api;

import org.example.petProject.JUnitSpringBootBase;
import org.example.petProject.model.ConstructionGroup;
import org.example.petProject.repository.ConstructionGroupRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.Objects;

class ConstructionGroupControllerTest extends JUnitSpringBootBase {
    @Autowired
    WebTestClient webTestClient;
    @Autowired
    ConstructionGroupRepository groupRepository;

    @Test
    void testGetAllConstructionGroupsOK() {
        List<ConstructionGroup> expected = groupRepository.saveAll(List.of(
                new ConstructionGroup(7, "Смесеобразование"),
                new ConstructionGroup(54, "Электрооборудование внутреннее"),
                new ConstructionGroup(82, "Электрооборудование наружнее")
        ));

        List<ConstructionGroup> responseBody = webTestClient.get()
                .uri("/group")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<ConstructionGroup>>() {
                })
                .returnResult().getResponseBody();

        Assertions.assertEquals(expected.size(), Objects.requireNonNull(responseBody).size());
        for (ConstructionGroup constructionGroup : responseBody) {
            boolean found = expected.stream()
                    .filter(it -> Objects.equals(it.getId(), constructionGroup.getId()))
                    .filter(it -> Objects.equals(it.getGroupNumber(), constructionGroup.getGroupNumber()))
                    .allMatch(it -> Objects.equals(it.getGroupName(), constructionGroup.getGroupName()));
            Assertions.assertTrue(found);
        }
    }

    @Test
    void testGetAllConstructionGroupsNotFound() {
        webTestClient.get()
                .uri("/group")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testGetConstructionGroupByIdOK() {
        ConstructionGroup expected = groupRepository.save(new ConstructionGroup(72, "Двери"));

        ConstructionGroup responseBody = webTestClient.get()
                .uri("/group/" + expected.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(ConstructionGroup.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertEquals(expected.getId(), responseBody.getId());
        Assertions.assertEquals(expected.getGroupNumber(), responseBody.getGroupNumber());
        Assertions.assertEquals(expected.getGroupName(), responseBody.getGroupName());
    }

    @Test
    void testGetConstructionGroupByIdNotFound() {
        webTestClient.get()
                .uri("/group/0")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testCreateConstructionGroupOK() {
        ConstructionGroup request = new ConstructionGroup(80, "Центральное запирание");

        ConstructionGroup responseBody = webTestClient.post()
                .uri("/group")
                .bodyValue(request)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(ConstructionGroup.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertNotNull(responseBody.getId());
        Assertions.assertTrue(groupRepository.findById(responseBody.getId()).isPresent());
        Assertions.assertEquals(responseBody.getGroupNumber(), request.getGroupNumber());
        Assertions.assertEquals(responseBody.getGroupName(), request.getGroupName());
    }

    @Test
    void testCreateConstructionGroupBadRequest() {
        webTestClient.post()
                .uri("/group")
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testUpdateConstructionGroupOK() {
        ConstructionGroup request = new ConstructionGroup(83, "Кондиционирование");
        ConstructionGroup group = groupRepository.save(new ConstructionGroup());

        ConstructionGroup responseBody = webTestClient.put()
                .uri("/group/" + group.getId())
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ConstructionGroup.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertEquals(responseBody.getId(), group.getId());
        Assertions.assertEquals(responseBody.getGroupNumber(), request.getGroupNumber());
        Assertions.assertEquals(responseBody.getGroupName(), request.getGroupName());
    }

    @Test
    void testUpdateConstructionGroupNotFound() {
        ConstructionGroup request = new ConstructionGroup();

        webTestClient.put()
                .uri("/group/0")
                .bodyValue(request)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDeleteConstructionGroup() {
        ConstructionGroup request = groupRepository.save(
                new ConstructionGroup(91, "Подушки безопасности"));

        webTestClient.delete()
                .uri("/group/" + request.getId())
                .exchange()
                .expectStatus().isNoContent();

        Assertions.assertFalse(groupRepository.findById(request.getId()).isPresent());
    }

    @Test
    void testGetConstructionGroupByGroupNumberOK() {
        ConstructionGroup expected = groupRepository.save(new ConstructionGroup(49, "Система выхлопа"));

        ConstructionGroup responseBody = webTestClient.get()
                .uri("/group/num/" + expected.getGroupNumber())
                .exchange()
                .expectStatus().isOk()
                .expectBody(ConstructionGroup.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertEquals(expected.getId(), responseBody.getId());
        Assertions.assertEquals(expected.getGroupNumber(), responseBody.getGroupNumber());
        Assertions.assertEquals(expected.getGroupName(), responseBody.getGroupName());
    }

    @Test
    void testGetConstructionGroupByGroupNumberNotFound() {
        webTestClient.get()
                .uri("/group/num/-1")
                .exchange()
                .expectStatus().isNotFound();
    }

    @BeforeEach
    void cleanUpDataBase() {
        groupRepository.deleteAll();
    }
}