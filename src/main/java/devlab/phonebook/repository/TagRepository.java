package devlab.phonebook.repository;

import devlab.phonebook.model.Contact;
import devlab.phonebook.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface TagRepository  extends JpaRepository<Tag, Long> {


    @Query(value = "SELECT * FROM tag WHERE title = ?1", nativeQuery = true)
    Optional<Tag> findByTitle(String title);



}
