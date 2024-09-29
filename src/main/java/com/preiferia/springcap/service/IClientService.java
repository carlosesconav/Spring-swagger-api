package com.preiferia.springcap.service;

import com.preiferia.springcap.dto.ClientRequestDTO;
import com.preiferia.springcap.dto.ClientResponseDTO;
import com.preiferia.springcap.exceptions.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * The interface Client service.
 */
public interface IClientService {

    /**
     * Add client response dto.
     *
     * @param clientRequestDTO the client request dto
     * @return the client response dto
     * @throws ServiceException the service exception
     */
    ClientResponseDTO addClient(ClientRequestDTO clientRequestDTO) throws ServiceException;

    /**
     * Show all clients list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<ClientResponseDTO> showAllClients() throws ServiceException;

    /**
     * Find client by id optional.
     *
     * @param clientId the client id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<ClientResponseDTO> findClientById(long clientId) throws ServiceException;

    /**
     * Update client info client response dto.
     *
     * @param clientId         the client id
     * @param clientRequestDTO the client request dto
     * @return the client response dto
     * @throws ServiceException the service exception
     */
    ClientResponseDTO updateClientInfo(long clientId, ClientRequestDTO clientRequestDTO) throws ServiceException;

    /**
     * Update client state client response dto.
     *
     * @param clientId    the client id
     * @param clientState the client state
     * @return the client response dto
     * @throws ServiceException the service exception
     */
    ClientResponseDTO updateClientState(long clientId, String clientState) throws ServiceException;

    /**
     * Delete client string.
     *
     * @param clientId the client id
     * @return the string
     * @throws ServiceException the service exception
     */
    String deleteClient(long clientId) throws ServiceException;


    /**
     * Test service client response dto.
     *
     * @param id   the id
     * @param name the name
     * @return the client response dto
     */
    ClientResponseDTO testService (Long id, String name);

}
