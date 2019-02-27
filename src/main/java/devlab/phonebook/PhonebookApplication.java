package devlab.phonebook;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@SpringBootApplication //@Configuration //@EnableAutoConfiguration //@ComponentScan
public class PhonebookApplication {

    /*
    statyczna metoda, która uruchamia statyczną metodę 'run'klasy SpringApplication.
    Jako parametr przyjmuje naszą klasę uruchomieniową.
     */


    public static void main(String[] args) {
        SpringApplication.run(PhonebookApplication.class, args);

    }

    /*

     Aplikacja ma za zadanie gromadzić nasze kontakty do innych osób.
     dla uproszeczenia tutaj będziemy przechowywać imię, nazwisko, numer telefonu, adres jako miejscowość, kategorię kotaktu,
     ranking, czyli coś po czym w przyszłości możemy wyswietlać kontakty w kolejności np. ze względu na częstość używania
     i tagi, po których łatwo wyszukamy konkretną grupę osób.

     Relacje:  ManyToMany, OneToMany, ManyToOne.

*/

}

