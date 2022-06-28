package app.controller;

import app.constant.WebUrlConstants;
import app.model.interviewslots.model.InterviewSlotsQueryModel;
import app.model.interviewslots.model.InterviewSlotsReturnModel;
import app.service.interviewslots.InterviewSlotsService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Interview slots controller.
 */
@RestController
@RequestMapping(WebUrlConstants.AVAILABILITY_FINDER_INTERVIEW_SLOTS)
public class InterviewSlotsController {
    @Autowired
    private InterviewSlotsService interviewSlotsService;

  /**
   * Gets interview slots.
   * @param interviewSlotsQueryModel the interview slots query model
   * @return the interview slots
   */
  @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public InterviewSlotsReturnModel getInterviewSlots(
            @Valid @RequestBody InterviewSlotsQueryModel interviewSlotsQueryModel) {
        return interviewSlotsService.getInterviewSlots(interviewSlotsQueryModel);
    }
}
