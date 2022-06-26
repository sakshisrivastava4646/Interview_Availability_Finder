package app.repository.candidate;

import app.model.candidate.entity.Candidate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
    @Query(value="select c.name from Candidate c",  nativeQuery = true)
    List<String> getAllNames();

    @Query(value="select c.name from Candidate c where c.email= ':email'",  nativeQuery = true)
    List<String> getEmailTocheckExisitng(String email);
}
