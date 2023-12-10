package krg.petr.naumen.model;

import jakarta.persistence.*;

@Entity
@Table(name = "objects")
public class Objects extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @OneToOne(mappedBy = "objects", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private ClientObjects clientObjects;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ClientObjects getClientObjects() {
        return clientObjects;
    }

    public void setClientObjects(ClientObjects clientObjects) {
        this.clientObjects = clientObjects;
    }
}