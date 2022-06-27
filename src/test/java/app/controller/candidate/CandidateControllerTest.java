package app.controller.candidate;


import app.constant.candidate.TestObjects;
import app.controller.CandidateController;
import app.helper.AvailabilityFinderUtil;
import app.model.candidate.entity.Candidate;
import app.model.candidate.entity.CandidateAvailability;
import app.service.candidate.CandidateService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = CandidateController.class)
@ExtendWith(SpringExtension.class)

class CandidateControllerTest {

  @Autowired
  CandidateController candidateController;
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private CandidateService candidateService;


  @Test
  void createCandidate() throws Exception {
    String json = getJsonValue(TestObjects.prepareCandidateObject());
    mockMvc.perform(MockMvcRequestBuilders.post("/availabilityFinder/api/candidates")
            .contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
        .andExpect(MockMvcResultMatchers.status().isCreated());
    Mockito.verify(candidateService, Mockito.times(1))
        .createCandidate(Mockito.any(Candidate.class));
  }

  @Test
  void createCandidateAvailability() throws Exception {
    String json = getJsonValue(TestObjects.createCandidateAvailability());

    mockMvc.perform(MockMvcRequestBuilders.post("/availabilityFinder/api/candidates/availability")
            .contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
        .andExpect(MockMvcResultMatchers.status().isCreated());
    Mockito.verify(candidateService, Mockito.times(1))
        .createCandidateAvailability(Mockito.any(CandidateAvailability.class));
  }

  @Test
  void getAllCandidates() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/availabilityFinder/api/candidates")
            .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(MockMvcResultMatchers.status().isOk());
    Mockito.verify(candidateService, Mockito.times(1))
        .getAllCandidates();
  }

  @Test
  void getCandidateById() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/availabilityFinder/api/candidates/1")
            .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(MockMvcResultMatchers.status().isOk());
    Mockito.verify(candidateService, Mockito.times(1))
        .getCandidateById(Mockito.any());
  }

  @Test
  void getCandidateAvailabilityById() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/availabilityFinder/api/candidates/availability/1")
            .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(MockMvcResultMatchers.status().isOk());
    Mockito.verify(candidateService, Mockito.times(1))
        .getCandidateAvailabilityById(Mockito.any());
  }

  @Test
  void deleteCandidateById() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.delete("/availabilityFinder/api/candidates/1")
            .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(MockMvcResultMatchers.status().isNoContent());
    Mockito.verify(candidateService, Mockito.times(1))
        .deleteCandidateById(Mockito.any());
  }

  private String getJsonValue(Candidate request) throws JsonProcessingException {
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    return ow.writeValueAsString(request);
  }

  private String getJsonValue(CandidateAvailability request) throws JsonProcessingException {
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    return ow.writeValueAsString(request);
  }

}