package app.model.interviewer.model;



import app.constant.ApplicationConstants;
import app.model.utils.model.DateTimeSlotModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Interviewer availability model.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InterviewerAvailabilityModel {

    private Long id;

    @JsonProperty(ApplicationConstants.INTERVIEWER_NAME)
    private InterviewerModel interviewerModel;

    @JsonProperty(ApplicationConstants.AVAILABILITY_SLOT_LIST)
    private DateTimeSlotModel availabilitySlotList;
}
