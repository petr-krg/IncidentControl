package krg.petr.naumen.model;

import jakarta.persistence.*;
import krg.petr.naumen.model.enumeration.Status;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "clients")
public class Clients extends BaseEntity{

    @Column(name = "name", length = 1024)
    private String name;

    @Column(name = "bin", length = 12)
    private String bin;

    @Column(name = "address", length = 256)
    private String address;

    @Column(name = "status")
    private Integer status;

    @OneToMany(mappedBy = "clients", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ClientObjects> clientObjects = new HashSet<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusDisplayName() {
        Status clientStatus = Status.fromCode(status);
        return (status != null ? clientStatus.getDisplayName() : "");
    }

    public Set<ClientObjects> getClientObjects() {
        return clientObjects;
    }

    public void setClientObjects(Set<ClientObjects> clientObjects) {
        this.clientObjects = clientObjects;
    }
}