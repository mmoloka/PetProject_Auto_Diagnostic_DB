package org.example.petProject.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

/**
 * Класс служит для хранения в базе данных объектов - результатов диагностики автомобилей
 * со свойствами <b>id</b>, <b>car</b>, <b>group</b>, <b>repairReport</b>, <b>executorName</b> и <b>repairDate</b>
 *
 * @author Молоканов Михаил
 * @version 0.0.1-SNAPSHOT
 */
@Data
@Entity
@Table(name = "repairs")
public class RepairData {
    /**
     * Поле номер в таблице базы данных
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Поле автомобиля из таблицы "cars" базы данных
     */
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
    /**
     * Поле конструкционной группы из таблицы "groups" базы данных
     */
    @ManyToOne
    @JoinColumn(name = "group_id")
    private ConstructionGroup group;
    /**
     * Поле результата диагностики автомобиля
     */
    @Column(name = "report")
    private String repairReport;
    /**
     * Поле фамилия исполнителя диагностики автомобиля
     */
    @Column(name = "executor")
    private String executorName;
    /**
     * Поле дата выполнения диагностики автомобиля
     */
    @Column(name = "date")
    private LocalDate repairDate;

    /**
     * Конструктор - создание нового объекта класса
     *
     * @see RepairData#RepairData(Car, ConstructionGroup, String, String, LocalDate)
     */
    public RepairData() {
    }

    /**
     * Конструктор - создание нового объекта класса
     *
     * @param car          автомобиль из таблицы "cars" базы данных
     * @param group        конструкционная группа из таблицы "groups" базы данных
     * @param repairReport результат диагностики автомобиля
     * @param executorName фамилия исполнителя диагностики автомобиля
     * @param repairDate   дата выполнения диагностики автомобиля
     * @see RepairData#RepairData()
     */
    public RepairData(Car car, ConstructionGroup group, String repairReport, String executorName, LocalDate repairDate) {
        this.car = car;
        this.group = group;
        this.repairReport = repairReport;
        this.executorName = executorName;
        this.repairDate = repairDate;
    }
}
