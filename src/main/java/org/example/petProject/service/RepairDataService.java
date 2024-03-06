package org.example.petProject.service;

import org.example.petProject.model.RepairData;

import java.util.List;

/**
 * Интерфейс служит для выполнения CRUD операций
 * используя репозиторий {@link org.example.petProject.repository.RepairDataRepository}
 *
 * @author Молоканов Михаил
 * @version 0.0.1-SNAPSHOT
 */
public interface RepairDataService {
    /**
     * Метод позволяет получить все объекты - результаты диагностики автомобилей репозитория
     *
     * @return объекты - искомые результаты диагностики автомобилей
     */
    List<RepairData> getAllRepairData();

    /**
     * Метод позволяет получить объект - результат диагностики автомобиля репозитория
     * по его номеру в таблице базы данных
     *
     * @param id номер результата диагностики автомобиля в таблице базы данных
     * @return объект - искомый результат диагностики автомобиля
     */
    RepairData getRepairDataById(Long id);

    /**
     * Метод позволяет добавить объект - результат диагностики автомобиля в репозиторий
     *
     * @param repairData добавляемый результат диагностики автомобиля
     * @return объект - добавляемый результат диагностики автомобиля
     */
    RepairData createRepairData(RepairData repairData);

    /**
     * Метод позволяет изменить объект - результат диагностики автомобиля репозитория
     * по его номеру в таблице базы данных
     *
     * @param id         номер результата диагностики автомобиля в таблице базы данных
     * @param repairData измененный результат диагностики автомобиля
     * @return объект - измененный результат диагностики автомобиля
     */
    RepairData updateRepairData(Long id, RepairData repairData);

    /**
     * Метод позволяет удалить объект - результат диагностики автомобиля репозитория
     * по его номеру в таблице базы данных
     *
     * @param id номер результата диагностики автомобиля в таблице базы данных
     */
    void deleteRepairData(Long id);

    /**
     * Метод позволяет получить все объекты - результаты диагностики автомобиля репозитория
     * по его идентификационному номеру
     *
     * @param carVin идентификационный номер автомобиля
     * @return объекты - искомые результаты диагностики автомобиля
     */
    List<RepairData> getRepairDataByCarVin(String carVin);

    /**
     * Метод позволяет найти все объекты - результаты диагностики автомобиля репозитория
     * по его идентификационному номеру и номеру конструкционной группы
     *
     * @param carVin                  идентификационный номер автомобиля
     * @param constructionGroupNumber номер конструкционной группы
     * @return объекты - искомые результаты диагностики автомобиля
     */
    List<RepairData> getRepairDataByCarVinAndConstructionGroupNumber(String carVin, int constructionGroupNumber);
}
