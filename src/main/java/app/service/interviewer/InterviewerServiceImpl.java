package app.service.interviewer;

import app.constant.ApplicationConstants;
import app.constant.ExceptionConstants;
import app.exception.BusinessException;
import app.helper.AvailabilityFinderUtil;
import app.model.interviewer.entity.InterviewerAvailability;
import app.model.interviewer.model.InterviewerAvailabilityModel;
import app.model.interviewer.model.InterviewerAvailabilityReturnModel;
import app.model.interviewer.entity.Interviewer;
import app.model.interviewer.model.InterviewerModel;
import app.model.utils.DateTimeSlot;
import app.repository.DateTimeRepository;
import app.repository.interviewer.InterviewerAvailabilityRepository;
import app.repository.interviewer.InterviewerRepository;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Interviewer service.
 */
@Service
@Slf4j
//@RequiredArgsConstructor
@Transactional
public class InterviewerServiceImpl implements InterviewerService {

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

  /**
   * The Date time repository.
   */
  @Autowired
  DateTimeRepository dateTimeRepository;

  //************** START CREATING Interviewer **************
  @Override
  public InterviewerModel createInterviewer(Interviewer interviewerModel) {
    verifyValidityOfInterviewer(interviewerModel);
    Interviewer interviewer = interviewerRepository.save(interviewerModel);
    return AvailabilityFinderUtil.convertInterviewerEntityToModel(interviewer);
  }

  private void verifyValidityOfInterviewer(Interviewer interviewerModel) {
    verifyNameIsFilled(interviewerModel);
    verifyUniqueEmail(interviewerModel);
    verifyUniqueName(interviewerModel);
  }

  private void verifyUniqueEmail(Interviewer interviewerModel) {
    String emailOfInterviewerToBeCreated = interviewerModel.getEmail();
    List<String> existingEmails = interviewerRepository.getEmailTocheckExisitng(emailOfInterviewerToBeCreated);

    if (!existingEmails.isEmpty()) {
      throw new BusinessException(ExceptionConstants.EMAIL_ALREADY_EXISTS, interviewerModel.getEmail());
    }
  }

  private void verifyNameIsFilled(Interviewer interviewerModel) {
    String nameOfInterviewerToBeCreated = interviewerModel.getName();

    if (nameOfInterviewerToBeCreated == null || nameOfInterviewerToBeCreated.isBlank()) {
      throw new BusinessException(ExceptionConstants.NAME_REQUIRED,
          interviewerModel.getName() != null ? interviewerModel.getName() : null);
    }
  }

  private void verifyUniqueName(Interviewer interviewerModel) {
    String nameOfInterviewerToBeCreated = interviewerModel.getName();
    List<String> existingNames = interviewerRepository.getAllNames();

    if (existingNames.contains(nameOfInterviewerToBeCreated)) {
      throw new BusinessException(ExceptionConstants.NAME_ALREADY_EXISTS, interviewerModel.getName());
    }
  }

  //************* END CREATING Interviewer **************

  //************* START ADD Interviewer AVAILABILITY ***********
  @Override
  public InterviewerAvailabilityModel createInterviewerAvailability(InterviewerAvailability interviewerAvailabilityModel) {
    InterviewerAvailability interviewerAvailability;
    AtomicBoolean existingFlag = new AtomicBoolean(false);

    verifyValidityOfInterviewerAvailability(interviewerAvailabilityModel);

    List<InterviewerAvailability> interviewerExistingAvailability = verifyIfInterviewerHasAvailabilityCreated(
        interviewerAvailabilityModel);

    if(interviewerExistingAvailability.isEmpty()) {
      interviewerAvailability = interviewerAvailabilityRepository.save(interviewerAvailabilityModel);
    }
    else{
      interviewerExistingAvailability.forEach(existingAvailability ->
        setExistingFlagForInterviewerAvailability(interviewerAvailabilityModel, existingFlag, existingAvailability)
      );

      if(existingFlag.toString().equals(ApplicationConstants.FALSE)) {
        interviewerAvailability = interviewerAvailabilityRepository.save(interviewerAvailabilityModel);
      }
      else{
        throw new BusinessException(ExceptionConstants.AVAILABILITY_ALREADY_EXISTS, String.valueOf(interviewerAvailabilityModel));
      }
    }return AvailabilityFinderUtil.convertInterviewerAvailabilityEntityToModel(interviewerAvailability);
  }

