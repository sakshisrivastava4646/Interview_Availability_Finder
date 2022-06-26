package app.service.interviewer;

import app.model.interviewer.entity.InterviewerAvailability;
import app.model.interviewer.model.InterviewerAvailabilityModel;
import app.model.interviewer.model.InterviewerAvailabilityReturnModel;
import app.model.interviewer.entity.Interviewer;
import app.model.interviewer.model.InterviewerModel;
import java.util.List;

public interface InterviewerService {
    InterviewerModel createInterviewer(Interviewer interviewerModel);

    InterviewerAvailabilityModel createInterviewerAvailability(
            InterviewerAvailability interviewerAvailabilityModel);

    List<InterviewerModel> getAllInterviewers();

    InterviewerModel getInterviewerById(String id);

    InterviewerAvailabilityReturnModel getInterviewerAvailabilityById(String id);

    void deleteInterviewerById(String id);
}
