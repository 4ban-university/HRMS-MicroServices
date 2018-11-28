package ca.concordia.soen6841.dbservice.exceptions;

public class PositionNotFoundException extends RuntimeException {
    public PositionNotFoundException(Integer id) {
        super("Could not find position " + id);
    }
}