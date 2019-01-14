package devlab.phonebook.repository;

import devlab.phonebook.model.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RankingRepository extends JpaRepository<Ranking, Long> {

    Optional<Ranking> findByNumber(int number);
}
