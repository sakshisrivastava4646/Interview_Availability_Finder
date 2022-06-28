package app.repository.candidate;

import app.model.candidate.entity.CandidateAvailability;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * The interface Candidate availability repository.
 */
@Repository
public interface CandidateAvailabilityRepository extends JpaRepository<CandidateAvailability, Long> {
  /**
   * Candidate availability by candidate id list.
   * @param candidateId the candidate id
   * @return the list
   */
  @Query(value="select * from candidate_availability ca where ca.candidate_id = :candidateId",   nativeQuery = true)
    List<CandidateAvailability> candidateAvailabilityByCandidateId(String candidateId);

  /**
   * Delete candidate availability.
   * @param candidateId the candidate id
   */
  @Modifying
    @Query(value = "delete from candidate_availability ca where ca.candidate_id = :candidateId", nativeQuery = true)
    void deleteCandidateAvailability(String candidateId);

}
