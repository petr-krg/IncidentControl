package krg.petr.naumen.service.impl;

import krg.petr.naumen.dto.ClientContactDTO;
import krg.petr.naumen.dto.ClientsListDTO;
import krg.petr.naumen.model.Clients;
import krg.petr.naumen.model.ClientsPerson;
import krg.petr.naumen.repository.ClientsPersonRepository;
import krg.petr.naumen.repository.ClientsRepository;
import krg.petr.naumen.service.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientsServiceImpl implements ClientsService {

    @Autowired
    private ClientsRepository clientsRepository;

    @Autowired
    private ClientsPersonRepository clientsPersonRepository;

    @Override
    public List<ClientsListDTO> getClientsList() {
        return clientsRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void addContact(ClientContactDTO clientContactDTO) {
        ClientsPerson clientsPerson = new ClientsPerson();

        clientsPerson.setEmployee(0);
        clientsPerson.setFirstName(clientContactDTO.getFirstName());
        clientsPerson.setLastName(clientContactDTO.getLastName());
        clientsPerson.setPatName(clientContactDTO.getPatName());
        clientsPerson.setBirthDate(clientContactDTO.getBirthDate());
        clientsPerson.setPhone(clientContactDTO.getPhone());
        clientsPerson.setEmail(clientContactDTO.getEmail());

        clientsPersonRepository.save(clientsPerson);
    }

    private ClientsListDTO convertToDTO(Clients clients) {
        ClientsListDTO dto = new ClientsListDTO();
        dto.setName(clients.getName());
        dto.setAddress(clients.getAddress());
        dto.setStatus(clients.getStatusDisplayName());
        dto.setDateCreate(clients.getDateFromCreated());
        dto.setPrimaryContact("");
        dto.setPhone("");

        return dto;
    }
}