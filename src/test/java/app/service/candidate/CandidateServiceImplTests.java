package app.service.candidate;


import app.constant.candidate.TestObjects;
import app.exception.BusinessException;
import app.model.candidate.entity.Candidate;
import app.model.candidate.entity.CandidateAvailability;
import app.model.candidate.model.CandidateModel;
import app.model.utils.DateTimeSlot;
import app.repository.DateTimeRepository;
import app.repository.candidate.CandidateAvailabilityRepository;
import app.repository.candidate.CandidateRepository;
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

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CandidateServiceImplTests {
    @Mock
    private CandidateRepository candidateRepository;
    @Mock
    private CandidateAvailabilityRepository candidateAvailabilityRepository;

    @Mock
    private DateTimeRepository dateTimeRepository;

    @InjectMocks
    private CandidateServiceImpl candidateServiceImpl;

    @Test
    public void createCandidateSuccessfully() {
        when(candidateRepository.save(Mockito.any())).thenReturn(TestObjects.prepareCandidateObject());
        when(candidateRepository.getEmailTocheckExisitng(Mockito.any())).thenReturn(new ArrayList<>());
        when(candidateRepository.getAllNames()).thenReturn(new ArrayList<>());

        CandidateModel savedCandidate = candidateServiceImpl.createCandidate(TestObjects.prepareCandidateObject());

        assertNotNull(savedCandidate);
    }

    @Test
    public void createCandidatewithNameError() {
        Assertions.assertThrows(BusinessException.class, () -> {

          candidateServiceImpl.createCandidate(TestObjects.createCandidatewithNameError());
        });
    }

    @Test
    public void createCandidatewithUniqueEmailError() {
        List<String> emails = new ArrayList<>();
        emails.add("sakshi@gmail.com");
        when(candidateRepository.getEmailTocheckExisitng(Mockito.any())).thenReturn(emails);
        Assertions.assertThrows(BusinessException.class, () -> {

            candidateServiceImpl.createCandidate(TestObjects.createCandidatewithUniqueEmailError());
        });
    }

    @Test
    public void createCandidatewithUniqueNameError() {
        String candidateName = "sakshi";
        List<String> names = new ArrayList<>();
        names.add(candidateName);
        when(candidateRepository.getEmailTocheckExisitng(Mockito.any())).thenReturn(new ArrayList<>());
        when(candidateRepository.getAllNames()).thenReturn(names);
        Assertions.assertThrows(BusinessException.class, () -> {

            candidateServiceImpl.createCandidate(TestObjects.prepareCandidateObject());
        });
    }

    @Test
    public void createCandidateAvailability() {
        when(candidateRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(TestObjects.prepareCandidateObject()));
        when(candidateAvailabilityRepository.candidateAvailabilityByCandidateId(Mockito.any()))
            .thenReturn(new ArrayList<>());

        when(candidateAvailabilityRepository.save(Mockito.any())).thenReturn(TestObjects.createCandidateAvailability());
        assertNotNull(candidateServiceImpl.createCandidateAvailability(TestObjects.createCandidateAvailability()));
    }

    @Test
    public void createCandidateAvailabilityWithExistingAvailabilityError() {

        when(candidateRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(TestObjects.prepareCandidateObject()));
        List<CandidateAvailability> candidateAvailabilityList = new ArrayList<>();
        candidateAvailabilityList.add(TestObjects.createCandidateAvailability());
        when(candidateAvailabilityRepository.candidateAvailabilityByCandidateId(Mockito.any()))
            .thenReturn(candidateAvailabilityList);

        Assertions.assertThrows(BusinessException.class, () -> {

            candidateServiceImpl.createCandidateAvailability(TestObjects.createCandidateAvailability());
        });
    }

    @Test
    public void createCandidateAvailabilityWithExistingAvailability() {
        when(candidateRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(TestObjects.prepareCandidateObject()));
        List<CandidateAvailability> candidateAvailabilityList = new ArrayList<>();

        candidateAvailabilityList.add(TestObjects.createCandidateAvailabilityWithExistingAvailability());
        when(candidateAvailabilityRepository.candidateAvailabilityByCandidateId(Mockito.any()))
            .thenReturn(candidateAvailabilityList);

        when(candidateAvailabilityRepository.save(Mockito.any())).thenReturn(TestObjects.createCandidateAvailability());

        assertNotNull(candidateServiceImpl.createCandidateAvailability(TestObjects.createCandidateAvailability()));

    }

    @Test
    public void createCandidateAvailabilityWithCandidateDoesNotExistError() {
        when(candidateRepository.findById(Mockito.any())).thenReturn(Optional.empty());

        Assertions.assertThrows(BusinessException.class, () -> {

            candidateServiceImpl.createCandidateAvailability(TestObjects.createCandidateAvailability());
        });
    }

    @Test
    public void createCandidateAvailabilityWithTimeError() {
        when(candidateRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(TestObjects.prepareCandidateObject()));
        Assertions.assertThrows(BusinessException.class, () -> {

            candidateServiceImpl.createCandidateAvailability(TestObjects.createCandidateAvailabilityWithTimeError());
        });
    }

    @Test
    public void createCandidateAvailabilityWithTimeError1() {
        when(candidateRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(TestObjects.prepareCandidateObject()));
        Assertions.assertThrows(BusinessException.class, () -> {
            candidateServiceImpl.createCandidateAvailability(TestObjects.createCandidateAvailabilityWithTimeError1());
        });
    }

    @Test
    public void getAllCandidates(){
        List<Candidate> candidateList = new ArrayList<>();
        candidateList.add(TestObjects.prepareCandidateObject());
        when(candidateRepository.findAll()).thenReturn(candidateList);
        assertNotNull(candidateServiceImpl.getAllCandidates());
    }

    @Test
    public void getCandidateById(){
        when(candidateRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(TestObjects.prepareCandidateObject()));
        assertNotNull(candidateServiceImpl.getCandidateById(String.valueOf(1)));
    }

    @Test
    public void getCandidateAvailabilityById(){
        List<CandidateAvailability> candidateAvailabilityList = new ArrayList<>();
        candidateAvailabilityList.add(TestObjects.createCandidateAvailability());
        when(candidateAvailabilityRepository.candidateAvailabilityByCandidateId(Mockito.any()))
            .thenReturn(candidateAvailabilityList);
        assertNotNull(candidateServiceImpl.getCandidateAvailabilityById("1"));
    }

    @Test
    public void deleteCandidateById(){
        List<CandidateAvailability> candidateAvailabilityList = new ArrayList<>();
        candidateAvailabilityList.add(TestObjects.createCandidateAvailability());
        when(candidateAvailabilityRepository.candidateAvailabilityByCandidateId(Mockito.any()))
            .thenReturn(candidateAvailabilityList);
        Mockito.doNothing().when(candidateAvailabilityRepository).deleteCandidateAvailability(Mockito.any());
        Mockito.doNothing().when(dateTimeRepository).deleteAvailability(Mockito.anyString());
        Mockito.doNothing().when(candidateRepository).deleteById(Mockito.any());
        candidateServiceImpl.deleteCandidateById("1");
        //TO DO: add verification
    }

}
