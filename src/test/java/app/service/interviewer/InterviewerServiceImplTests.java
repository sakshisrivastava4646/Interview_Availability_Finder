package app.service.interviewer;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import app.constant.interviewer.TestObjects;
import app.exception.BusinessException;

import app.model.interviewer.entity.Interviewer;
import app.model.interviewer.entity.InterviewerAvailability;
import app.model.interviewer.model.InterviewerModel;
import app.model.utils.DateTimeSlot;
import app.repository.DateTimeRepository;

import app.repository.interviewer.InterviewerAvailabilityRepository;
import app.repository.interviewer.InterviewerRepository;

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
public class InterviewerServiceImplTests {
    @Mock
    private InterviewerRepository interviewerRepository;
    @Mock
    private InterviewerAvailabilityRepository interviewerAvailabilityRepository;

    @Mock
    private DateTimeRepository dateTimeRepository;

    @InjectMocks
    private InterviewerServiceImpl interviewerServiceImpl;

    @Test
    public void createInterviewerSuccessfully() {
        when(interviewerRepository.save(Mockito.any())).thenReturn(TestObjects.prepareInterviewerObject());
        when(interviewerRepository.getEmailTocheckExisitng(Mockito.any())).thenReturn(new ArrayList<>());
        when(interviewerRepository.getAllNames()).thenReturn(new ArrayList<>());

        InterviewerModel savedInterviewer = interviewerServiceImpl.createInterviewer(TestObjects.prepareInterviewerObject());

        assertNotNull(savedInterviewer);
    }

    @Test
    public void createInterviewerwithNameError() {
        Assertions.assertThrows(BusinessException.class, () -> {
          interviewerServiceImpl.createInterviewer(TestObjects.createInterviewerwithNameError());
        });
    }

    @Test
    public void createInterviewerwithUniqueEmailError() {
        List<String> emails = new ArrayList<>();
        emails.add("sakshi@gmail.com");
        when(interviewerRepository.getEmailTocheckExisitng(Mockito.any())).thenReturn(emails);
        Assertions.assertThrows(BusinessException.class, () -> {
            interviewerServiceImpl.createInterviewer(TestObjects.createInterviewerwithUniqueEmailError());
        });
    }

    @Test
    public void createInterviewerwithUniqueNameError() {
        String interviewerName = "sakshi";
        List<String> names = new ArrayList<>();
        names.add(interviewerName);
        when(interviewerRepository.getEmailTocheckExisitng(Mockito.any())).thenReturn(new ArrayList<>());
        when(interviewerRepository.getAllNames()).thenReturn(names);
        Assertions.assertThrows(BusinessException.class, () -> {
            interviewerServiceImpl.createInterviewer(TestObjects.prepareInterviewerObject());
        });
    }

    @Test
    public void createInterviewerAvailability() {
        when(interviewerRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(TestObjects.prepareInterviewerObject()));
        when(interviewerAvailabilityRepository.getInterviewerAvailabilityByInterviewerId(Mockito.any()))
            .thenReturn(new ArrayList<>());

        when(interviewerAvailabilityRepository.save(Mockito.any())).thenReturn(TestObjects.createInterviewerAvailability());
        assertNotNull(interviewerServiceImpl.createInterviewerAvailability(TestObjects.createInterviewerAvailability()));
    }

    @Test
    public void createInterviewerAvailabilityWithExistingAvailabilityError() {
        when(interviewerRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(TestObjects.prepareInterviewerObject()));
        List<InterviewerAvailability> interviewerAvailabilityList = new ArrayList<>();
        interviewerAvailabilityList.add(TestObjects.createInterviewerAvailability());
        when(interviewerAvailabilityRepository.getInterviewerAvailabilityByInterviewerId(Mockito.any()))
            .thenReturn(interviewerAvailabilityList);

        Assertions.assertThrows(BusinessException.class, () -> {
            interviewerServiceImpl.createInterviewerAvailability(TestObjects.createInterviewerAvailability());
        });
    }

