package lesson_8;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("clientCh")
@Scope("prototype")
public class ClientCh extends Client {

    public ClientCh() {
        super(DoctorType.CHIRURGUS);
    }
}