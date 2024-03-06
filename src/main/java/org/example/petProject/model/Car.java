package org.example.petProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Класс служит для хранения в базе данных объектов - автомобилей
 * со свойствами <b>id</b>, <b>vin</b>, <b>registrationNumber</b>, <b>ownerName</b> и <b>repairData</b>
 *
 * @author Молоканов Михаил
 * @version 0.0.1-SNAPSHOT
 */
@Data
@Entity
@Table(name = "cars")
public class Car {
    /**
     * Поле номер в таблице базы данных
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Поле идентификационный номер автомобиля
     */
    @Column(name = "vin")
    private String vin;
    /**
     * Поле государственный номер транспортного средства
     */
    @Column(name = "reg_num")
    private String registrationNumber;
    /**
     * Поле фамилия владельца автомобиля
     */
    @Column(name = "owner_name")
    private String ownerName;
    /**
     * Поле для реализации вторичного ключа в таблице "repairs" поле "car_id"
     */
    @JsonIgnore
    @OneToMany(mappedBy = "car")
    private List<RepairData> repairData;

    /**
     * Конструктор - создание нового объекта класса
     *
     * @see Car#Car(String, String, String)
     */
    public Car() {
    }

    /**
     * Конструктор - создание нового объекта класса
     *
     * @param vin                идентификационный номер автомобиля
     * @param registrationNumber государственный номер транспортного средства
     * @param ownerName          фамилия владельца автомобиля
     * @see Car#Car()
     */
    public Car(String vin, String registrationNumber, String ownerName) {
        this.vin = vin;
        this.registrationNumber = registrationNumber;
        this.ownerName = ownerName;
    }

    // Переопределение метода toString() для тестирования работы приложения
    @Override
    public String toString() {
        return "Car{" +
                "vin='" + vin + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", ownerName='" + ownerName + '\'' +
                '}';
    }
}
