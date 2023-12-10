package krg.petr.naumen.model;

import jakarta.persistence.*;

@Entity
@Table(name = "client_objects")
public class ClientObjects extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Clients clients;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "object_id")
    private Objects objects;

    public ClientObjects() {

    }

    public ClientObjects(Clients clients, Objects objects) {
        this.clients = clients;
        this.objects = objects;
    }

    public Clients getClients() {
        return clients;
    }

    public void setClients(Clients clients) {
        this.clients = clients;
    }

    public Objects getObjects() {
        return objects;
    }

    public void setObjects(Objects objects) {
        this.objects = objects;
    }
}