package app.constant.interviewslots;

import app.model.candidate.entity.Candidate;
import app.model.candidate.entity.CandidateAvailability;
import app.model.interviewer.entity.Interviewer;
import app.model.interviewer.entity.InterviewerAvailability;
import app.model.interviewslots.model.InterviewSlotsQueryModel;
import app.model.utils.DateTimeSlot;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Test objects.
 */
public class TestObjects {

  public static Candidate prepareCandidateObject() {
    return Candidate.builder().id(1).email("sakshi@gmail.com").name("sakshi").build();
  }

  public static CandidateAvailability createCandidateAvailability() {
    DateTimeSlot dateTimeSlot = DateTimeSlot.builder().day(LocalDate.now())
        .from(LocalTime.of(9, 00)).to(LocalTime.of(12, 00)).id(2).build();
    CandidateAvailability candidateAvailability = CandidateAvailability.builder()
        .availabilitySlotList(dateTimeSlot).candidateModel(prepareCandidateObject())
        .id(3L).build();
    return candidateAvailability;
  }

  public static CandidateAvailability createCandidateAvailability1() {
    DateTimeSlot dateTimeSlot = DateTimeSlot.builder().day(LocalDate.now())
        .from(LocalTime.of(11, 00)).to(LocalTime.of(13, 00)).id(2).build();
    CandidateAvailability candidateAvailability = CandidateAvailability.builder()
        .availabilitySlotList(dateTimeSlot).candidateModel(prepareCandidateObject())
        .id(3L).build();
    return candidateAvailability;
  }

  public static Interviewer prepareInterviewerObject() {
    return Interviewer.builder().id(4).email("A@gmail.com").name("A").build();
  }

  public static InterviewerAvailability createInterviewerAvailability() {
    DateTimeSlot dateTimeSlot = DateTimeSlot.builder().day(LocalDate.now())
        .from(LocalTime.of(11, 00)).to(LocalTime.of(12, 00)).id(5).build();
    InterviewerAvailability interviewerAvailability = InterviewerAvailability.builder()
        .availabilitySlotList(dateTimeSlot).interviewerModel(prepareInterviewerObject())
        .id(6L).build();
    return interviewerAvailability;
  }

  public static InterviewerAvailability createInterviewerAvailability1() {
    DateTimeSlot dateTimeSlot = DateTimeSlot.builder().day(LocalDate.now())
        .from(LocalTime.of(9, 00)).to(LocalTime.of(12, 00)).id(5).build();
    InterviewerAvailability interviewerAvailability = InterviewerAvailability.builder()
        .availabilitySlotList(dateTimeSlot).interviewerModel(prepareInterviewerObject())
        .id(6L).build();
    return interviewerAvailability;
  }

  public static InterviewerAvailability createInterviewerAvailability2() {
    DateTimeSlot dateTimeSlot = DateTimeSlot.builder().day(LocalDate.now())
        .from(LocalTime.of(9, 00)).to(LocalTime.of(14, 00)).id(5).build();
    InterviewerAvailability interviewerAvailability = InterviewerAvailability.builder()
        .availabilitySlotList(dateTimeSlot).interviewerModel(prepareInterviewerObject())
        .id(6L).build();
    return interviewerAvailability;
  }

  public static InterviewSlotsQueryModel getInterviewSlots() {
    List<Integer> interviewIds = new ArrayList<>();
    interviewIds.add(6);
    return InterviewSlotsQueryModel.builder().interviewersIds(interviewIds)
        .candidateId(1).build();
  }
}
