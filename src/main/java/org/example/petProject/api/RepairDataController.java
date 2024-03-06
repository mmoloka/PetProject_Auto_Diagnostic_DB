package org.example.petProject.api;

import org.example.petProject.model.RepairData;
import org.example.petProject.service.RepairDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * Класс обрабатывает HTTP-запросы от клиентов и вызывает соответствующие методы сервиса
 * {@link org.example.petProject.service.RepairDataServiceImpl}
 *
 * @author Молоканов Михаил
 * @version 0.0.1-SNAPSHOT
 */
@RestController
@RequestMapping("/repair")
public class RepairDataController {

    private final RepairDataServiceImpl repairService;

    @Autowired
    public RepairDataController(RepairDataServiceImpl repairService) {
        this.repairService = repairService;
    }

    /**
     * Метод обрабатывает GET-запрос на получение всех JSON объектов - результатов диагностики автомобилей
     *
     * @return JSON объекты - результаты диагностики автомобилей и статус 200 ОК, либо статус 404 Not Found
     */
    @GetMapping
    public ResponseEntity<List<RepairData>> getAllRepairData() {
        final List<RepairData> repairs = repairService.getAllRepairData();
        if (!repairs.equals(Collections.emptyList())) {
            return new ResponseEntity<>(repairs, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Метод обрабатывает GET-запрос на получение JSON объекта - результат диагностики автомобиля
     * по его номеру в таблице базы данных
     *
     * @param id номер результата диагностики автомобиля в таблице базы данных
     * @return JSON объект - результат диагностики автомобиля и статус 200 ОК, либо статус 404 Not Found
     */
    @GetMapping("/{id}")
    public ResponseEntity<RepairData> getRepairDataById(@PathVariable Long id) {
        final RepairData repair = repairService.getRepairDataById(id);
        if (repair != null) {
            return new ResponseEntity<>(repair, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Метод обрабатывает POST-запрос на добавление объекта - результат диагностики автомобиля
     *
     * @param repairData JSON объект - добавляемый результат диагностики автомобиля
     * @return JSON объект - добавленный результат диагностики автомобиля и статус 201 Created,
     * либо статус 400 Bad Request
     */
    @PostMapping
    public ResponseEntity<RepairData> createRepairData(@RequestBody RepairData repairData) {
        try {
            return new ResponseEntity<>(repairService.createRepairData(repairData), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Метод обрабатывает PUT-запрос на изменение объекта - результат диагностики автомобиля
     * по его номеру в таблице базы данных
     *
     * @param id         номер результата диагностики автомобиля в таблице базы данных
     * @param repairData JSON объект - измененный результат диагностики автомобиля
     * @return JSON объект - измененный результат диагностики автомобиля и статус 200 ОК, либо статус 404 Not Found
     */
    @PutMapping("/{id}")
    public ResponseEntity<RepairData> updateRepairData(@PathVariable Long id, @RequestBody RepairData repairData) {
        final RepairData updatedRepair = repairService.updateRepairData(id, repairData);
        if (updatedRepair != null) {
            return new ResponseEntity<>(updatedRepair, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Метод обрабатывает DELETE-запрос на удаление объекта - результат диагностики автомобиля
     * по его номеру в таблице базы данных
     *
     * @param id номер результата диагностики автомобиля в таблице базы данных
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRepairData(@PathVariable Long id) {
        try {
            repairService.deleteRepairData(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Метод обрабатывает GET-запрос на получение всех JSON объектов - результатов диагностики автомобиля
     * по его идентификационному номеру
     *
     * @param carVin идентификационный номер автомобиля
     * @return JSON объекты - результаты диагностики автомобиля и статус 200 ОК, либо статус 404 Not Found
     */
    @GetMapping("/vin/{carVin}")
    public ResponseEntity<List<RepairData>> getRepairDataByCarVin(@PathVariable String carVin) {
        final List<RepairData> repairData = repairService.getRepairDataByCarVin(carVin);
        if (!repairData.equals(Collections.emptyList())) {
            return new ResponseEntity<>(repairData, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Метод обрабатывает GET-запрос на получение всех JSON объектов - результатов диагностики автомобиля
     * по его идентификационному номеру и номеру конструкционной группы
     *
     * @param carVin                  идентификационный номер автомобиля
     * @param constructionGroupNumber номер конструкционной группы
     * @return JSON объекты - результаты диагностики автомобиля и статус 200 ОК, либо статус 404 Not Found
     */
    @GetMapping("/vin/{carVin}/group/{constructionGroupNumber}")
    public ResponseEntity<List<RepairData>> getRepairDataByCarVinAndConstructionGroupNumber(
            @PathVariable String carVin, @PathVariable int constructionGroupNumber) {
        final List<RepairData> repairData = repairService.getRepairDataByCarVinAndConstructionGroupNumber(
                carVin, constructionGroupNumber);
        if (!repairData.equals(Collections.emptyList())) {
            return new ResponseEntity<>(repairData, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
