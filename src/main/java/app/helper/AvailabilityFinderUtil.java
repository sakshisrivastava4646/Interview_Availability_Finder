package app.helper;

import app.model.candidate.entity.Candidate;
import app.model.candidate.entity.CandidateAvailability;
import app.model.candidate.model.CandidateAvailabilityModel;
import app.model.candidate.model.CandidateModel;
import app.model.interviewer.entity.Interviewer;
import app.model.interviewer.entity.InterviewerAvailability;
import app.model.interviewer.model.InterviewerAvailabilityModel;
import app.model.interviewer.model.InterviewerModel;
import app.model.utils.DateTimeSlot;
import app.model.utils.model.DateTimeSlotModel;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

/**
 * The type Invoice util.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class AvailabilityFinderUtil {

  /**
   * Convert candidate model to entity candidate.
   * @param candidateModel the candidate model
   * @return the candidate
   */
  public static Candidate convertCandidateModelToEntity(CandidateModel candidateModel){
    Candidate candidate = new Candidate();
    BeanUtils.copyProperties(candidateModel, candidate);
    return candidate;
  }

  /**
   * Convert candidate entity to model candidate model.
   * @param candidate the candidate
   * @return the candidate model
   */
  public static CandidateModel convertCandidateEntityToModel(Candidate candidate){
    CandidateModel candidateModel = new CandidateModel();
    BeanUtils.copyProperties(candidate, candidateModel );
    return candidateModel;
  }

  /**
   * Convert candidate entity to model list list.
   * @param candidateList the candidate list
   * @return the list
   */
  public static List<CandidateModel> convertCandidateEntityToModelList(List<Candidate> candidateList){
    List<CandidateModel> candidateModelList ;
    candidateModelList = candidateList.stream().map(
        AvailabilityFinderUtil::convertCandidateEntityToModel
    ).collect(Collectors.toList());
    return candidateModelList;
  }

  /**
   * Convert candidate availability model to entity candidate availability.
   * @param candidateAvailabilityModel the candidate availability model
   * @return the candidate availability
   */
  public static CandidateAvailability convertCandidateAvailabilityModelToEntity(CandidateAvailabilityModel candidateAvailabilityModel){
    CandidateAvailability candidateAvailability = new CandidateAvailability();
    BeanUtils.copyProperties(candidateAvailabilityModel, candidateAvailability);

    Candidate candidate = new Candidate();
    BeanUtils.copyProperties( candidateAvailabilityModel.getCandidateModel(), candidate);
    DateTimeSlot dateTimeSlot = new DateTimeSlot();
    BeanUtils.copyProperties(candidateAvailabilityModel.getAvailabilitySlotList(), dateTimeSlot);

    return CandidateAvailability.builder().candidateModel(candidate)
        .availabilitySlotList(dateTimeSlot).id(candidateAvailabilityModel.getId()).build();

  }

  /**
   * Convert candidate availability entity to model candidate availability model.
   * @param candidateAvailability the candidate availability
   * @return the candidate availability model
   */
  public static CandidateAvailabilityModel convertCandidateAvailabilityEntityToModel(CandidateAvailability candidateAvailability){
    CandidateAvailabilityModel candidateAvailabilityModel = new CandidateAvailabilityModel();
    BeanUtils.copyProperties(candidateAvailability, candidateAvailabilityModel );

    CandidateModel candidateModel = new CandidateModel();
    BeanUtils.copyProperties(candidateAvailability.getCandidateModel(), candidateModel);
    DateTimeSlotModel dateTimeSlotModel = new DateTimeSlotModel();
    BeanUtils.copyProperties(candidateAvailability.getAvailabilitySlotList(), dateTimeSlotModel);

    return CandidateAvailabilityModel.builder().candidateModel(candidateModel)
        .availabilitySlotList(dateTimeSlotModel).id(candidateAvailability.getId()).build();

  }

  // Interviewer object conversion starts

  /**
   * Convert interviewer model to entity interviewer.
   * @param interviewerModel the interviewer model
   * @return the interviewer
   */
  public static Interviewer convertInterviewerModelToEntity(InterviewerModel interviewerModel){
    Interviewer interviewer = new Interviewer();
    BeanUtils.copyProperties(interviewerModel, interviewer);
    return interviewer;
  }

  /**
   * Convert interviewer entity to model interviewer model.
   * @param interviewer the interviewer
   * @return the interviewer model
   */
  public static InterviewerModel convertInterviewerEntityToModel(Interviewer interviewer){
    InterviewerModel interviewerModel = new InterviewerModel();
    BeanUtils.copyProperties(interviewer, interviewerModel );
    return interviewerModel;
  }

  /**
   * Convert interviewer entity to model list list.
   * @param interviewerList the interviewer list
   * @return the list
   */
  public static List<InterviewerModel> convertInterviewerEntityToModelList(List<Interviewer> interviewerList){
    List<InterviewerModel> interviewerModelList ;
    interviewerModelList = interviewerList.stream().map(
        AvailabilityFinderUtil::convertInterviewerEntityToModel
    ).collect(Collectors.toList());
    return interviewerModelList;
  }

  /**
   * Convert interviewer availability model to entity interviewer availability.
   * @param interviewerAvailabilityModel the interviewer availability model
   * @return the interviewer availability
   */
  public static InterviewerAvailability convertInterviewerAvailabilityModelToEntity(InterviewerAvailabilityModel interviewerAvailabilityModel){
    InterviewerAvailability interviewerAvailability = new InterviewerAvailability();
    BeanUtils.copyProperties(interviewerAvailabilityModel, interviewerAvailability);

    Interviewer interviewer = new Interviewer();
    BeanUtils.copyProperties( interviewerAvailabilityModel.getInterviewerModel(), interviewer);
    DateTimeSlot dateTimeSlot = new DateTimeSlot();
    BeanUtils.copyProperties(interviewerAvailabilityModel.getAvailabilitySlotList(), dateTimeSlot);

    return InterviewerAvailability.builder().interviewerModel(interviewer)
        .availabilitySlotList(dateTimeSlot).id(interviewerAvailabilityModel.getId()).build();

  }

  /**
   * Convert interviewer availability entity to model interviewer availability model.
   * @param interviewerAvailability the interviewer availability
   * @return the interviewer availability model
   */
  public static InterviewerAvailabilityModel convertInterviewerAvailabilityEntityToModel(InterviewerAvailability interviewerAvailability){
    InterviewerAvailabilityModel interviewerAvailabilityModel = new InterviewerAvailabilityModel();
    BeanUtils.copyProperties(interviewerAvailability, interviewerAvailabilityModel );

    InterviewerModel interviewerModel = new InterviewerModel();
    BeanUtils.copyProperties(interviewerAvailability.getInterviewerModel(), interviewerModel);
    DateTimeSlotModel dateTimeSlotModel = new DateTimeSlotModel();
    BeanUtils.copyProperties(interviewerAvailability.getAvailabilitySlotList(), dateTimeSlotModel);

    return InterviewerAvailabilityModel.builder().interviewerModel(interviewerModel)
        .availabilitySlotList(dateTimeSlotModel).id(interviewerAvailability.getId()).build();

  }

}