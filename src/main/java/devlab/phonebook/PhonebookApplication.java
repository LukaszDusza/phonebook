package devlab.phonebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //@Configuration //@EnableAutoConfiguration //@ComponentScan
public class PhonebookApplication {

    /*
    statyczna metoda, która uruchamia statyczną metodę 'run'klasy SpringApplication.
    Jako parametr przyjmuje naszą klasę uruchomieniową.
     */

    public static void main(String[] args) {
        SpringApplication.run(PhonebookApplication.class, args);

    }

    /* zaczniemy od tworzenia klasy modelowej w naszym projekcie. Aplikacja ma za zadanie gromadzić nasze kontakty do innych osób.
     dla uproszeczenia tutaj będziemy przechowywać imię, nazwisko, numer telefonu, adres jako miejscowość, kategorię kotaktu,
     ranking, czyli coś po czym w przyszłości możemy wyswietlać kontakty w kolejności np. ze względu na częstość używania
     i tagi, po których łatwo wyszukamy konkretną grupę osób.

     Pola te tworzymy głównie dla przykładu relacji pomiędzy encjami w bazie danych.
     Będziemy programować relacje:
     ManyToMany, OneToMany, ManyToOne, OneToOne.

     Pokażę również jak skonfigurować bibliotekę Lombok, która załatwia nam całość standardowego kodu, jaki musimy napisać,
     aby klasa była używalna, czyli getery setery, konstruktory itp.*/

}

