package app.controller.interviewer;

import app.constant.interviewer.TestObjects;
import app.controller.CandidateController;
import app.controller.InterviewerController;
import app.model.candidate.entity.Candidate;
import app.model.candidate.entity.CandidateAvailability;
import app.model.interviewer.entity.Interviewer;
import app.model.interviewer.entity.InterviewerAvailability;
import app.service.candidate.CandidateService;
import app.service.interviewer.InterviewerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
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

@WebMvcTest(controllers = InterviewerController.class)
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class InterviewerControllerTest {

  @Autowired
  InterviewerController interviewerController;
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private InterviewerService interviewerService;


  @Test
  void createInterviewer() throws Exception {
    String json = getJsonValue(TestObjects.prepareInterviewerObject());
    mockMvc.perform(MockMvcRequestBuilders.post("/availabilityFinder/api/interviewers")
            .contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
        .andExpect(MockMvcResultMatchers.status().isCreated());
    Mockito.verify(interviewerService, Mockito.times(1))
        .createInterviewer(Mockito.any(Interviewer.class));
  }

  @Test
  void createInterviewerAvailability() throws Exception {
    String json = getJsonValue(TestObjects.createInterviewerAvailability());
    mockMvc.perform(MockMvcRequestBuilders.post("/availabilityFinder/api/interviewers/availability")
            .contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
        .andExpect(MockMvcResultMatchers.status().isCreated());
    Mockito.verify(interviewerService, Mockito.times(1))
        .createInterviewerAvailability(Mockito.any(InterviewerAvailability.class));
  }

  @Test
  void getAllInterviewers() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/availabilityFinder/api/interviewers")
            .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(MockMvcResultMatchers.status().isOk());
    Mockito.verify(interviewerService, Mockito.times(1))
        .getAllInterviewers();
  }

  @Test
  void getInterviewerById() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/availabilityFinder/api/interviewers/1")
            .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(MockMvcResultMatchers.status().isOk());
    Mockito.verify(interviewerService, Mockito.times(1))
        .getInterviewerById(Mockito.any());
  }

  @Test
  void getInterviewerAvailabilityById() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/availabilityFinder/api/interviewers/availability/1")
            .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(MockMvcResultMatchers.status().isOk());
    Mockito.verify(interviewerService, Mockito.times(1))
        .getInterviewerAvailabilityById(Mockito.any());
  }

  @Test
  void deleteInterviewerById() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.delete("/availabilityFinder/api/interviewers/1")
            .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(MockMvcResultMatchers.status().isNoContent());
    Mockito.verify(interviewerService, Mockito.times(1))
        .deleteInterviewerById(Mockito.any());
  }

  private String getJsonValue(Interviewer request) throws JsonProcessingException {
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    return ow.writeValueAsString(request);
  }

  private String getJsonValue(InterviewerAvailability request) throws JsonProcessingException {
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    return ow.writeValueAsString(request);
  }

}