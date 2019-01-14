package devlab.phonebook.repository;

import devlab.phonebook.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TagRepository  extends JpaRepository<Tag, Long> {

}
