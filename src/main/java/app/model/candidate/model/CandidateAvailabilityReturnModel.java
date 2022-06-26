package app.model.candidate.model;

import app.model.candidate.entity.Candidate;
import app.model.utils.DateTimeSlot;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CandidateAvailabilityReturnModel {

    private Long id;

    private Candidate candidateModel;

    private List<DateTimeSlot> availabilitySlotList;

}
