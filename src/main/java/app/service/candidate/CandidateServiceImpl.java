package app.service.candidate;

import app.constant.ApplicationConstants;
import app.constant.ExceptionConstants;
import app.helper.AvailabilityFinderUtil;
import app.exception.BusinessException;
import app.model.candidate.entity.CandidateAvailability;
import app.model.candidate.model.CandidateAvailabilityModel;
import app.model.candidate.model.CandidateAvailabilityReturnModel;
import app.model.candidate.entity.Candidate;
import app.model.candidate.model.CandidateModel;
import app.model.utils.DateTimeSlot;
import app.repository.DateTimeRepository;
import app.repository.candidate.CandidateAvailabilityRepository;
import app.repository.candidate.CandidateRepository;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
//@RequiredArgsConstructor
@Transactional
public class CandidateServiceImpl implements CandidateService {
  @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    CandidateAvailabilityRepository candidateAvailabilityRepository;

    @Autowired
    DateTimeRepository dateTimeRepository;

    //******** START CREATING CANDIDATE **************
    @Override
    public CandidateModel createCandidate(Candidate candidate) {
        verifyValidityOfCandidate(candidate);
        Candidate cand = candidateRepository.save(candidate);
        return AvailabilityFinderUtil.convertCandidateEntityToModel(cand);
    }

    private void verifyValidityOfCandidate(Candidate candidateModel) {
      verifyNameIsFilled(candidateModel);
      verifyUniqueEmail(candidateModel);
      verifyUniqueName(candidateModel);
    }

  private void verifyUniqueEmail(Candidate candidateModel) {
    String emailOfCandidateToBeCreated = candidateModel.getEmail();
    List<String> existingEmails = candidateRepository.getEmailTocheckExisitng(emailOfCandidateToBeCreated);

    if (!existingEmails.isEmpty()) {
      throw new BusinessException(ExceptionConstants.EMAIL_ALREADY_EXISTS, candidateModel.getEmail());
    }
  }

    private void verifyNameIsFilled(Candidate candidateModel) {
      String nameOfCandidateToBeCreated = candidateModel.getName();

      if (nameOfCandidateToBeCreated == null || nameOfCandidateToBeCreated.isBlank()) {
        throw new BusinessException(ExceptionConstants.NAME_REQUIRED,
            candidateModel.getName() != null ? candidateModel.getName() : null);
      }
    }

    private void verifyUniqueName(Candidate candidateModel) {
      String nameOfCandidateToBeCreated = candidateModel.getName();
      List<String> existingNames = candidateRepository.getAllNames();

      if (existingNames.contains(nameOfCandidateToBeCreated)) {
          throw new BusinessException(ExceptionConstants.NAME_ALREADY_EXISTS, candidateModel.getName());
      }
    }

  //******** END CREATING CANDIDATE **************

  //************* START ADD CANDIDATE AVAILABILITY ***********
  @Override
  public CandidateAvailabilityModel createCandidateAvailability(CandidateAvailability candidateAvailability) {
      AtomicBoolean existingFlag = new AtomicBoolean(false);
      CandidateAvailability candidateAvailabilityAfterSave;

      verifyValidityOfCandidateAvailability(candidateAvailability);

    List<CandidateAvailability> candidateExistingAvailability = verifyIfCandidateHasAvailabilityCreated(
        candidateAvailability);

    if(candidateExistingAvailability.isEmpty()) {
      candidateAvailabilityAfterSave = candidateAvailabilityRepository.save(candidateAvailability);
    }
    else{
      candidateExistingAvailability.forEach(existingAvailability ->
        setExisitngFlagForCandidateAvailability(candidateAvailability, existingFlag, existingAvailability)
      );

      if(existingFlag.toString().equals(ApplicationConstants.FALSE)) {
        candidateAvailabilityAfterSave = candidateAvailabilityRepository.save(candidateAvailability);
      }
      else{
        throw new BusinessException(ExceptionConstants.AVAILABILITY_ALREADY_EXISTS, String.valueOf(candidateAvailability));
      }
    }return AvailabilityFinderUtil.convertCandidateAvailabilityEntityToModel(candidateAvailabilityAfterSave);
  }