  private void setExistingFlagForInterviewerAvailability(InterviewerAvailability interviewerAvailabilityModel, AtomicBoolean existingFlag, InterviewerAvailability existingAvailability) {
    if(checkExistingAvailability(interviewerAvailabilityModel, existingAvailability)){
      existingFlag.set(true);
    }else{
      existingFlag.set(false);
    }
  }
  private boolean checkExistingAvailability(InterviewerAvailability interviewerAvailabilityModel, InterviewerAvailability existingAvailability) {
    return existingAvailability.getAvailabilitySlotList().getDay().equals(interviewerAvailabilityModel.getAvailabilitySlotList().getDay()) &&
        existingAvailability.getAvailabilitySlotList().getFrom().equals(interviewerAvailabilityModel.getAvailabilitySlotList().getFrom()) &&
        existingAvailability.getAvailabilitySlotList().getTo().equals(interviewerAvailabilityModel.getAvailabilitySlotList().getTo());
  }

  private void verifyValidityOfInterviewerAvailability(InterviewerAvailability interviewerAvailabilityModel) {
    verifyInterviewerExists(interviewerAvailabilityModel);
    verifyPeriodOfAvailabilityIsValid(interviewerAvailabilityModel);
  }

  private List<InterviewerAvailability> verifyIfInterviewerHasAvailabilityCreated(
      InterviewerAvailability interviewerAvailabilityModel) {
    String interviewerId = String.valueOf(interviewerAvailabilityModel.getInterviewerModel().getId());

    return interviewerAvailabilityRepository.getInterviewerAvailabilityByInterviewerId(interviewerId);
  }


  private void verifyInterviewerExists(InterviewerAvailability interviewerAvailabilityModel) {
    Optional<Interviewer> interviewerModel = interviewerRepository.findById(
        interviewerAvailabilityModel.getInterviewerModel().getId());

    if (interviewerModel.isEmpty()) {
      throw new BusinessException(ExceptionConstants.INTERVIEWER_DOES_NOT_EXIST,
          interviewerAvailabilityModel.getInterviewerModel().getName());
    }
  }

  private void verifyPeriodOfAvailabilityIsValid(InterviewerAvailability interviewerAvailabilityModel) {
    DateTimeSlot availabilitySlot = interviewerAvailabilityModel.getAvailabilitySlotList();
    LocalTime newTimeSlotFromTime = availabilitySlot.getFrom();
    LocalTime newTimeSlotToTime = availabilitySlot.getTo();

    if (newTimeSlotFromTime.isAfter(newTimeSlotToTime) || newTimeSlotFromTime.equals(newTimeSlotToTime)) {
      throw new BusinessException(ExceptionConstants.INVALID_FROM_TO_TIME,
          ApplicationConstants.FROM + newTimeSlotFromTime,
          ApplicationConstants.TO + newTimeSlotToTime);
    }

    if (newTimeSlotFromTime.getMinute() != 0 || newTimeSlotToTime.getMinute() != 0) {
      throw new BusinessException(
          ExceptionConstants.INVALID_TIME,
          ApplicationConstants.FROM + newTimeSlotFromTime, ApplicationConstants.TO + newTimeSlotToTime);
    }
  }

  //************* END ADD Interviewer AVAILABILITY ***********

  @Override
  public List<InterviewerModel> getAllInterviewers() {
    List<Interviewer> interviewerList = interviewerRepository.findAll();
    return AvailabilityFinderUtil.convertInterviewerEntityToModelList(interviewerList);
  }
  
  @Override
  public InterviewerModel getInterviewerById(String id) {
    Optional<Interviewer> interviewer = interviewerRepository.findById(Integer.valueOf(id));
    return AvailabilityFinderUtil.convertInterviewerEntityToModel(interviewer.isPresent() ? interviewer.get() : new Interviewer());
  }
  
  @Override
  public InterviewerAvailabilityReturnModel getInterviewerAvailabilityById(String id) {
    List<InterviewerAvailability> interviewerAvailabilityModelList = interviewerAvailabilityRepository.getInterviewerAvailabilityByInterviewerId(id);
    List<DateTimeSlot> dateTimeSlotList=interviewerAvailabilityModelList.stream().map(
        InterviewerAvailability::getAvailabilitySlotList
    ).collect(Collectors.toList());

    return InterviewerAvailabilityReturnModel
        .builder().availabilitySlotList(dateTimeSlotList)
        .interviewerModel(interviewerAvailabilityModelList.get(0).getInterviewerModel())
        .id(interviewerAvailabilityModelList.get(0).getId()).build();

  }

  @Override
  public void deleteInterviewerById(String id) {
    List<InterviewerAvailability> interviewerAvailabilityModelList = interviewerAvailabilityRepository.getInterviewerAvailabilityByInterviewerId(id);
    interviewerAvailabilityRepository.deleteInterviewerAvailability(id);
    interviewerAvailabilityModelList.forEach(e->{
      int dateTimeId=e.getAvailabilitySlotList().getId();
      dateTimeRepository.deleteAvailability(String.valueOf(dateTimeId));
    });

    interviewerRepository.deleteById(Integer.valueOf(id));
  }

}
