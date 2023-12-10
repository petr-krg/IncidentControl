package krg.petr.naumen.service;

import krg.petr.naumen.dto.ClientContactDTO;
import krg.petr.naumen.dto.ClientsListDTO;
import java.util.List;

public interface ClientsService {

    List<ClientsListDTO> getClientsList();

    void addContact(ClientContactDTO addClientContactDTO);
}