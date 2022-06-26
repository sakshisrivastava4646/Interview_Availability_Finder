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

@RestController
@RequestMapping(WebUrlConstants.INTERVIEWERS_AVAILABILITY_FINDER)
public class InterviewerController {
  @Autowired
    private InterviewerService interviewerService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InterviewerModel createInterviewer(@Valid @RequestBody InterviewerModel interviewerModel) {
      return interviewerService.createInterviewer(AvailabilityFinderUtil.convertInterviewerModelToEntity(interviewerModel));
    }

  @PostMapping(WebUrlConstants.AVAILABILITY)
  @ResponseStatus(HttpStatus.CREATED)
  public InterviewerAvailabilityModel createCandidateAvailability(
      @Valid @RequestBody InterviewerAvailabilityModel interviewerAvailabilityModel) {
    return interviewerService.createInterviewerAvailability(AvailabilityFinderUtil.convertInterviewerAvailabilityModelToEntity(interviewerAvailabilityModel));
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<InterviewerModel> getAllInterviewers() {
    return interviewerService.getAllInterviewers();
  }

  @GetMapping(WebUrlConstants.ID)
  @ResponseStatus(HttpStatus.OK)
  public InterviewerModel getInterviewerById(@PathVariable String id) {
    return interviewerService.getInterviewerById(id);
  }

  @GetMapping(WebUrlConstants.AVAILABILITY_BY_ID)
  @ResponseStatus(HttpStatus.OK)
  public InterviewerAvailabilityReturnModel getCandidateAvailabilityById(@PathVariable String id) {
    return interviewerService.getInterviewerAvailabilityById(id);
  }

  @DeleteMapping(WebUrlConstants.ID)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteCandidateById(@PathVariable String id) {
    interviewerService.deleteInterviewerById(id);
  }

}
