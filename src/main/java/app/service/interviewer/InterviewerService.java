package app.service.interviewer;

import app.model.interviewer.entity.InterviewerAvailability;
import app.model.interviewer.model.InterviewerAvailabilityModel;
import app.model.interviewer.model.InterviewerAvailabilityReturnModel;
import app.model.interviewer.entity.Interviewer;
import app.model.interviewer.model.InterviewerModel;
import java.util.List;

/**
 * The interface Interviewer service.
 */
public interface InterviewerService {
  /**
   * Create interviewer interviewer model.
   * @param interviewerModel the interviewer model
   * @return the interviewer model
   */
  InterviewerModel createInterviewer(Interviewer interviewerModel);

  /**
   * Create interviewer availability interviewer availability model.
   * @param interviewerAvailabilityModel the interviewer availability model
   * @return the interviewer availability model
   */
  InterviewerAvailabilityModel createInterviewerAvailability(
            InterviewerAvailability interviewerAvailabilityModel);

  /**
   * Gets all interviewers.
   * @return the all interviewers
   */
  List<InterviewerModel> getAllInterviewers();

  /**
   * Gets interviewer by id.
   * @param id the id
   * @return the interviewer by id
   */
  InterviewerModel getInterviewerById(String id);

  /**
   * Gets interviewer availability by id.
   * @param id the id
   * @return the interviewer availability by id
   */
  InterviewerAvailabilityReturnModel getInterviewerAvailabilityById(String id);

  /**
   * Delete interviewer by id.
   * @param id the id
   */
  void deleteInterviewerById(String id);
}
