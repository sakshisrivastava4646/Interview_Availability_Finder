package app.model.candidate.model;

import app.constant.ApplicationConstants;
import app.model.utils.model.DateTimeSlotModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidateAvailabilityModel {

    private Long id;

    @JsonProperty(ApplicationConstants.CANDIDATE_NAME)
    private CandidateModel candidateModel;

    @JsonProperty(ApplicationConstants.AVAILABILITY_SLOT_LIST)
    private DateTimeSlotModel availabilitySlotList;

}
