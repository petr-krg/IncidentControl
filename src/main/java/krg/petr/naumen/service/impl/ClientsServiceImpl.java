package krg.petr.naumen.service.impl;

import krg.petr.naumen.dto.ClientsListDTO;
import krg.petr.naumen.model.Clients;
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

    @Override
    public List<ClientsListDTO> getClientsList() {
        return clientsRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
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