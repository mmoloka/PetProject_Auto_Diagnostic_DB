package org.example.petProject.api;

import lombok.Getter;
import org.example.petProject.model.RepairData;

import java.time.LocalDate;

/**
 * Класс служит для создания объектов - результатов диагностики автомобилей отображаемых на HTML страницах
 * со свойствами <b>carVin</b>, <b>carRegistrationNumber</b>, <b>carOwnerName</b>,
 * <b>constructionGroupNumber</b>, <b>constructionGroupName</b>,
 * <b>repairReport</b>, <b>executorName</b> и <b>repairDate</b>
 *
 * @author Молоканов Михаил
 * @version 0.0.1-SNAPSHOT
 */
@Getter
public class RepairDataUiTable {
    /**
     * Поле идентификационный номер диагностируемого автомобиля
     */
    private final String carVin;
    /**
     * Поле государственный номер диагностируемого автомобиля
     */
    private final String carRegistrationNumber;
    /**
     * Поле фамилия владельца диагностируемого автомобиля
     */
    private final String carOwnerName;
    /**
     * Поле номер конструкционной группы в программе ремонтной документации
     */
    private final int constructionGroupNumber;
    /**
     * Поле название конструкционной группы в программе ремонтной документации
     */
    private final String constructionGroupName;
    /**
     * Поле результата диагностики автомобиля
     */
    private final String repairReport;
    /**
     * Поле фамилия исполнителя диагностики автомобиля
     */
    private final String executorName;
    /**
     * Поле дата выполнения диагностики автомобиля
     */
    private final LocalDate repairDate;

    /**
     * Конструктор - создание нового объекта класса
     *
     * @param repairData объект класса {@link org.example.petProject.model.RepairData}
     */
    public RepairDataUiTable(RepairData repairData) {
        this.carVin = repairData.getCar().getVin();
        this.carRegistrationNumber = repairData.getCar().getRegistrationNumber();
        this.carOwnerName = repairData.getCar().getOwnerName();
        this.constructionGroupNumber = repairData.getGroup().getGroupNumber();
        this.constructionGroupName = repairData.getGroup().getGroupName();
        this.repairReport = repairData.getRepairReport();
        this.executorName = repairData.getExecutorName();
        this.repairDate = repairData.getRepairDate();
    }
}
