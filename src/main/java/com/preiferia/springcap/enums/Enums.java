package com.preiferia.springcap.enums;

/**
 * The type Enums.
 */
public class Enums {
    /**
     * The enum Exception messages.
     */
    public enum ExceptionMessages {
        /**
         * The Invalid name format.
         */
        INVALID_NAME_FORMAT("001", "client name format is invalid"),
        /**
         * The Invalid identification format.
         */
        INVALID_IDENTIFICATION_FORMAT("002", "client identification format is invalid"),
        /**
         * The Invalir phone format.
         */
        INVALIR_PHONE_FORMAT("003", "client phone number format is invalid"),
        /**
         * The Invalid value.
         */
        INVALID_VALUE("004", "the client id must be a positive number"),
        /**
         * The Entity not registered.
         */
        ENTITY_NOT_REGISTERED("005","the entity is not registered");

        private final String code;
        private final String message;

        private ExceptionMessages(String code, String value) {
            this.code = code;
            this.message = value;
        }

        /**
         * Gets code.
         *
         * @return the code
         */
        public String getCode() {
            return code;
        }

        /**
         * Gets message.
         *
         * @return the message
         */
        public String getMessage() {
            return message;
        }
    }
}
