package org.example.petProject.service;

import jakarta.annotation.PostConstruct;
import org.example.petProject.model.ConstructionGroup;
import org.example.petProject.repository.ConstructionGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Класс реализует интерфейс {@link org.example.petProject.service.ConstructionGroupService}
 * с внедренной зависимостью интерфейса {@link org.example.petProject.repository.ConstructionGroupRepository}
 *
 * @author Молоканов Михаил
 * @version 0.0.1-SNAPSHOT
 */
@Service
public class ConstructionGroupServiceImpl implements ConstructionGroupService {
    private final ConstructionGroupRepository constructionGroupRepository;

    @Autowired
    public ConstructionGroupServiceImpl(ConstructionGroupRepository constructionGroupRepository) {
        this.constructionGroupRepository = constructionGroupRepository;
    }


    //Генерация данных тестирования в таблице "groups" базы данных после внедрения зависимостей
//    @PostConstruct
//    private void generateConstructionGroupsData() {
//        constructionGroupRepository.save(new ConstructionGroup(7, "Смесеобразование"));
//        constructionGroupRepository.save(new ConstructionGroup(15, "Электрооборудование двигателя"));
//        constructionGroupRepository.save(new ConstructionGroup(20, "Охлаждение двигателя"));
//        constructionGroupRepository.save(new ConstructionGroup(27, "Автоматическая коробка передач"));
//        constructionGroupRepository.save(new ConstructionGroup(47, "Топливная система"));
//        constructionGroupRepository.save(new ConstructionGroup(49, "Система выхлопа"));
//        constructionGroupRepository.save(new ConstructionGroup(54, "Электрооборудование внутреннее"));
//        constructionGroupRepository.save(new ConstructionGroup(72, "Двери"));
//        constructionGroupRepository.save(new ConstructionGroup(80, "Центральное запирание"));
//        constructionGroupRepository.save(new ConstructionGroup(82, "Электрооборудование наружнее"));
//        constructionGroupRepository.save(new ConstructionGroup(83, "Кондиционирование"));
//        constructionGroupRepository.save(new ConstructionGroup(91, "Подушки безопасности"));
//    }

    @Override
    public List<ConstructionGroup> getAllConstructionGroups() {
        return constructionGroupRepository.findAll();
    }

    @Override
    public ConstructionGroup getConstructionGroupById(Long id) {
        return constructionGroupRepository.findById(id).orElse(null);
    }

    @Override
    public ConstructionGroup createConstructionGroup(ConstructionGroup constructionGroup) {
        return constructionGroupRepository.save(constructionGroup);
    }

    @Override
    public ConstructionGroup updateConstructionGroup(Long id, ConstructionGroup constructionGroup) {
        ConstructionGroup existingConstructionGroup = getConstructionGroupById(id);
        if (existingConstructionGroup != null) {
            existingConstructionGroup.setGroupNumber(constructionGroup.getGroupNumber());
            existingConstructionGroup.setGroupName(constructionGroup.getGroupName());
            return constructionGroupRepository.save(existingConstructionGroup);
        }
        return null;
    }

    @Override
    public void deleteConstructionGroup(Long id) {
        constructionGroupRepository.deleteById(id);
    }

    @Override
    public ConstructionGroup getConstructionGroupByGroupNumber(int groupNumber) {
        return constructionGroupRepository.findConstructionGroupByGroupNumber(groupNumber);
    }
}
