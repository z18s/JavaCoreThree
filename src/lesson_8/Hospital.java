package lesson_8;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component("hospital")
@Scope("singleton")
public class Hospital implements IHospital {
    private final ArrayList<Doctor> doctors = new ArrayList<>();

    @Autowired
    @Qualifier("therapeutist")
    public void addTherapeutist(Doctor doctor) {
        doctors.add(doctor);
    }

    @Autowired
    @Qualifier("chirurgus")
    public void addChirurgus(Doctor doctor) {
        doctors.add(doctor);
    }

    @Override
    public void receiveClient(Client client) {
        for (Doctor doctor : doctors) {
            if (doctor.getType() == client.getRequest()) {
                doctor.work(client);
            }
        }
    }

    @Override
    public void receiveClient(Client... clients) {
        for (Client client : clients) {
            receiveClient(client);
        }
    }
}