package devlab.phonebook.repository;

import devlab.phonebook.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    String BY_NAME = "SELECT * FROM contact WHERE name = ?1";

    @Query(value = BY_NAME, nativeQuery = true)
    List<Contact> getContactsByName(String name);
}
