package com.preiferia.springcap;

import com.preiferia.springcap.dto.ClientRequestDTO;
import com.preiferia.springcap.dto.ClientResponseDTO;
import com.preiferia.springcap.entity.ClientEntity;
import com.preiferia.springcap.exceptions.ServiceException;
import com.preiferia.springcap.repository.ClientRepository;
import com.preiferia.springcap.service.ClientServiceImpl;
import com.preiferia.springcap.service.IClientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Springcap application tests.
 */
@SpringBootTest
class SpringcapApplicationTests {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private IClientService clientService = new ClientServiceImpl();

    /**
     * Test service.
     */
    @Test
    void testService() {
        final ClientResponseDTO result =
                clientService.testService(1L, "Carlos");
        final ClientResponseDTO expected =
                new ClientResponseDTO(1L, "111", "CC", "Carlos", "Escobar",
                        "test@test.com", "Activo", "123456789");
        Assertions.assertEquals(expected, result);
    }

    /**
     * Show all clients.
     *
     * @throws ServiceException the service exception
     */
    @Test
    void showAllClients() throws ServiceException {

        List<ClientEntity> clientMock = new ArrayList<>();
        ClientEntity clientEntity =
                new ClientEntity(1L, "111", "CC", "Carlos", "Escobar", "test@test.com", "Activo", "123456789");
        clientMock.add(clientEntity);
        ClientResponseDTO client =
                new ClientResponseDTO(1L, "111", "CC", "Carlos", "Escobar", "test@test.com", "Activo", "123456789");
        List<ClientResponseDTO> expectedClient = new ArrayList<>();
        expectedClient.add(client);

        Mockito.when(clientRepository.findAll()).thenReturn(clientMock);
        List<ClientResponseDTO> resultClient = clientService.showAllClients();

        for (ClientResponseDTO client1 : resultClient) {
            for (ClientResponseDTO client2 : expectedClient) {
                Assertions.assertEquals(client2, client1);
                Assertions.assertEquals(client2.getStrName(), client1.getStrName());
                Assertions.assertEquals(client2.getStrSurname(), client1.getStrSurname());
            }
        }
    }

    /**
     * Add client.
     *
     * @throws ServiceException the service exception
     */
    @Test
    void addClient() throws ServiceException {

        ClientEntity clientEntityMock =
                new ClientEntity(1L, "222", "CC", "Andres", "Escobar", "test@test.com", "Activo", "123456789");

        Mockito.when(clientRepository.save(Mockito.any())).thenReturn(clientEntityMock);
        ClientResponseDTO expectedClient =
                new ClientResponseDTO(1L, "222", "CC", "Andres", "Escobar", "test@test.com", "Activo", "123456789");

        ClientRequestDTO client =
                new ClientRequestDTO(1L, "222", "CC", "Andres", "Escobar", "test@test.com", "Activo", "123456789");

        ClientResponseDTO savedClient = clientService.addClient(client);
        Assertions.assertEquals(expectedClient, savedClient);
    }

    /**
     * Find client by id.
     *
     * @throws ServiceException the service exception
     */
    @Test
    void findClientById() throws ServiceException {
        ClientEntity clientEntityMock =
                new ClientEntity(1L, "112", "CC", "Andres", "Escobar", "test@test.com", "Activo", "123456789");
        ClientResponseDTO expectedClient =
                new ClientResponseDTO(1L, "112", "CC", "Andres", "Escobar", "test@test.com", "Activo", "123456789");
        Mockito.when(clientRepository.findById(clientEntityMock.getNumId())).thenReturn(Optional.of(clientEntityMock));
        Optional<ClientResponseDTO> resultClient = clientService.findClientById(clientEntityMock.getNumId());
        Assertions.assertTrue(resultClient.isPresent());
        Assertions.assertEquals(resultClient.get(), expectedClient);
    }

    /**
     * Update client info.
     *
     * @throws ServiceException the service exception
     */
    @Test
    void updateClientInfo() throws ServiceException {

        ClientEntity clientEntityMock =
                new ClientEntity(1L, "112", "CC", "Andres", "Escobar",
                        "test@test.com", "Activo", "123456789");
        ClientResponseDTO expectedClient =
                new ClientResponseDTO(1L, "122", "TI", "Carlos", "Escobar",
                        "test@test.com", "Activo", "123456782");
        Mockito.when(clientRepository.findById(clientEntityMock.getNumId())).thenReturn(Optional.of(clientEntityMock));
        Optional<ClientResponseDTO> resultClient = clientService.findClientById(clientEntityMock.getNumId());
        Assertions.assertTrue(resultClient.isPresent());
        ClientResponseDTO resultClientUpdate = clientService.updateClientInfo(clientEntityMock.getNumId(),
                new ClientRequestDTO(1L, "122", "TI", "Carlos", "Escobar",
                        "test@test.com", "Activo", "123456782"));
        Assertions.assertEquals(expectedClient, resultClientUpdate);
    }

    /**
     * Update client state.
     *
     * @throws ServiceException the service exception
     */
    @Test
    void updateClientState() throws ServiceException {

        ClientEntity clientEntityMock =
                new ClientEntity(1L, "112", "CC", "Andres", "Escobar",
                        "test@test.com", "Activo", "123456789");
        ClientResponseDTO expectedClient =
                new ClientResponseDTO(1L, "112", "CC", "Andres", "Escobar",
                        "test@test.com", "Inactivo", "123456789");
        Mockito.when(clientRepository.findById(clientEntityMock.getNumId())).thenReturn(Optional.of(clientEntityMock));
        Optional<ClientResponseDTO> resultClient = clientService.findClientById(clientEntityMock.getNumId());
        Assertions.assertTrue(resultClient.isPresent());
        ClientResponseDTO resultClientUpdate = clientService.updateClientState(clientEntityMock.getNumId(), "Inactivo");
        Assertions.assertEquals(expectedClient, resultClientUpdate);
    }

    /**
     * Delete client.
     *
     * @throws ServiceException the service exception
     */
    @Test
    void deleteClient() throws ServiceException {

        ClientEntity clientEntityMock =
                new ClientEntity(1L, "112", "CC", "Andres", "Escobar",
                        "test@test.com", "Activo", "123456789");
        Mockito.when(clientRepository.findById(clientEntityMock.getNumId())).thenReturn(Optional.of(clientEntityMock));
        Optional<ClientResponseDTO> resultClient = clientService.findClientById(clientEntityMock.getNumId());
        Assertions.assertTrue(resultClient.isPresent());
        ClientResponseDTO client = resultClient.get();
        String expected = "Successfully deleted client";
        String resultDeleteClient = clientService.deleteClient(client.getNumId());
        Mockito.doNothing().when(clientRepository).delete(clientEntityMock);
        Mockito.verify(clientRepository, Mockito.times(1)).delete(clientEntityMock);
        Assertions.assertEquals(expected, resultDeleteClient);
    }
}
