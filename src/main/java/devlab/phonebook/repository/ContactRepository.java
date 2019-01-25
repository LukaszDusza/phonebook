package devlab.phonebook.repository;

import devlab.phonebook.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// interfejs repozytorium, który rozszerza klasę JpaRepository pochodzącą ze Spring Data.
// Klasa ta daje nam gotową funkcjonalość CRUD, czyli dostacza metody do zarzadzania rekordami w bazie danych.
// Min. kasowanie, wyszkiwanie oraz dodawanie rekordów.

// Adnotacja nie jest wymagana, natomiast oznaczenie tego interfejsu jako repozytorium pozwala nam min.
// otrzymywać automatyczne tłumaczenie wyjątków.
@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    // w interfejsach każda zmienna jest publiczna, statyczna i finalna.
    // nie ma potrzeby oznaczania jako 'public static final'.
    // tą statyczną zmienną wykorzystujemy w adnotacji @query jako natywne zapytanie SQL.
    String BY_NAME = "SELECT * FROM contact WHERE name = ?1";

    //adnotacja ta pozwala samodzielnie generować zapytanie SQL.
    // bez opcji 'nativeQuery' możemy posługiwać się JPQL, czyli Java Persistance Query Language
    // przy włączonej adnotacji na true, możemy skorzystać z natywnych zapytań SQL.
    @Query(value = BY_NAME, nativeQuery = true)
    List<Contact> getContactsByName(String name);

    // w tym przykładzie dodaliśmy do naszego interfejsu dodatkową metodę zwracającą listę kontaktów,
    // gdzie parametrem wyszukwania jest imię.


    @Query(value = "SELECT * FROM contact WHERE number = ?1", nativeQuery = true)
    Optional<Contact> findContactByPhoneNumber(String phone);

    @Query(value = "SELECT * FROM contact WHERE fk_category = (select id from category where title = ?1)", nativeQuery = true)
    List<Contact> findContactsByCategory(String tile);

    @Query(value = "select contact.id,\n" +
            "       contact.name,\n" +
            "       contact.number,\n" +
            "       contact.surname,\n" +
            "       contact.fk_address,\n" +
            "       contact.fk_category,\n" +
            "       contact.fk_ranking\n" +
            "from contact\n" +
            "       join contact_tag on contact.id = contact_tag.contact_id\n" +
            "       join tag on contact_tag.tag_id = tag.id\n" +
            "where tag.id = (select id from tag where title = ?1)", nativeQuery = true)
    List<Contact> getContactsByTag(String title);

    @Query(value = "SELECT c FROM Contact c WHERE c.number =  ?1")
    Optional<Contact> findContactByNumber(String number);

    @Query(value = "SELECT c FROM Contact c WHERE c.number =  ?1")
    List<Contact> findContactsByNumber(String number);

}
