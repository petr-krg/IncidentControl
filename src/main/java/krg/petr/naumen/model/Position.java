package krg.petr.naumen.model;

import jakarta.persistence.*;

@Entity
@Table(name = "positions")
public class Position extends BaseEntity{

    @Column(name = "name", length = 512)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}