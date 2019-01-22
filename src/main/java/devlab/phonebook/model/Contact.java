package devlab.phonebook.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data //Adnotacja @Data generuje settery, gettery, oraz metody z klasy Object.
@AllArgsConstructor
@NoArgsConstructor
// Adnotacja, która określa, czy dana klasa jest encją bazodanową.
// Hibernate zmapuje tą klasę  i stworzy jej odzwierciedlenie w bazie danych.
// pola klasy , to będą kolumny.
@Entity
@Table(name = "contact") //adnotacja nie jesy wymagana, gdyż automatycznie hibernate tworząc nazwę tabeli nazwie ją tak samo jak klasa, lecz z małej litery.
public class Contact {


    // Adnotacja Id określa, że to pole będzie unikalne oraz, że jest to klucz główny w tabeli.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //strategia generowania kolejnych numerów Id podczas dodawania rekordów do bazy danych.
    private Long id;

    @Column(name = "name") //adnotacja określająca nazwę kolumny oraz dodatkowe ustawienia, np. unikalność, brak nulla, maksym. długość znaków itp.
    private String name;
    private String surname;
    private String number;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "fk_address")
    private Address address;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "fk_category")
    private Category category;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "fk_ranking")
    private Ranking ranking;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinTable(name = "contact_tag",
            joinColumns = @JoinColumn(name = "contact_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();


}
