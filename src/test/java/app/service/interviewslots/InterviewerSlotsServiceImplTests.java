package app.service.interviewslots;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import app.constant.interviewslots.TestObjects;
import app.exception.BusinessException;
import app.model.candidate.entity.CandidateAvailability;
import app.model.interviewer.entity.Interviewer;
import app.model.interviewer.entity.InterviewerAvailability;
import app.model.interviewer.model.InterviewerModel;
import app.model.interviewslots.model.InterviewSlotsQueryModel;
import app.model.utils.DateTimeSlot;
import app.repository.DateTimeRepository;
import app.repository.candidate.CandidateAvailabilityRepository;
import app.repository.candidate.CandidateRepository;
import app.repository.interviewer.InterviewerAvailabilityRepository;
import app.repository.interviewer.InterviewerRepository;
import app.service.interviewer.InterviewerServiceImpl;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class InterviewerSlotsServiceImplTests {
    @Mock
    private InterviewerRepository interviewerRepository;
    @Mock
    private InterviewerAvailabilityRepository interviewerAvailabilityRepository;

    @Mock
    private CandidateRepository candidateRepository;
    @Mock
    private CandidateAvailabilityRepository candidateAvailabilityRepository;

    @Mock
    private DateTimeRepository dateTimeRepository;

    @InjectMocks
    private InterviewSlotsServiceImpl interviewSlotsServiceImpl;

    @Test
    public void getAvailabilitySlots() {
        List<Integer> interviewerIds = new ArrayList<>();
        interviewerIds.add(4);
        InterviewSlotsQueryModel interviewSlotsQueryModel = InterviewSlotsQueryModel.builder().candidateId(1)
            .interviewersIds(interviewerIds).build();

        when(candidateRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(TestObjects.prepareCandidateObject()));
        when(interviewerRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(TestObjects.prepareInterviewerObject()));
        List<CandidateAvailability> candidateAvailabilityList = new ArrayList<>();
        candidateAvailabilityList.add(TestObjects.createCandidateAvailability());
        when(candidateAvailabilityRepository.candidateAvailabilityByCandidateId(Mockito.any()))
            .thenReturn(candidateAvailabilityList);

        List<InterviewerAvailability> interviewerAvailabilityList = new ArrayList<>();
        interviewerAvailabilityList.add(TestObjects.createInterviewerAvailability());
        when(interviewerAvailabilityRepository
            .getInterviewerAvailabilityByInterviewerId(Mockito.any()))
            .thenReturn(interviewerAvailabilityList);
        assertNotNull(interviewSlotsServiceImpl.getInterviewSlots(interviewSlotsQueryModel));
    }

    @Test
    public void getAvailabilitySlotsCandidateExistenceError() {
        List<Integer> interviewerIds = new ArrayList<>();
        interviewerIds.add(4);
        InterviewSlotsQueryModel interviewSlotsQueryModel = InterviewSlotsQueryModel.builder().candidateId(1)
            .interviewersIds(interviewerIds).build();

        when(candidateRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(BusinessException.class, () -> {
            interviewSlotsServiceImpl.getInterviewSlots(interviewSlotsQueryModel);
        });
    }

    @Test
    public void getAvailabilitySlotsInterviewerExistenceError() {
        List<Integer> interviewerIds = new ArrayList<>();
        interviewerIds.add(4);
        InterviewSlotsQueryModel interviewSlotsQueryModel = InterviewSlotsQueryModel.builder().candidateId(1)
            .interviewersIds(interviewerIds).build();

        when(candidateRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(TestObjects.prepareCandidateObject()));
        when(interviewerRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(BusinessException.class, () -> {
            interviewSlotsServiceImpl.getInterviewSlots(interviewSlotsQueryModel);
        });
    }

    @Test
    public void getAvailabilitySlotsCandidateAvailabilityError() {
        List<Integer> interviewerIds = new ArrayList<>();
        interviewerIds.add(4);
        InterviewSlotsQueryModel interviewSlotsQueryModel = InterviewSlotsQueryModel.builder().candidateId(1)
            .interviewersIds(interviewerIds).build();

        when(candidateRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(TestObjects.prepareCandidateObject()));
        when(interviewerRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(TestObjects.prepareInterviewerObject()));
        when(candidateAvailabilityRepository.candidateAvailabilityByCandidateId(Mockito.any()))
            .thenReturn(new ArrayList<>());

        Assertions.assertThrows(BusinessException.class, () -> {
            interviewSlotsServiceImpl.getInterviewSlots(interviewSlotsQueryModel);
        });
    }

    @Test
    public void getAvailabilitySlotsInterviewerAvailabilityError() {
        List<Integer> interviewerIds = new ArrayList<>();
        interviewerIds.add(4);
        InterviewSlotsQueryModel interviewSlotsQueryModel = InterviewSlotsQueryModel.builder().candidateId(1)
            .interviewersIds(interviewerIds).build();

        when(candidateRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(TestObjects.prepareCandidateObject()));
        when(interviewerRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(TestObjects.prepareInterviewerObject()));
        List<CandidateAvailability> candidateAvailabilityList = new ArrayList<>();
        candidateAvailabilityList.add(TestObjects.createCandidateAvailability());
        when(candidateAvailabilityRepository.candidateAvailabilityByCandidateId(Mockito.any()))
            .thenReturn(candidateAvailabilityList);

        when(interviewerAvailabilityRepository
            .getInterviewerAvailabilityByInterviewerId(Mockito.any()))
            .thenReturn(new ArrayList<>());

        Assertions.assertThrows(BusinessException.class, () -> {
            interviewSlotsServiceImpl.getInterviewSlots(interviewSlotsQueryModel);
        });
    }

    @Test
    public void getAvailabilitySlots1() {
        List<Integer> interviewerIds = new ArrayList<>();
        interviewerIds.add(4);
        InterviewSlotsQueryModel interviewSlotsQueryModel = InterviewSlotsQueryModel.builder().candidateId(1)
            .interviewersIds(interviewerIds).build();

        when(candidateRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(TestObjects.prepareCandidateObject()));
        when(interviewerRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(TestObjects.prepareInterviewerObject()));
        List<CandidateAvailability> candidateAvailabilityList = new ArrayList<>();
        candidateAvailabilityList.add(TestObjects.createCandidateAvailability1());
        when(candidateAvailabilityRepository.candidateAvailabilityByCandidateId(Mockito.any()))
            .thenReturn(candidateAvailabilityList);

        List<InterviewerAvailability> interviewerAvailabilityList = new ArrayList<>();
        interviewerAvailabilityList.add(TestObjects.createInterviewerAvailability1());
        when(interviewerAvailabilityRepository
            .getInterviewerAvailabilityByInterviewerId(Mockito.any()))
            .thenReturn(interviewerAvailabilityList);
        assertNotNull(interviewSlotsServiceImpl.getInterviewSlots(interviewSlotsQueryModel));
    }

    @Test
    public void getAvailabilitySlots2() {
        List<Integer> interviewerIds = new ArrayList<>();
        interviewerIds.add(4);
        InterviewSlotsQueryModel interviewSlotsQueryModel = InterviewSlotsQueryModel.builder().candidateId(1)
            .interviewersIds(interviewerIds).build();

        when(candidateRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(TestObjects.prepareCandidateObject()));
        when(interviewerRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(TestObjects.prepareInterviewerObject()));
        List<CandidateAvailability> candidateAvailabilityList = new ArrayList<>();
        candidateAvailabilityList.add(TestObjects.createCandidateAvailability1());
        when(candidateAvailabilityRepository.candidateAvailabilityByCandidateId(Mockito.any()))
            .thenReturn(candidateAvailabilityList);

        List<InterviewerAvailability> interviewerAvailabilityList = new ArrayList<>();
        interviewerAvailabilityList.add(TestObjects.createInterviewerAvailability2());
        when(interviewerAvailabilityRepository
            .getInterviewerAvailabilityByInterviewerId(Mockito.any()))
            .thenReturn(interviewerAvailabilityList);
        assertNotNull(interviewSlotsServiceImpl.getInterviewSlots(interviewSlotsQueryModel));
    }

    @Test
    public void getAvailabilitySlots3() {
        List<Integer> interviewerIds = new ArrayList<>();
        interviewerIds.add(4);
        InterviewSlotsQueryModel interviewSlotsQueryModel = InterviewSlotsQueryModel.builder().candidateId(1)
            .interviewersIds(interviewerIds).build();

        List<CandidateAvailability> candidateAvailabilityList = new ArrayList<>();
        candidateAvailabilityList.add(TestObjects.createCandidateAvailability1());

        List<InterviewerAvailability> interviewerAvailabilityList = new ArrayList<>();
        interviewerAvailabilityList.add(TestObjects.createInterviewerAvailability2());

        assertNotNull(interviewSlotsServiceImpl.getOverlappingDateTimeSlot(TestObjects.createCandidateAvailability().getAvailabilitySlotList().getFrom(),
            TestObjects.createCandidateAvailability().getAvailabilitySlotList().getTo(),
            TestObjects.createCandidateAvailability(), LocalDate.now(),
            TestObjects.createInterviewerAvailability()));
    }
}
