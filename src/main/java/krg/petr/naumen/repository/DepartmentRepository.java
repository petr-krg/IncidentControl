package krg.petr.naumen.repository;

import krg.petr.naumen.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    List<Department> findByParentId(Long parentId);

    List<Department> findByParentIsNull();

    Department findByName(String departmentName);

}