package devlab.phonebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //@Configuration //@EnableAutoConfiguration //@ComponentScan
public class PhonebookApplication {

    //statyczna metoda, która uruchamia statyczną metodę 'run'klasy SpringApplication.
    // Jako parametr przyjmuje naszą klasę uruchomieniową.

    public static void main(String[] args) {
        SpringApplication.run(PhonebookApplication.class, args);
    }

}

