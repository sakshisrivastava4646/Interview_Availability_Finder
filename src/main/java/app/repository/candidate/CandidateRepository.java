package app.repository.candidate;

import app.model.candidate.entity.Candidate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * The interface Candidate repository.
 */
@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
    /**
     * Gets all names.
     * @return the all names
     */
    @Query(value="select c.name from Candidate c",  nativeQuery = true)
    List<String> getAllNames();

    /**
     * Gets email tocheck exisitng.
     * @param email the email
     * @return the email tocheck exisitng
     */
    @Query(value="select * from Candidate c where c.email= :email",  nativeQuery = true)
    List<String> getEmailTocheckExisitng(String email);
}