  private void setExisitngFlagForCandidateAvailability(CandidateAvailability candidateAvailabilityModel, AtomicBoolean existingFlag, CandidateAvailability existingAvailability) {
    if(existingAvailability.getAvailabilitySlotList().getDay().equals(candidateAvailabilityModel.getAvailabilitySlotList().getDay()) &&
    existingAvailability.getAvailabilitySlotList().getFrom().equals(candidateAvailabilityModel.getAvailabilitySlotList().getFrom())&&
    existingAvailability.getAvailabilitySlotList().getTo().equals(candidateAvailabilityModel.getAvailabilitySlotList().getTo())){
      existingFlag.set(true);
    }else{
      existingFlag.set(false);
    }
  }

  private void verifyValidityOfCandidateAvailability(CandidateAvailability candidateAvailabilityModel) {
    verifyCandidateExists(candidateAvailabilityModel);
    verifyPeriodOfAvailabilityIsValid(candidateAvailabilityModel);
  }

  private List<CandidateAvailability> verifyIfCandidateHasAvailabilityCreated(
      CandidateAvailability candidateAvailabilityModel) {
    String candidateId = String.valueOf(candidateAvailabilityModel.getCandidateModel().getId());

    return candidateAvailabilityRepository.candidateAvailabilityByCandidateId(candidateId);
  }


  private void verifyCandidateExists(CandidateAvailability candidateAvailabilityModel) {
    Optional<Candidate> candidateModel = candidateRepository.findById(
        candidateAvailabilityModel.getCandidateModel().getId());

    if (candidateModel.isEmpty()) {
      throw new BusinessException(ExceptionConstants.CANDIDATE_DOES_NOT_EXIST,
          candidateAvailabilityModel.getCandidateModel().getName());
    }
  }

  private void verifyPeriodOfAvailabilityIsValid(CandidateAvailability candidateAvailabilityModel) {
      DateTimeSlot availabilitySlot = candidateAvailabilityModel.getAvailabilitySlotList();
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

  //************* END ADD CANDIDATE AVAILABILITY ***********

  @Override
  public List<CandidateModel> getAllCandidates() {
      List<Candidate> candidateList = candidateRepository.findAll();
    return AvailabilityFinderUtil.convertCandidateEntityToModelList(candidateList);
  }
  @Override
  public CandidateModel getCandidateById(String id) {
      Optional<Candidate> candidate = candidateRepository.findById(Integer.valueOf(id));
      return AvailabilityFinderUtil.convertCandidateEntityToModel(candidate.isPresent() ? candidate.get() : new Candidate());
  }

  @Override
  public CandidateAvailabilityReturnModel getCandidateAvailabilityById(String id) {
    List<CandidateAvailability> candidateAvailabilityModelList = candidateAvailabilityRepository.candidateAvailabilityByCandidateId(id);
    List<DateTimeSlot> dateTimeSlotList=candidateAvailabilityModelList.stream()
        .map(CandidateAvailability::getAvailabilitySlotList)
        .collect(Collectors.toList());
    return CandidateAvailabilityReturnModel.builder().availabilitySlotList(dateTimeSlotList)
        .candidateModel(candidateAvailabilityModelList.get(0).getCandidateModel())
        .id(candidateAvailabilityModelList.get(0).getId()).build();
  }

  @Override
  public void deleteCandidateById(String id) {
      List<CandidateAvailability> candidateAvailabilityModelList = candidateAvailabilityRepository.candidateAvailabilityByCandidateId(id);
    candidateAvailabilityRepository.deleteCandidateAvailability(id);
      candidateAvailabilityModelList.forEach(e->{
        int dateTimeId=e.getAvailabilitySlotList().getId();
        dateTimeRepository.deleteAvailability(String.valueOf(dateTimeId));
      });

    candidateRepository.deleteById(Integer.valueOf(id));
  }

}
