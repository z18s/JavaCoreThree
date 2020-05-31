package lesson_8;

public abstract class Doctor {
    private final DoctorType type;

    public Doctor(DoctorType type) {
        this.type = type;
    }

    public DoctorType getType() {
        return type;
    }

    public void work(Client client) {
        System.out.printf("The %s has examined a client #%d.%n", type.getType(), client.getNumber());
    }
}