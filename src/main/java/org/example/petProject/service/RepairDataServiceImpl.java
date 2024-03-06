package org.example.petProject.service;

import jakarta.annotation.PostConstruct;
import org.example.petProject.model.RepairData;
import org.example.petProject.repository.CarRepository;
import org.example.petProject.repository.ConstructionGroupRepository;
import org.example.petProject.repository.RepairDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Класс реализует интерфейс {@link org.example.petProject.service.RepairDataService}
 * с внедренными зависимостями интерфейсов {@link org.example.petProject.repository.RepairDataRepository},
 * {@link org.example.petProject.repository.CarRepository},
 * {@link org.example.petProject.repository.ConstructionGroupRepository}
 *
 * @author Молоканов Михаил
 * @version 0.0.1-SNAPSHOT
 */
@Service
public class RepairDataServiceImpl implements RepairDataService {
    private final RepairDataRepository repairDataRepository;
    private final CarRepository carRepository;
    private final ConstructionGroupRepository constructionGroupRepository;

    @Autowired
    public RepairDataServiceImpl(RepairDataRepository repairDataRepository,
                                 CarRepository carRepository,
                                 ConstructionGroupRepository constructionGroupRepository) {
        this.repairDataRepository = repairDataRepository;
        this.carRepository = carRepository;
        this.constructionGroupRepository = constructionGroupRepository;
    }

    //Генерация данных тестирования в таблице "repairs" базы данных после внедрения зависимостей
//    @PostConstruct
//    private void generateRepairsData() {
//        repairDataRepository.save(new RepairData(
//                carRepository.findById(1L).orElse(null),
//                constructionGroupRepository.findById(1L).orElse(null),
//                "Требуется...", "Шпунтиков", LocalDate.now()));
//        repairDataRepository.save(new RepairData(
//                carRepository.findById(4L).orElse(null),
//                constructionGroupRepository.findById(7L).orElse(null),
//                "Требуется...", "Винтиков", LocalDate.now()));
//        repairDataRepository.save(new RepairData(
//                carRepository.findById(6L).orElse(null),
//                constructionGroupRepository.findById(11L).orElse(null),
//                "Требуется...", "Торопунькин", LocalDate.now()));
//        repairDataRepository.save(new RepairData(
//                carRepository.findById(1L).orElse(null),
//                constructionGroupRepository.findById(7L).orElse(null),
//                "Требуется...", "Шпунтиков", LocalDate.now()));
//        repairDataRepository.save(new RepairData(
//                carRepository.findById(1L).orElse(null),
//                constructionGroupRepository.findById(12L).orElse(null),
//                "Требуется...", "Винтиков", LocalDate.now()));
//        repairDataRepository.save(new RepairData(
//                carRepository.findById(4L).orElse(null),
//                constructionGroupRepository.findById(7L).orElse(null),
//                "Требуется...", "Винтиков", LocalDate.now()));
//        repairDataRepository.save(new RepairData(
//                carRepository.findById(4L).orElse(null),
//                constructionGroupRepository.findById(7L).orElse(null),
//                "Требуется...", "Торопунькин", LocalDate.now()));
//    }

    @Override
    public List<RepairData> getAllRepairData() {
        return repairDataRepository.findAll();
    }

    @Override
    public RepairData getRepairDataById(Long id) {
        return repairDataRepository.findById(id).orElse(null);
    }

    @Override
    public RepairData createRepairData(RepairData repairData) {
        return repairDataRepository.save(repairData);
    }

    @Override
    public RepairData updateRepairData(Long id, RepairData repairData) {
        RepairData existingRepairData = getRepairDataById(id);
        if (existingRepairData != null) {
            existingRepairData.setCar(repairData.getCar());
            existingRepairData.setGroup(repairData.getGroup());
            existingRepairData.setRepairReport(repairData.getRepairReport());
            existingRepairData.setExecutorName(repairData.getExecutorName());
            existingRepairData.setRepairDate(repairData.getRepairDate());
            return repairDataRepository.save(existingRepairData);

        }
        return null;
    }

    @Override
    public void deleteRepairData(Long id) {
        repairDataRepository.deleteById(id);
    }

    @Override
    public List<RepairData> getRepairDataByCarVin(String carVin) {
        return repairDataRepository.findRepairDataByCarVin(carVin);
    }

    @Override
    public List<RepairData> getRepairDataByCarVinAndConstructionGroupNumber(String carVin, int constructionGroupNumber) {
        return repairDataRepository.findRepairDataByCarVinAndConstructionGroupNumber(carVin, constructionGroupNumber);
    }
}
