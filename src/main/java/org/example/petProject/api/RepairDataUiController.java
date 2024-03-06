package org.example.petProject.api;

import org.example.petProject.model.RepairData;
import org.example.petProject.service.RepairDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс обрабатывает GET-запросы от клиентов, используя методы сервиса
 * {@link org.example.petProject.service.RepairDataServiceImpl}
 * и отображает результаты на HTML страницах
 *
 * @author Молоканов Михаил
 * @version 0.0.1-SNAPSHOT
 */
@Controller
@RequestMapping("repair/ui")
public class RepairDataUiController {

    private final RepairDataServiceImpl repairService;

    @Autowired
    public RepairDataUiController(RepairDataServiceImpl repairService) {
        this.repairService = repairService;
    }

    /**
     * Метод обрабатывает GET-запрос на получение всех объектов - результатов диагностики автомобилей
     * отображаемых на HTML странице
     *
     * @return имя отображаемой HTML страницы
     */
    @GetMapping
    public String getAllRepairData(Model model) {
        List<RepairDataUiTable> repairs = new ArrayList<>();
        for (RepairData repair : repairService.getAllRepairData()) {
            repairs.add(new RepairDataUiTable(repair));
        }
        model.addAttribute("repairs", repairs);
        return "repairs";
    }

    /**
     * Метод обрабатывает GET-запрос на получение всех объектов - результатов диагностики автомобиля
     * по его идентификационному номеру отображаемых на HTML странице
     *
     * @param carVin идентификационный номер автомобиля
     * @return имя отображаемой HTML страницы
     */
    @GetMapping("/vin/{carVin}")
    public String getRepairDataByCarVin(@PathVariable String carVin, Model model) {
        List<RepairDataUiTable> repairs = new ArrayList<>();
        for (RepairData repair : repairService.getRepairDataByCarVin(carVin)) {
            repairs.add(new RepairDataUiTable(repair));
        }
        model.addAttribute("repairs", repairs);
        return "repairs";
    }

    /**
     * Метод обрабатывает GET-запрос на получение всех объектов - результатов диагностики автомобиля
     * по его идентификационному номеру и номеру конструкционной группы отображаемых на HTML странице
     *
     * @param carVin                  идентификационный номер автомобиля
     * @param constructionGroupNumber номер конструкционной группы
     * @return имя отображаемой HTML страницы
     */
    @GetMapping("/vin/{carVin}/group/{constructionGroupNumber}")
    public String getRepairDataByCarVinAndConstructionGroupNumber(
            @PathVariable String carVin, @PathVariable int constructionGroupNumber, Model model) {
        List<RepairDataUiTable> repairs = new ArrayList<>();
        for (RepairData repair : repairService.getRepairDataByCarVinAndConstructionGroupNumber(
                carVin, constructionGroupNumber)) {
            repairs.add(new RepairDataUiTable(repair));
        }
        model.addAttribute("repairs", repairs);
        return "repairs";
    }
}
