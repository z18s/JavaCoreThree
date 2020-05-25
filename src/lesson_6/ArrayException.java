package lesson_6;

public class ArrayException extends RuntimeException {
    public ArrayException() {
        super("Number '4' not found.");
    }
}