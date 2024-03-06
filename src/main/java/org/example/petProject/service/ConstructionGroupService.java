package org.example.petProject.service;

import org.example.petProject.model.ConstructionGroup;

import java.util.List;

/**
 * Интерфейс служит для выполнения CRUD операций
 * используя репозиторий {@link org.example.petProject.repository.ConstructionGroupRepository}
 *
 * @author Молоканов Михаил
 * @version 0.0.1-SNAPSHOT
 */
public interface ConstructionGroupService {
    /**
     * Метод позволяет получить все объекты - конструкционные группы репозитория
     *
     * @return объекты - искомые конструкционные группы
     */
    List<ConstructionGroup> getAllConstructionGroups();

    /**
     * Метод позволяет получить объект - конструкционная группа репозитория по ее номеру в таблице базы данных
     *
     * @param id номер конструкционной группы в таблице базы данных
     * @return объект - искомая конструкционная группа
     */
    ConstructionGroup getConstructionGroupById(Long id);

    /**
     * Метод позволяет добавить объект - конструкционная группа в репозиторий
     *
     * @param constructionGroup добавляемая конструкционная группа
     * @return объект - добавляемая конструкционная группа
     */
    ConstructionGroup createConstructionGroup(ConstructionGroup constructionGroup);

    /**
     * Метод позволяет изменить объект - конструкционная группа репозитория по ее номеру в таблице базы данных
     *
     * @param id                номер конструкционной группы в таблице базы данных
     * @param constructionGroup измененная конструкционная группа
     * @return объект - измененная конструкционная группа
     */
    ConstructionGroup updateConstructionGroup(Long id, ConstructionGroup constructionGroup);

    /**
     * Метод позволяет удалить объект - конструкционная группа репозитория по ее номеру в таблице базы данных
     *
     * @param id номер конструкционной группы в таблице базы данных
     */
    void deleteConstructionGroup(Long id);

    /**
     * Метод позволяет получить объект - конструкционная группа в базе данных по ее номеру
     *
     * @param groupNumber номер конструкционной группы
     * @return объект - искомая конструкционная группа
     */
    ConstructionGroup getConstructionGroupByGroupNumber(int groupNumber);
}
