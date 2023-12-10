package krg.petr.naumen.repository;

import krg.petr.naumen.model.ClientsPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientsPersonRepository extends JpaRepository<ClientsPerson, Long> {
}