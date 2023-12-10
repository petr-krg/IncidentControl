package krg.petr.naumen.repository;

import krg.petr.naumen.model.EmployeesPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeesPersonRepository extends JpaRepository<EmployeesPerson, Long> {
}