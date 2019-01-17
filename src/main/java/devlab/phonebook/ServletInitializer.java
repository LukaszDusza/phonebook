package devlab.phonebook;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {


    // Ta klasa wiąże serwlet, filtr i komponenty ServletContextInitializer
    // z kontekstem aplikacji na serwererze.
    // Rozszerzenie klasy SpringBootServletInitializer pozwala nam również skonfigurować naszą aplikację,
    // gdy jest uruchamiana przez kontener serwletu, przez przesłonięcie metody configure ().

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PhonebookApplication.class);
    }

}

