package dev.patika.veterinary.Management.System.core.exception;

public class ValidationException extends RuntimeException {
    private final String entityName;

    public ValidationException(String entityName, String message) {
        super(message);
        this.entityName = entityName;
    }

    public String getEntityName() {
        return entityName;
    }
}
