package org.example.petProject.api;

import org.example.petProject.model.Car;
import org.example.petProject.service.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * Класс обрабатывает HTTP-запросы от клиентов и вызывает соответствующие методы сервиса
 * {@link org.example.petProject.service.CarServiceImpl}
 *
 * @author Молоканов Михаил
 * @version 0.0.1-SNAPSHOT
 */
@RestController
@RequestMapping("/car")
public class CarController {

    private final CarServiceImpl carService;

    @Autowired
    public CarController(CarServiceImpl carService) {
        this.carService = carService;
    }

    /**
     * Метод обрабатывает GET-запрос на получение всех JSON объектов - автомобилей
     *
     * @return JSON объекты - автомобили и статус 200 ОК, либо статус 404 Not Found
     */
    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        final List<Car> cars = carService.getAllCars();
        if (!cars.equals(Collections.emptyList())) {
            return new ResponseEntity<>(cars, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Метод обрабатывает GET-запрос на получение JSON объекта - автомобиль по его номеру в таблице базы данных
     *
     * @param id номер автомобиля в таблице базы данных
     * @return JSON объект - автомобиль и статус 200 ОК, либо статус 404 Not Found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        final Car car = carService.getCarById(id);
        if (car != null) {
            return new ResponseEntity<>(car, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Метод обрабатывает POST-запрос на добавление объекта - автомобиль
     *
     * @param car JSON объект - добавляемый автомобиль
     * @return JSON объект - добавленный автомобиль и статус 201 Created, либо статус 400 Bad Request
     */
    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        try {
            return new ResponseEntity<>(carService.createCar(car), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Метод обрабатывает PUT-запрос на изменение объекта - автомобиль по его номеру в таблице базы данных
     *
     * @param id  номер автомобиля в таблице базы данных
     * @param car JSON объект - измененный автомобиль
     * @return JSON объект - измененный автомобиль и статус 200 ОК, либо статус 404 Not Found
     */
    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car car) {
        final Car updatedCar = carService.updateCar(id, car);
        if (updatedCar != null) {
            return new ResponseEntity<>(updatedCar, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Метод обрабатывает DELETE-запрос на удаление объекта - автомобиль по его номеру в таблице базы данных
     *
     * @param id номер автомобиля в таблице базы данных
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        try {
            carService.deleteCar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Метод обрабатывает GET-запрос на получение JSON объекта - автомобиль по его идентификационному номеру
     *
     * @param vin идентификационный номер автомобиля
     * @return JSON объект - автомобиль и статус 200 ОК, либо статус 404 Not Found
     */
    @GetMapping("/vin/{vin}")
    public ResponseEntity<Car> getCarByVin(@PathVariable String vin) {
        final Car car = carService.getCarByVin(vin);
        if (car != null) {
            return new ResponseEntity<>(car, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Метод обрабатывает GET-запрос на получение JSON объекта - автомобиль по его государственному номеру
     *
     * @param registrationNumber государственный номер автомобиля
     * @return JSON объект - автомобиль и статус 200 ОК, либо статус 404 Not Found
     */
    @GetMapping("/num/{registrationNumber}")
    public ResponseEntity<Car> getCarByRegistrationNumber(@PathVariable String registrationNumber) {
        final Car car = carService.getCarByRegistrationNumber(registrationNumber);
        if (car != null) {
            return new ResponseEntity<>(car, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Метод обрабатывает GET-запрос на получение JSON объекта - автомобиль по фамилии его владельца
     *
     * @param ownerName фамилия владельца автомобиля
     * @return JSON объект - автомобиль и статус 200 ОК, либо статус 404 Not Found
     */
    @GetMapping("/own/{ownerName}")
    public ResponseEntity<Car> getCarByOwnerName(@PathVariable String ownerName) {
        final Car car = carService.getCarByOwnerName(ownerName);
        if (car != null) {
            return new ResponseEntity<>(car, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
