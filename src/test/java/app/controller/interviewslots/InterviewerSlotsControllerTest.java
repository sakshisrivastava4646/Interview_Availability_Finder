package app.controller.interviewslots;

import app.constant.interviewslots.TestObjects;
import app.controller.InterviewSlotsController;
import app.controller.InterviewerController;
import app.model.interviewer.entity.Interviewer;
import app.model.interviewer.entity.InterviewerAvailability;
import app.model.interviewslots.model.InterviewSlotsQueryModel;
import app.service.interviewer.InterviewerService;
import app.service.interviewslots.InterviewSlotsService;
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

@WebMvcTest(controllers = InterviewSlotsController.class)
@ExtendWith(SpringExtension.class)
class InterviewerSlotsControllerTest {

  @Autowired
  InterviewSlotsController interviewSlotsController;

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private InterviewSlotsService interviewSlotsService;

  @Test
  void createInterviewer() throws Exception {
    String json = getJsonValue(TestObjects.getInterviewSlots());
    mockMvc.perform(MockMvcRequestBuilders.get("/availabilityFinder/api/interview-slots")
            .contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
        .andExpect(MockMvcResultMatchers.status().isOk());
    Mockito.verify(interviewSlotsService, Mockito.times(1))
        .getInterviewSlots(Mockito.any(InterviewSlotsQueryModel.class));
  }

  private String getJsonValue(InterviewSlotsQueryModel request) throws JsonProcessingException {
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    return ow.writeValueAsString(request);
  }


}