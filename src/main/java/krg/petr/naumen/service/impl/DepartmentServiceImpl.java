package krg.petr.naumen.service.impl;

import krg.petr.naumen.model.Department;
import krg.petr.naumen.repository.DepartmentRepository;
import krg.petr.naumen.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> getDepartmentsByParent(Long idDepartment) {

        if (idDepartment == null || idDepartment.equals(0L)) {
            return departmentRepository.findByParentIsNull();
        }

        return departmentRepository.findByParentId(idDepartment);
    }

    @Override
    public List<String> getDivisionByDepartmentName(String name) {

        Department department = departmentRepository.findByName(name);

        if (department != null) {
            return departmentRepository.findByParentId(department.getId())
                    .stream()
                    .map(Department::getName)
                    .toList();
        }

        return null;
    }

    @Override
    public List<Department> getTopLevelDepartments() {
        return null;
    }
}