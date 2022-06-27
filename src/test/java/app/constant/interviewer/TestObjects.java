package app.constant.interviewer;

import app.model.candidate.entity.Candidate;
import app.model.candidate.entity.CandidateAvailability;
import app.model.interviewer.entity.Interviewer;
import app.model.interviewer.entity.InterviewerAvailability;
import app.model.utils.DateTimeSlot;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The type Test objects.
 */
public class TestObjects {

  public static Interviewer prepareInterviewerObject() {
    return Interviewer.builder().id(1).email("sakshi@gmail.com").name("sakshi").build();
  }

  public static Interviewer createInterviewerwithNameError() {
    return Interviewer.builder().id(1).email("sakshi@gmail.com").build();
  }

  public static Interviewer createInterviewerwithUniqueEmailError() {
    return Interviewer.builder().id(1).name("sakshi").build();
  }

  public static InterviewerAvailability createInterviewerAvailability() {
    DateTimeSlot dateTimeSlot = DateTimeSlot.builder().day(LocalDate.now())
        .from(LocalTime.of(9, 00)).to(LocalTime.of(12, 00)).build();
    InterviewerAvailability interviewerAvailability = InterviewerAvailability.builder().availabilitySlotList(dateTimeSlot).interviewerModel(prepareInterviewerObject())
        .id(2L).build();
    return interviewerAvailability;
  }

  public static InterviewerAvailability createInterviewerAvailabilityWithTimeError() {
    DateTimeSlot dateTimeSlot = DateTimeSlot.builder().day(LocalDate.now())
        .from(LocalTime.of(9, 00)).to(LocalTime.of(1, 00)).build();
    InterviewerAvailability interviewerAvailability = InterviewerAvailability.builder()
        .availabilitySlotList(dateTimeSlot).interviewerModel(TestObjects.prepareInterviewerObject())
        .id(2L).build();
    return interviewerAvailability;
  }

  public static InterviewerAvailability createInterviewerAvailabilityWithTimeError1() {
    DateTimeSlot dateTimeSlot = DateTimeSlot.builder().day(LocalDate.now())
        .from(LocalTime.of(9, 30)).to(LocalTime.of(12, 50)).build();
    InterviewerAvailability interviewerAvailability = InterviewerAvailability.builder()
        .availabilitySlotList(dateTimeSlot).interviewerModel(TestObjects.prepareInterviewerObject())
        .id(2L).build();
    return interviewerAvailability;
  }

}
