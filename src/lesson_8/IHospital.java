package lesson_8;

public interface IHospital {
    void receiveClient(Client client);
    void receiveClient(Client... clients);
}