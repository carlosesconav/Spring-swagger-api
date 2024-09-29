package com.preiferia.springcap.service;

import com.preiferia.springcap.dto.ClientRequestDTO;
import com.preiferia.springcap.dto.ClientResponseDTO;
import com.preiferia.springcap.entity.ClientEntity;
import com.preiferia.springcap.enums.Enums.ExceptionMessages;
import com.preiferia.springcap.exceptions.ServiceException;
import com.preiferia.springcap.repository.ClientRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type Client service.
 */
@Service
public class ClientServiceImpl implements IClientService {

    @Autowired
    private ClientRepository clientRepository;
    private ModelMapper mapper = new ModelMapper();

    @Override
    public ClientResponseDTO addClient(ClientRequestDTO clientRequestDTO) throws ServiceException {
        String strName = clientRequestDTO.getStrName();
        String regexName = "^[a-zA-Z0-9]*$";
        String strIdentification = clientRequestDTO.getStrIdentification();
        String strPhone = clientRequestDTO.getStrPhone();

        if (strIdentification == null || strIdentification.equals(" ")) {
            throw new ServiceException(ExceptionMessages.INVALID_IDENTIFICATION_FORMAT);
        } else if (strName == null || strName.equals(" ")) {
            throw new ServiceException(ExceptionMessages.INVALID_NAME_FORMAT);
        } else if (strPhone == null || strPhone.equals(" ")) {
            throw new ServiceException(ExceptionMessages.INVALIR_PHONE_FORMAT);
        } else {
            ClientEntity clientEntity = mapper.map(clientRequestDTO, ClientEntity.class);
            ClientEntity savedClient = clientRepository.save(clientEntity);
            return mapper.map(savedClient, ClientResponseDTO.class);
        }
    }

    @Override
    public List<ClientResponseDTO> showAllClients() {

        List<ClientEntity> clientList = clientRepository.findAll();

        return clientList.stream()
                .map(clientEntity -> mapper.map(clientEntity, ClientResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<ClientResponseDTO> findClientById(long clientId) throws ServiceException {
        if (clientId <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_VALUE.getMessage());
        }
        Optional<ClientEntity> client = clientRepository.findById(clientId);

        if (client.isPresent()) {
            ClientEntity clientEntity = client.get();
            ClientResponseDTO clientDTO = mapper.map(clientEntity, ClientResponseDTO.class);
            return Optional.of(clientDTO);
        } else {
            throw new ServiceException(ExceptionMessages.ENTITY_NOT_REGISTERED);
        }
    }

    @Override
    @Transactional
    public ClientResponseDTO updateClientInfo(long clientId, ClientRequestDTO clientRequestDTO) throws ServiceException {
        if (clientId <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_VALUE.getMessage());
        }
        Optional<ClientEntity> client = clientRepository.findById(clientId);

        if (client.isPresent()) {
            ClientEntity clientEntity = client.get();
            if (clientRequestDTO.getStrName() == null || clientRequestDTO.getStrName().equals(" ")) {
                throw new ServiceException(ExceptionMessages.INVALID_NAME_FORMAT);
            } else if (clientRequestDTO.getStrIdentification() == null || clientRequestDTO.getStrIdentification().equals(" ")) {
                throw new ServiceException(ExceptionMessages.INVALID_IDENTIFICATION_FORMAT);
            } else {
                clientEntity.setStrName(clientRequestDTO.getStrName());
                clientEntity.setStrSurname(clientRequestDTO.getStrSurname());
                clientEntity.setStrIdentification(clientRequestDTO.getStrIdentification());
                clientEntity.setStrIdentificationType(clientRequestDTO.getStrIdentificationType());
                clientEntity.setStrEmail(clientRequestDTO.getStrEmail());
                clientEntity.setStrPhone(clientRequestDTO.getStrPhone());
                clientRepository.save(clientEntity);

                return mapper.map(clientEntity, ClientResponseDTO.class);

            }
        } else {
            throw new ServiceException(ExceptionMessages.ENTITY_NOT_REGISTERED);
        }
    }

    @Override
    @Transactional
    public ClientResponseDTO updateClientState(long clientId, String clientState) throws ServiceException {

        if (clientId <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_VALUE.getMessage());
        }

        if (clientState == null || clientState.equals(" ")) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_VALUE.getMessage());
        }

        Optional<ClientEntity> client = clientRepository.findById(clientId);
        if (client.isPresent()) {
            ClientEntity clientEntity = client.get();
            clientEntity.setStrState(clientState);
            clientRepository.save(clientEntity);

            return mapper.map(clientEntity, ClientResponseDTO.class);
        } else {
            throw new ServiceException(ExceptionMessages.ENTITY_NOT_REGISTERED);
        }

    }

    @Override
    public String deleteClient(long clientId) throws ServiceException {
        if (clientId <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_VALUE.getMessage());
        }
        Optional<ClientEntity> client = clientRepository.findById(clientId);
        if (client.isPresent()) {
            clientRepository.delete(client.get());
            return "Successfully deleted client";
        } else {
            throw new ServiceException(ExceptionMessages.ENTITY_NOT_REGISTERED);
        }
    }

    private final HashMap<Long, ClientResponseDTO> FAKEDB = new HashMap<>();

    @Override
    public ClientResponseDTO testService(Long id, String strName) {
        final String strIdentification = "111";
        final String strIdentificationType = "CC";
        final String strSurname = "Escobar";
        final String strEmail = "test@test.com";
        final String strState = "Activo";
        final String strPhone = "123456789";

        FAKEDB.put(id, new ClientResponseDTO(id, strIdentification, strIdentificationType, strName, strSurname, strEmail, strState, strPhone));
        return FAKEDB.get(id);
    }
}
