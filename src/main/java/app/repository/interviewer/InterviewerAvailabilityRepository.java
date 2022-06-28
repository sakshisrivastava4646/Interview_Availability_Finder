package app.repository.interviewer;

import app.model.interviewer.entity.InterviewerAvailability;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * The interface Interviewer availability repository.
 */
@Repository
public interface InterviewerAvailabilityRepository extends JpaRepository<InterviewerAvailability, Long> {
  /**
   * Gets interviewer availability by interviewer id.
   * @param interviewerId the interviewer id
   * @return the interviewer availability by interviewer id
   */
  @Query(value="select * from interviewer_availability ia where ia.interviewer_id = :interviewerId",   nativeQuery = true)
    List<InterviewerAvailability> getInterviewerAvailabilityByInterviewerId(String interviewerId);

  /**
   * Delete interviewer availability.
   * @param interviewerId the interviewer id
   */
  @Modifying
    @Query(value = "delete from interviewer_availability ia where ia.interviewer_id = :interviewerId", nativeQuery = true)
    void deleteInterviewerAvailability(String interviewerId);
}
