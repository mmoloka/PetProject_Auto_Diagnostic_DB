package org.example.petProject.api;

import org.example.petProject.model.ConstructionGroup;
import org.example.petProject.service.ConstructionGroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * Класс обрабатывает HTTP-запросы от клиентов и вызывает соответствующие методы сервиса
 * {@link org.example.petProject.service.ConstructionGroupServiceImpl}
 *
 * @author Молоканов Михаил
 * @version 0.0.1-SNAPSHOT
 */
@RestController
@RequestMapping("/group")
public class ConstructionGroupController {

    private final ConstructionGroupServiceImpl groupService;

    @Autowired
    public ConstructionGroupController(ConstructionGroupServiceImpl groupService) {
        this.groupService = groupService;
    }

    /**
     * Метод обрабатывает GET-запрос на получение всех JSON объектов - конструкционных групп
     *
     * @return JSON объекты - конструкционные группы и статус 200 ОК, либо статус 404 Not Found
     */
    @GetMapping
    public ResponseEntity<List<ConstructionGroup>> getAllConstructionGroups() {
        final List<ConstructionGroup> groups = groupService.getAllConstructionGroups();
        if (!groups.equals(Collections.emptyList())) {
            return new ResponseEntity<>(groups, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Метод обрабатывает GET-запрос на получение JSON объекта - конструкционная группа
     * по ее номеру в таблице базы данных
     *
     * @param id номер конструкционной группы в таблице базы данных
     * @return JSON объект - конструкционная группа и статус 200 ОК, либо статус 404 Not Found
     */
    @GetMapping("/{id}")
    public ResponseEntity<ConstructionGroup> getConstructionGroupById(@PathVariable Long id) {
        final ConstructionGroup group = groupService.getConstructionGroupById(id);
        if (group != null) {
            return new ResponseEntity<>(group, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Метод обрабатывает POST-запрос на добавление объекта - конструкционная группа
     *
     * @param constructionGroup JSON объект - добавляемая конструкционная группа
     * @return JSON объект - добавленная конструкционная группа и статус 201 Created, либо статус 400 Bad Request
     */
    @PostMapping
    public ResponseEntity<ConstructionGroup> createConstructionGroup(@RequestBody ConstructionGroup constructionGroup) {
        try {
            return new ResponseEntity<>(groupService.createConstructionGroup(constructionGroup), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Метод обрабатывает PUT-запрос на изменение объекта - конструкционная группа по ее номеру в таблице базы данных
     *
     * @param id                номер конструкционной группы в таблице базы данных
     * @param constructionGroup JSON объект - измененная конструкционная группа
     * @return JSON объект - измененная конструкционная группа и статус 200 ОК, либо статус 404 Not Found
     */
    @PutMapping("/{id}")
    public ResponseEntity<ConstructionGroup> updateConstructionGroup(
            @PathVariable Long id, @RequestBody ConstructionGroup constructionGroup) {
        final ConstructionGroup updatedGroup = groupService.updateConstructionGroup(id, constructionGroup);
        if (updatedGroup != null) {
            return new ResponseEntity<>(updatedGroup, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Метод обрабатывает DELETE-запрос на удаление объекта - конструкционная группа по ее номеру в таблице базы данных
     *
     * @param id номер конструкционной группы в таблице базы данных
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConstructionGroup(@PathVariable Long id) {
        try {
            groupService.deleteConstructionGroup(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Метод обрабатывает GET-запрос на получение JSON объекта - конструкционная группа по ее номеру
     *
     * @param groupNumber номер конструкционной группы
     * @return JSON объект - конструкционная группа и статус 200 ОК, либо статус 404 Not Found
     */
    @GetMapping("/num/{groupNumber}")
    public ResponseEntity<ConstructionGroup> getConstructionGroupByGroupNumber(@PathVariable int groupNumber) {
        final ConstructionGroup group = groupService.getConstructionGroupByGroupNumber(groupNumber);
        if (group != null) {
            return new ResponseEntity<>(group, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
