package org.example.petProject.service;

import jakarta.annotation.PostConstruct;
import org.example.petProject.model.Car;
import org.example.petProject.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Класс реализует интерфейс {@link org.example.petProject.service.CarService}
 * с внедренной зависимостью интерфейса {@link org.example.petProject.repository.CarRepository}
 *
 * @author Молоканов Михаил
 * @version 0.0.1-SNAPSHOT
 */
@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    //Генерация данных тестирования в таблице "cars" базы данных после внедрения зависимостей
//    @PostConstruct
//    private void generateCarsData() {
//        carRepository.save(new Car("WDC1668821A123456", "A001AA97", "Иванов"));
//        carRepository.save(new Car("WDD17604221J123456", "A002AA97", "Петров"));
//        carRepository.save(new Car("WDC1660451A123456", "A003AA97", "Сидоров"));
//        carRepository.save(new Car("WDD2130411A123456", "A004AA97", "Гусев"));
//        carRepository.save(new Car("WDB4631771A123456", "A005AA97", "Лебедев"));
//        carRepository.save(new Car("WDD2051331A123456", "A006AA97", "Лосев"));
//    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Car getCarById(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    @Override
    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car updateCar(Long id, Car car) {
        Car existingCar = getCarById(id);
        if (existingCar != null) {
            existingCar.setVin(car.getVin());
            existingCar.setRegistrationNumber(car.getRegistrationNumber());
            existingCar.setOwnerName(car.getOwnerName());
            return carRepository.save(existingCar);
        }
        return null;
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public Car getCarByVin(String vin) {
        return carRepository.findCarByVin(vin);
    }

    @Override
    public Car getCarByRegistrationNumber(String registrationNumber) {
        return carRepository.findCarByRegistrationNumber(registrationNumber);
    }

    @Override
    public Car getCarByOwnerName(String ownerName) {
        return carRepository.findCarByOwnerName(ownerName);
    }
}
