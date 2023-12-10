package krg.petr.naumen.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.hibernate.annotations.SQLRestriction;


@Entity
@DiscriminatorValue("Client")
@SQLRestriction("employee = 0")
public class ClientsPerson extends Person{

}