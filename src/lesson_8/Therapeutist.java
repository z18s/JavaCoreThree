package lesson_8;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("therapeutist")
@Scope("singleton")
public class Therapeutist extends Doctor {

    public Therapeutist() {
        super(DoctorType.THERAPEUTIST);
    }
}