package krg.petr.naumen.service.impl;

import krg.petr.naumen.model.Department;
import krg.petr.naumen.model.Position;
import krg.petr.naumen.repository.DepartmentRepository;
import krg.petr.naumen.repository.PositionRepository;
import krg.petr.naumen.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Override
    public List<Position> getPositionsByDepartmentId(Long idDepartment) {
        return null;
    }

    @Override
    public List<String> getPositionByDivisionName(String name) {

        Department department = departmentRepository.findByName(name);

        if (department != null) {
            return positionRepository.findByDepartmentId(department.getId())
                    .stream()
                    .map(Position::getName)
                    .toList();
        }

        return null;
    }
}