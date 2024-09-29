package com.preiferia.springcap.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Client entity.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "NUM_ID_CLIENT")
    private Long numId;
    @Column(name = "STR_IDENTIFICATION")
    private String strIdentification;
    @Column(name = "STR_IDENTIFICATION_TYPE")
    private String strIdentificationType;
    @Column(name = "STR_NAME")
    private String strName;
    @Column(name = "STR_SURNAME")
    private String strSurname;
    @Column(name = "STR_EMAIL")
    private String strEmail;
    @Column(name = "STR_STATE")
    private String strState;
    @Column(name = "STR_PHONE")
    private String strPhone;

}
