package krg.petr.naumen.service;

import krg.petr.naumen.model.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> getDepartmentsByParent(Long idDepartment);

    List<String> getDivisionByDepartmentName(String name);

    List<Department> getTopLevelDepartments();
}