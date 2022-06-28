package app.model.interviewer.model;

import app.model.interviewer.entity.Interviewer;
import app.model.utils.DateTimeSlot;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Interviewer availability return model.
 */
@Getter
@Setter
@Builder
public class InterviewerAvailabilityReturnModel {

    private Long id;

    private Interviewer interviewerModel;

    private List<DateTimeSlot> availabilitySlotList;
}
