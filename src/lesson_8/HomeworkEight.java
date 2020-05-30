package lesson_8;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HomeworkEight {

    public static void main(String[] args) {

        HelixArray helixArray = new HelixArray(5);
        helixArray.print();

        // ---
        System.out.println();
        // ---

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        IHospital hospital = context.getBean("hospital", IHospital.class);

        Client client1 = context.getBean("clientTh", Client.class);
        Client client2 = context.getBean("clientTh", Client.class);
        Client client3 = context.getBean("clientCh", Client.class);
        Client client4 = context.getBean("clientTh", Client.class);
        Client client5 = context.getBean("clientCh", Client.class);

        hospital.receiveClient(client1);
        hospital.receiveClient(client2, client3, client4);
        hospital.receiveClient(client5);
    }
}