package org.example.petProject.repository;

import org.example.petProject.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс, наследующий базовый интерфейс репозитория Spring Data JPA для
 * обеспечения CRUD операций класса {@link org.example.petProject.model.Car}
 *
 * @author Молоканов Михаил
 * @version 0.0.1-SNAPSHOT
 */
@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    /**
     * Метод позволяет найти объект - автомобиль в базе данных по его идентификационному номеру
     *
     * @param vin идентификационный номер автомобиля
     * @return объект - искомый автомобиль
     */
    Car findCarByVin(String vin);

    /**
     * Метод позволяет найти объект - автомобиль в базе данных по его государственному номеру
     *
     * @param registrationNumber государственный номер автомобиля
     * @return объект - искомый автомобиль
     */
    Car findCarByRegistrationNumber(String registrationNumber);

    /**
     * Метод позволяет найти объект - автомобиль в базе данных по фамилии его владельца
     *
     * @param ownerName фамилия владельца автомобиля
     * @return объект - искомый автомобиль
     */
    Car findCarByOwnerName(String ownerName);
}