    @Test
    public void createInterviewerAvailabilityWithExistingAvailability() {
        when(interviewerRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(TestObjects.prepareInterviewerObject()));
        List<InterviewerAvailability> interviewerAvailabilityList = new ArrayList<>();

        DateTimeSlot dateTimeSlot1 = DateTimeSlot.builder().day(LocalDate.now())
            .from(LocalTime.of(11,00)).to(LocalTime.of(12, 00)).build();
        InterviewerAvailability interviewerAvailability1 = InterviewerAvailability.builder()
            .availabilitySlotList(dateTimeSlot1).interviewerModel(TestObjects.prepareInterviewerObject())
            .id(3L).build();
        interviewerAvailabilityList.add(interviewerAvailability1);
        when(interviewerAvailabilityRepository.getInterviewerAvailabilityByInterviewerId(Mockito.any()))
            .thenReturn(interviewerAvailabilityList);


        when(interviewerAvailabilityRepository.save(Mockito.any())).thenReturn(TestObjects.createInterviewerAvailability());

        assertNotNull(interviewerServiceImpl.createInterviewerAvailability(TestObjects.createInterviewerAvailability()));

    }

    @Test
    public void createInterviewerAvailabilityWithInterviewerDoesNotExistError() {
       when(interviewerRepository.findById(Mockito.any())).thenReturn(Optional.empty());

        Assertions.assertThrows(BusinessException.class, () -> {
            interviewerServiceImpl.createInterviewerAvailability(TestObjects.createInterviewerAvailability());
        });
    }

    @Test
    public void createInterviewerAvailabilityWithTimeError() {
        when(interviewerRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(TestObjects.prepareInterviewerObject()));

        Assertions.assertThrows(BusinessException.class, () -> {
            interviewerServiceImpl.createInterviewerAvailability(TestObjects.createInterviewerAvailabilityWithTimeError());
        });
    }

    @Test
    public void createInterviewerAvailabilityWithTimeError1() {
        when(interviewerRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(TestObjects.prepareInterviewerObject()));

        Assertions.assertThrows(BusinessException.class, () -> {
            interviewerServiceImpl.createInterviewerAvailability(TestObjects.createInterviewerAvailabilityWithTimeError1());
        });
    }

    @Test
    public void getAllInterviewers(){
        List<Interviewer> interviewerList = new ArrayList<>();
        interviewerList.add(TestObjects.prepareInterviewerObject());
        when(interviewerRepository.findAll()).thenReturn(interviewerList);
        assertNotNull(interviewerServiceImpl.getAllInterviewers());
    }

    @Test
    public void getInterviewerById(){
        when(interviewerRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(TestObjects.prepareInterviewerObject()));
        assertNotNull(interviewerServiceImpl.getInterviewerById(String.valueOf(1)));
    }

    @Test
    public void getInterviewerAvailabilityById(){
        List<InterviewerAvailability> interviewerAvailabilityList = new ArrayList<>();
        interviewerAvailabilityList.add(TestObjects.createInterviewerAvailability());

        when(interviewerAvailabilityRepository.getInterviewerAvailabilityByInterviewerId(Mockito.any()))
            .thenReturn(interviewerAvailabilityList);

        assertNotNull(interviewerServiceImpl.getInterviewerAvailabilityById("1"));
    }

    @Test
    public void deleteInterviewerById(){
        List<InterviewerAvailability> interviewerAvailabilityList = new ArrayList<>();
        interviewerAvailabilityList.add(TestObjects.createInterviewerAvailability());

        when(interviewerAvailabilityRepository.getInterviewerAvailabilityByInterviewerId(Mockito.any()))
            .thenReturn(interviewerAvailabilityList);

        Mockito.doNothing().when(interviewerAvailabilityRepository).deleteInterviewerAvailability(Mockito.any());
        Mockito.doNothing().when(dateTimeRepository).deleteAvailability(Mockito.anyString());
        Mockito.doNothing().when(interviewerRepository).deleteById(Mockito.any());

        interviewerServiceImpl.deleteInterviewerById("1");
        //TO DO: add verification
    }

}
