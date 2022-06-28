package app.service.interviewslots;

import app.constant.ExceptionConstants;
import app.exception.BusinessException;
import app.model.candidate.entity.CandidateAvailability;
import app.model.candidate.entity.Candidate;
import app.model.interviewer.entity.InterviewerAvailability;
import app.model.interviewer.entity.Interviewer;
import app.model.interviewslots.model.InterviewSlotsQueryModel;
import app.model.interviewslots.model.InterviewSlotsReturnModel;
import app.model.utils.OverlappingDateTimeSlot;
import app.repository.candidate.CandidateAvailabilityRepository;
import app.repository.candidate.CandidateRepository;
import app.repository.interviewer.InterviewerAvailabilityRepository;
import app.repository.interviewer.InterviewerRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Interview slots service.
 */
@Service
@Transactional
public class InterviewSlotsServiceImpl implements InterviewSlotsService {
  /**
   * The Candidate repository.
   */
  @Autowired
    CandidateRepository candidateRepository;

  /**
   * The Candidate availability repository.
   */
  @Autowired
    CandidateAvailabilityRepository candidateAvailabilityRepository;

  /**
   * The Interviewer repository.
   */
  @Autowired
    InterviewerRepository interviewerRepository;

  /**
   * The Interviewer availability repository.
   */
  @Autowired
    InterviewerAvailabilityRepository interviewerAvailabilityRepository;

  @Override
  public InterviewSlotsReturnModel getInterviewSlots(InterviewSlotsQueryModel interviewSlotsQueryModel) {
    verifyCandidateAndInterviewersExist(interviewSlotsQueryModel);

    int candidateId = interviewSlotsQueryModel.getCandidateId();
    List<Integer> interviewersIds = interviewSlotsQueryModel.getInterviewersIds();
    List<OverlappingDateTimeSlot> interviewAvailabilitySlots = getInterviewAvailabilitySlots(interviewSlotsQueryModel);

    return
        InterviewSlotsReturnModel.builder().candidateId(candidateId).interviewersIds(interviewersIds)
                .interviewAvailabilitySlotList(interviewAvailabilitySlots).build();

  }

  private void verifyCandidateAndInterviewersExist(InterviewSlotsQueryModel interviewSlotsQueryModel) {
    int candidateId = interviewSlotsQueryModel.getCandidateId();
    List<Integer> interviewersIds = interviewSlotsQueryModel.getInterviewersIds();

    verifyCandidateExists(candidateId);
    verifyInterviewersExist(interviewersIds);
  }

  private void verifyCandidateExists(int candidateId) {
    Optional<Candidate> existingCandidate = candidateRepository.findById(candidateId);

    if (existingCandidate.isEmpty()) {
      throw new BusinessException(ExceptionConstants.CANDIDATE_DOES_NOT_EXIST, String.valueOf(candidateId));
    }
  }

  private void verifyInterviewersExist(List<Integer> interviewersIds) {
    for (int interviewer : interviewersIds) {
      Optional<Interviewer> existingInterviewer = interviewerRepository.findById(interviewer);

      if (existingInterviewer.isEmpty()) {
        throw new BusinessException(ExceptionConstants.INTERVIEWER_DOES_NOT_EXIST, String.valueOf(interviewer));
      }
    }
  }

  private List<OverlappingDateTimeSlot> getInterviewAvailabilitySlots(InterviewSlotsQueryModel interviewSlotsQueryModel) {
    int candidateId = interviewSlotsQueryModel.getCandidateId();
    List<CandidateAvailability> candidateAvailability = getCandidateAvailability(candidateId);

    List<Integer> interviewersIds = interviewSlotsQueryModel.getInterviewersIds();

    Map<Integer, List<InterviewerAvailability>> interviewersAvailabilitiesMap = new HashMap<>();

    for (int interviewer : interviewersIds) {
      List<InterviewerAvailability> interviewerAvailability = getInterviewerAvailability(interviewer);

      interviewersAvailabilitiesMap.put(interviewer, interviewerAvailability);
    }

    return
        getCandidateAndInterviewersAvailabilitiesCommonDays(
            candidateAvailability,
            interviewersAvailabilitiesMap);

  }


  private List<CandidateAvailability> getCandidateAvailability(int candidateId) {
    List<CandidateAvailability> candidateAvailability =
            candidateAvailabilityRepository.candidateAvailabilityByCandidateId(String.valueOf(candidateId));

    if (candidateAvailability.isEmpty()) {
        throw new BusinessException(ExceptionConstants.CANDIDATE_HAS_NO_AVAILABILITY_DEFINED, String.valueOf(candidateId));
    }

    return candidateAvailability;
  }

  private List<InterviewerAvailability> getInterviewerAvailability(int interviewerId) {
    List<InterviewerAvailability> interviewerAvailability = interviewerAvailabilityRepository
            .getInterviewerAvailabilityByInterviewerId(String.valueOf(interviewerId));

    if (interviewerAvailability.isEmpty()) {
        throw new BusinessException(ExceptionConstants.INTERVIEWER_HAS_NO_AVAILABILITY_DEFINED, String.valueOf(interviewerId));
    }

    return interviewerAvailability;
  }

