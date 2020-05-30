package lesson_8;

public enum DoctorType {
    THERAPEUTIST("therapeutist"),
    CHIRURGUS("chirurgus");

    private final String type;

    DoctorType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}