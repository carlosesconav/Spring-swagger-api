package com.preiferia.springcap.exceptions;

import com.preiferia.springcap.enums.Enums.ExceptionMessages;

/**
 * The type Service exception.
 */
public class ServiceException extends Exception {
    private String code;
    private String message;

    /**
     * Instantiates a new Service exception.
     */
    public ServiceException(){
        super();
    }

    /**
     * Instantiates a new Service exception.
     *
     * @param em the em
     */
    public ServiceException(ExceptionMessages em){
        super(em.getMessage());
        this.code = em.getCode();
        this.message = em.getMessage();
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
