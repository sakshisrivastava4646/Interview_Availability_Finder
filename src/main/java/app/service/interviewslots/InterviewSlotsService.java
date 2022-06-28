package app.service.interviewslots;

import app.model.interviewslots.model.InterviewSlotsQueryModel;
import app.model.interviewslots.model.InterviewSlotsReturnModel;

/**
 * The interface Interview slots service.
 */
public interface InterviewSlotsService {
  /**
   * Gets interview slots.
   * @param interviewSlotsQueryModel the interview slots query model
   * @return the interview slots
   */
  InterviewSlotsReturnModel getInterviewSlots(InterviewSlotsQueryModel interviewSlotsQueryModel);
}
