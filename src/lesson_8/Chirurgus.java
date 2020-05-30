package lesson_8;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("chirurgus")
@Scope("singleton")
public class Chirurgus extends Doctor {

    public Chirurgus() {
        super(DoctorType.CHIRURGUS);
    }
}