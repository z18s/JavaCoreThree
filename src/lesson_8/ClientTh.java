package lesson_8;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("clientTh")
@Scope("prototype")
public class ClientTh extends Client {

    public ClientTh() {
        super(DoctorType.THERAPEUTIST);
    }
}