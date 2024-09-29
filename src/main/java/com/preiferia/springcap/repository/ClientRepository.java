package com.preiferia.springcap.repository;

import com.preiferia.springcap.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Client repository.
 */
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
}