  private List<OverlappingDateTimeSlot> getCandidateAndInterviewersAvailabilitiesCommonDays(
      List<CandidateAvailability> candidateAvailability,
      Map<Integer, List<InterviewerAvailability>> interviewersAvailabilitiesMap) {

    List<OverlappingDateTimeSlot> overlappingDateTimeSlot = new ArrayList<>();

    int i;
    LocalTime from;
    LocalTime to;

    for(CandidateAvailability candAvailability: candidateAvailability){
      LocalDate candidateAvailabilityDate = candAvailability.getAvailabilitySlotList().getDay();
      i=0;
      from =null;
      to=null;
      calculateInterviewerAvailability(interviewersAvailabilitiesMap, overlappingDateTimeSlot, i, from, to, candAvailability, candidateAvailabilityDate);
    }
    return overlappingDateTimeSlot;
  }
  private void calculateInterviewerAvailability(Map<Integer, List<InterviewerAvailability>> interviewersAvailabilitiesMap, List<OverlappingDateTimeSlot> overlappingDateTimeSlot, int i, LocalTime from, LocalTime to,
                                                CandidateAvailability candAvailability, LocalDate candidateAvailabilityDate) {
    OverlappingDateTimeSlot x;
    for (Entry<Integer, List<InterviewerAvailability>> entry: interviewersAvailabilitiesMap.entrySet()) {
      List<InterviewerAvailability> interviewerAvailabilityList = entry.getValue();
      for (InterviewerAvailability interviewerAvailability : interviewerAvailabilityList) {
        LocalDate interviewerAvailabilityDate = interviewerAvailability.getAvailabilitySlotList().getDay();
        if (candidateAvailabilityDate.compareTo(interviewerAvailabilityDate) == 0) {
          x = getOverlappingDateTimeSlot(from, to, candAvailability, candidateAvailabilityDate, interviewerAvailability);
          if (x.getFrom() != null && x.getTo() != null) {
            i = i + 1;
            from = x.getFrom();
            to = x.getTo();
          }
          addingCandInterviewerAvailability(interviewersAvailabilitiesMap, overlappingDateTimeSlot, i, x);
        }
      }
    }
  }
  private void addingCandInterviewerAvailability(Map<Integer, List<InterviewerAvailability>> interviewersAvailabilitiesMap, List<OverlappingDateTimeSlot> overlappingDateTimeSlot, int i, OverlappingDateTimeSlot x) {
    if(i == interviewersAvailabilitiesMap.size() && x.getFrom() != null && x.getTo() !=null){
      overlappingDateTimeSlot.add(x);
    }
  }

  /**
   * Gets overlapping date time slot.
   * @param from                      the from
   * @param to                        the to
   * @param candAvailability          the cand availability
   * @param candidateAvailabilityDate the candidate availability date
   * @param interviewerAvailability   the interviewer availability
   * @return the overlapping date time slot
   */
  public OverlappingDateTimeSlot getOverlappingDateTimeSlot(LocalTime from, LocalTime to, CandidateAvailability candAvailability, LocalDate candidateAvailabilityDate,
                                                             InterviewerAvailability interviewerAvailability) {
    OverlappingDateTimeSlot x;

    if (from != null && to != null) {
      x = getOverlappingTimeSlot(from, to,
          interviewerAvailability.getAvailabilitySlotList().getFrom(),
          interviewerAvailability.getAvailabilitySlotList().getTo(),
          candidateAvailabilityDate);
    } else {

      x = getOverlappingTimeSlot(candAvailability.getAvailabilitySlotList().getFrom(),
          candAvailability.getAvailabilitySlotList().getTo(),
          interviewerAvailability.getAvailabilitySlotList().getFrom(),
          interviewerAvailability.getAvailabilitySlotList().getTo(),
          candidateAvailabilityDate);
    }

    return x;
  }

  private OverlappingDateTimeSlot getOverlappingTimeSlot(LocalTime candidateFrom, LocalTime candidateTo, LocalTime interviewerFrom,
                                                         LocalTime interviewerTo, LocalDate overlappingDate) {
    OverlappingDateTimeSlot overlappingDateTimeSlot = new OverlappingDateTimeSlot();
    LocalTime overlapTimeSlotFrom;
    LocalTime overlapTimeSlotTo;

    if (candidateFrom.isBefore(interviewerTo) && interviewerFrom.isBefore(candidateTo)) {
      if (candidateFrom.isBefore(interviewerFrom)) {
        overlapTimeSlotFrom = interviewerFrom;
      } else {
        overlapTimeSlotFrom = candidateFrom;
      }

      if (candidateTo.isBefore(interviewerTo)) {
        overlapTimeSlotTo = candidateTo;
      } else {
        overlapTimeSlotTo = interviewerTo;
      }

      overlappingDateTimeSlot = OverlappingDateTimeSlot.builder().from(overlapTimeSlotFrom).to(overlapTimeSlotTo)
          .day(overlappingDate).build();

    }

    return overlappingDateTimeSlot;
  }
}
