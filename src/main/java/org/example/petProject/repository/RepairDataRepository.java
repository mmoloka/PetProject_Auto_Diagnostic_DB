package org.example.petProject.repository;

import org.example.petProject.model.RepairData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Интерфейс, наследующий базовый интерфейс репозитория Spring Data JPA для
 * обеспечения CRUD операций класса {@link org.example.petProject.model.RepairData}
 *
 * @author Молоканов Михаил
 * @version 0.0.1-SNAPSHOT
 */
@Repository
public interface RepairDataRepository extends JpaRepository<RepairData, Long> {
    /**
     * Метод позволяет найти все объекты - результаты диагностики автомобиля в базе данных
     * по его идентификационному номеру
     *
     * @param carVin идентификационный номер автомобиля
     * @return объекты - искомые результаты диагностики автомобиля
     */
    List<RepairData> findRepairDataByCarVin(String carVin);

    /**
     * Метод позволяет найти все объекты - результаты диагностики автомобиля в базе данных
     * по его идентификационному номеру и номеру конструкционной группы
     *
     * @param carVin                  идентификационный номер автомобиля
     * @param constructionGroupNumber номер конструкционной группы
     * @return объекты - искомые результаты диагностики автомобиля
     */
    @Query("select r from RepairData r where r.car.vin = ?1 and r.group.groupNumber = ?2")
    List<RepairData> findRepairDataByCarVinAndConstructionGroupNumber(String carVin, int constructionGroupNumber);
}
