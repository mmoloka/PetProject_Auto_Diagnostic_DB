package org.example.petProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * Класс служит для хранения в базе данных объектов - конструкционных ремонтных групп автомобилей
 * (например: группа электрооборудование, группа оборудование двигателя, группа двери и пр.)
 * со свойствами <b>id</b>, <b>groupNumber</b>, <b>groupName</b> и <b>repairData</b>
 *
 * @author Молоканов Михаил
 * @version 0.0.1-SNAPSHOT
 */
@Data
@Entity
@Table(name = "groups")
public class ConstructionGroup {
    /**
     * Поле номер в таблице базы данных
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Поле номер в программе ремонтной документации
     */
    @Column(name = "group_num")
    private int groupNumber;
    /**
     * Поле название в программе ремонтной документации
     */
    @Column(name = "group_name")
    private String groupName;
    /**
     * Поле для реализации вторичного ключа в таблице "repairs" поле "group_id"
     */
    @JsonIgnore
    @OneToMany(mappedBy = "group")
    private List<RepairData> repairData;

    /**
     * Конструктор - создание нового объекта класса
     *
     * @see ConstructionGroup#ConstructionGroup(int, String)
     */
    public ConstructionGroup() {
    }

    /**
     * Конструктор - создание нового объекта класса
     *
     * @param groupNumber номер в программе ремонтной документации
     * @param groupName   название в программе ремонтной документации
     */
    public ConstructionGroup(int groupNumber, String groupName) {
        this.groupNumber = groupNumber;
        this.groupName = groupName;
    }

    // Переопределение метода toString() для тестирования работы приложения
    @Override
    public String toString() {
        return "ConstructionGroup{" +
                "groupNumber=" + groupNumber +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}
