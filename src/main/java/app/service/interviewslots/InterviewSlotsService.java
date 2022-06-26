package app.service.interviewslots;

import app.model.interviewslots.model.InterviewSlotsQueryModel;
import app.model.interviewslots.model.InterviewSlotsReturnModel;

public interface InterviewSlotsService {
    InterviewSlotsReturnModel getInterviewSlots(InterviewSlotsQueryModel interviewSlotsQueryModel);
}
