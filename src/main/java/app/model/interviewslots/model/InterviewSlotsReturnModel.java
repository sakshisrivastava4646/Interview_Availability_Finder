package app.model.interviewslots.model;

import app.model.utils.OverlappingDateTimeSlot;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InterviewSlotsReturnModel {
    private  int candidateId;
    private  List<Integer> interviewersIds;
    private  List<OverlappingDateTimeSlot> interviewAvailabilitySlotList;
}
