package app.model.interviewer.entity;

import app.constant.ApplicationConstants;
import app.model.utils.DateTimeSlot;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * The type Interviewer availability.
 */
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = ApplicationConstants.INTERVIEWER_AVAILABILITY)
public class InterviewerAvailability {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty(ApplicationConstants.INTERVIEWER_NAME)
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = ApplicationConstants.INTERVIEWER_ID, nullable = true)
    private Interviewer interviewerModel;

    @JsonProperty(ApplicationConstants.AVAILABILITY_SLOT_LIST)
    @OneToOne(cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = ApplicationConstants.AVAILABLE_SLOT, nullable = true)
    private DateTimeSlot availabilitySlotList;
}
