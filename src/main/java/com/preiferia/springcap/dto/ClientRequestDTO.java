package com.preiferia.springcap.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Client request dto.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientRequestDTO {

    private Long numId;
    private String strIdentification;
    private String strIdentificationType;
    private String strName;
    private String strSurname;
    private String strEmail;
    private String strState;
    private String strPhone;

}
