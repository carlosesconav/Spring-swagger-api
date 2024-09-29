package com.preiferia.springcap.controller;

import com.preiferia.springcap.dto.ClientRequestDTO;
import com.preiferia.springcap.dto.ClientResponseDTO;
import com.preiferia.springcap.entity.ClientEntity;
import com.preiferia.springcap.exceptions.ServiceException;
import com.preiferia.springcap.service.IClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * The type Client controller.
 */
@Tag(name = "Clientes", description = "API para la gestion de clientes")
@RestController
@RequestMapping(path = "/client")
public class ClientController {

    private static final Logger LOG = Logger.getLogger(ClientController.class);

    @Autowired
    private IClientService clientService;

    /**
     * Add client response entity.
     *
     * @param clientRequestDTO the client request dto
     * @return the response entity
     */
    @PostMapping(path = "/add")
    @Operation(summary = "Agregar cliente",
            description = "Agrega un cliente al sistema",
            tags = {"post"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente creado exitosamente (Cumple reglas de negocio)"),
            @ApiResponse(responseCode = "400", description = "Campos mal diligenciados"),
            @ApiResponse(responseCode = "500", description = "Error interno de servidor")
    })
    public ResponseEntity<ClientEntity> addClient(@RequestBody ClientRequestDTO clientRequestDTO) {
        try {
            clientService.addClient(clientRequestDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (ServiceException se) {
            LOG.warn(se.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Show all clients response entity.
     *
     * @return the response entity
     */
    @GetMapping(path = "/show")
    @Operation(summary = "Buscar Clientes",
            description = "Devuelve la lista de los clientes registrados",
            tags = {"get"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "la solicitud ha tenido éxito"),
            @ApiResponse(responseCode = "500", description = "Error interno de servidor")
    })
    public ResponseEntity<List<ClientResponseDTO>> showAllClients() {
        try {
            return ResponseEntity.ok(clientService.showAllClients());
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Find client by id response entity.
     *
     * @param clientId the client id
     * @return the response entity
     */
    @GetMapping(path = "/find/{clientId}")
    @Operation(summary = "Buscar cliente por id",
            description = "Devuelve el cliente registrado con el id solicitado",
            tags = {"get"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La solicitud ha tenido éxito"),
            @ApiResponse(responseCode = "404", description = "Recurso solicitado no encontrado"),
            @ApiResponse(responseCode = "400", description = "No se puede o no procesará la petición"),
    })
    public ResponseEntity<Optional<ClientResponseDTO>> findClientById(
            @Parameter(description = "Id para buscar producto") @PathVariable long clientId) {
        try {
            return ResponseEntity.ok(clientService.findClientById(clientId));
        } catch (ServiceException se) {
            LOG.warn(se.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update client info response entity.
     *
     * @param clientId         the client id
     * @param clientRequestDTO the client request dto
     * @return the response entity
     */
    @PutMapping(path = "/updateInfo/{clientId}")
    @Operation(summary = "Actualizar producto",
            description = "Cambia los atributos del cliente solicitado",
            tags = {"put"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La solicitud ha tenido éxito"),
            @ApiResponse(responseCode = "404", description = "Recurso solicitado no encontrado"),
            @ApiResponse(responseCode = "400", description = "No se puede o no procesará la petición"),
    })
    public ResponseEntity<ClientResponseDTO> updateClientInfo(@Parameter(description = "Id para actualizar producto") @PathVariable long clientId,
                                                              @RequestBody ClientRequestDTO clientRequestDTO) {
        try {
            return ResponseEntity.ok(clientService.updateClientInfo(clientId, clientRequestDTO));
        } catch (ServiceException se) {
            LOG.warn(se.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update client state response entity.
     *
     * @param clientId    the client id
     * @param clientState the client state
     * @return the response entity
     */
    @PatchMapping(path = "/updateState/{clientId}")
    @Operation(summary = "Actualizar el estado del cliente",
            description = "Cambia el estado del cliente solicitado",
            tags = {"patch"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La solicitud ha tenido éxito"),
            @ApiResponse(responseCode = "400", description = "No se puede o no procesará la petición"),
            @ApiResponse(responseCode = "404", description = "Recurso solicitado no encontrado"),
    })
    public ResponseEntity<ClientResponseDTO> updateClientState(
            @Parameter(description = "Id para actualizar el estado del cliente") @PathVariable long clientId,
            @Parameter(description = "Nuevo estado del cliente") @RequestParam String clientState) {
        try {
            ClientResponseDTO clientDTO = clientService.updateClientState(clientId, clientState);
            return new ResponseEntity<>(clientDTO, HttpStatus.OK);
        } catch (ServiceException se) {
            LOG.warn(se.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete client response entity.
     *
     * @param clientId the client id
     * @return the response entity
     */
    @DeleteMapping(path = "/delete/{clientId}")
    @Operation(summary = "Borrar cliente",
            description = "Borra el cliente de la base de datos",
            tags = {"delete"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La solicitud ha tenido éxito"),
            @ApiResponse(responseCode = "404", description = "Recurso solicitado no encontrado"),
            @ApiResponse(responseCode = "400", description = "Error interno de servidor"),
    })
    public ResponseEntity<String> deleteClient(@Parameter(description = "Id para eliminar el cliente") @PathVariable long clientId) {
        try {
            String message = clientService.deleteClient(clientId);
            return ResponseEntity.ok(message);
        } catch (ServiceException se) {
            LOG.warn(se.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
