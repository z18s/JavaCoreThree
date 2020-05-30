package lesson_8;

public class Client {
    private final DoctorType request;
    private final int number;

    private static int counter = 0;

    {
        counter++;
    }

    public Client(DoctorType request) {
        this.request = request;
        this.number = counter;
    }

    public DoctorType getRequest() {
        return request;
    }

    public int getNumber() {
        return number;
    }
}