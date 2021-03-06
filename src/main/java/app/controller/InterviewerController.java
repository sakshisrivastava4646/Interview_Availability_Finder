package app.controller;

import app.constant.WebUrlConstants;
import app.helper.AvailabilityFinderUtil;
import app.model.interviewer.model.InterviewerAvailabilityModel;
import app.model.interviewer.model.InterviewerAvailabilityReturnModel;
import app.model.interviewer.model.InterviewerModel;
import app.service.interviewer.InterviewerService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Interviewer controller.
 */
@RestController
@RequestMapping(WebUrlConstants.INTERVIEWERS_AVAILABILITY_FINDER)
public class InterviewerController {
  @Autowired
    private InterviewerService interviewerService;

  /**
   * Create interviewer interviewer model.
   * @param interviewerModel the interviewer model
   * @return the interviewer model
   */
  @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InterviewerModel createInterviewer(@Valid @RequestBody InterviewerModel interviewerModel) {
      return interviewerService.createInterviewer(AvailabilityFinderUtil.convertInterviewerModelToEntity(interviewerModel));
    }

  /**
   * Create interviewer availability interviewer availability model.
   * @param interviewerAvailabilityModel the interviewer availability model
   * @return the interviewer availability model
   */
  @PostMapping(WebUrlConstants.AVAILABILITY)
  @ResponseStatus(HttpStatus.CREATED)
  public InterviewerAvailabilityModel createInterviewerAvailability(
      @Valid @RequestBody InterviewerAvailabilityModel interviewerAvailabilityModel) {
    return interviewerService.createInterviewerAvailability(AvailabilityFinderUtil.convertInterviewerAvailabilityModelToEntity(interviewerAvailabilityModel));
  }

  /**
   * Gets all interviewers.
   * @return the all interviewers
   */
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<InterviewerModel> getAllInterviewers() {
    return interviewerService.getAllInterviewers();
  }

  /**
   * Gets interviewer by id.
   * @param id the id
   * @return the interviewer by id
   */
  @GetMapping(WebUrlConstants.ID)
  @ResponseStatus(HttpStatus.OK)
  public InterviewerModel getInterviewerById(@PathVariable String id) {
    return interviewerService.getInterviewerById(id);
  }

  /**
   * Gets interviewer availability by id.
   * @param id the id
   * @return the interviewer availability by id
   */
  @GetMapping(WebUrlConstants.AVAILABILITY_BY_ID)
  @ResponseStatus(HttpStatus.OK)
  public InterviewerAvailabilityReturnModel getInterviewerAvailabilityById(@PathVariable String id) {
    return interviewerService.getInterviewerAvailabilityById(id);
  }

  /**
   * Delete interviewer by id.
   * @param id the id
   */
  @DeleteMapping(WebUrlConstants.ID)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteInterviewerById(@PathVariable String id) {
    interviewerService.deleteInterviewerById(id);
  }

}
