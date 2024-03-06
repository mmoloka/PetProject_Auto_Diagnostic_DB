package org.example.petProject.repository;

import org.example.petProject.model.ConstructionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс, наследующий базовый интерфейс репозитория Spring Data JPA для
 * обеспечения CRUD операций класса {@link org.example.petProject.model.ConstructionGroup}
 *
 * @author Молоканов Михаил
 * @version 0.0.1-SNAPSHOT
 */
@Repository
public interface ConstructionGroupRepository extends JpaRepository<ConstructionGroup, Long> {
    /**
     * Метод позволяет найти объект - конструкционная группа в базе данных по ее номеру
     *
     * @param groupNumber номер конструкционной группы
     * @return объект - искомая конструкционная группа
     */
    ConstructionGroup findConstructionGroupByGroupNumber(int groupNumber);
}
