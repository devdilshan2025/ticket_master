package edu.icet.exception;

public class SeatLockedException extends RuntimeException {

    public SeatLockedException(long message) {
        super(String.valueOf(message));
    }

}
