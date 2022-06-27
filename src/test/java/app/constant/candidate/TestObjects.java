package app.constant.candidate;

import app.model.candidate.entity.Candidate;
import app.model.candidate.entity.CandidateAvailability;
import app.model.interviewer.entity.Interviewer;
import app.model.interviewer.entity.InterviewerAvailability;
import app.model.utils.DateTimeSlot;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The type Test objects.
 */
public class TestObjects {

  public static Candidate prepareCandidateObject() {
    return Candidate.builder().id(1).email("sakshi@gmail.com").name("sakshi").build();
  }

  public static Candidate createCandidatewithNameError() {
    return Candidate.builder().id(1).email("sakshi@gmail.com").build();
  }

  public static Candidate createCandidatewithUniqueEmailError() {
    return Candidate.builder().id(1).name("sakshi").build();
  }

  public static CandidateAvailability createCandidateAvailability() {
    DateTimeSlot dateTimeSlot = DateTimeSlot.builder().day(LocalDate.now())
        .from(LocalTime.of(9, 00)).to(LocalTime.of(12, 00)).build();
    CandidateAvailability candidateAvailability = CandidateAvailability.builder()
        .availabilitySlotList(dateTimeSlot).candidateModel(prepareCandidateObject())
        .id(2L).build();
    return candidateAvailability;
  }

  public static CandidateAvailability createCandidateAvailabilityWithExistingAvailability() {
    DateTimeSlot dateTimeSlot1 = DateTimeSlot.builder().day(LocalDate.now())
        .from(LocalTime.of(11, 00)).to(LocalTime.of(12, 00)).build();
    CandidateAvailability candidateAvailability1 = CandidateAvailability.builder()
        .availabilitySlotList(dateTimeSlot1).candidateModel(TestObjects.prepareCandidateObject())
        .id(3L).build();
    return candidateAvailability1;
  }

  public static CandidateAvailability createCandidateAvailabilityWithTimeError() {
    DateTimeSlot dateTimeSlot = DateTimeSlot.builder().day(LocalDate.now())
        .from(LocalTime.of(9, 00)).to(LocalTime.of(1, 00)).build();
    CandidateAvailability candidateAvailability = CandidateAvailability.builder()
        .availabilitySlotList(dateTimeSlot).candidateModel(TestObjects.prepareCandidateObject())
        .id(2L).build();
    return candidateAvailability;
  }

  public static CandidateAvailability createCandidateAvailabilityWithTimeError1() {
    DateTimeSlot dateTimeSlot = DateTimeSlot.builder().day(LocalDate.now())
        .from(LocalTime.of(9, 30)).to(LocalTime.of(12, 50)).build();
    CandidateAvailability candidateAvailability = CandidateAvailability.builder().availabilitySlotList(dateTimeSlot).candidateModel(TestObjects.prepareCandidateObject())
        .id(2L).build();
    return  candidateAvailability;
  }

}
