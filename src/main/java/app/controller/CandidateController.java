package app.controller;

import app.constant.WebUrlConstants;
import app.helper.AvailabilityFinderUtil;
import app.model.candidate.model.CandidateAvailabilityModel;
import app.model.candidate.model.CandidateAvailabilityReturnModel;
import app.model.candidate.model.CandidateModel;
import app.service.candidate.CandidateService;
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
@RequestMapping(WebUrlConstants.CANDIDATE_AVAILABILITY_FINDER)
public class CandidateController {
    @Autowired
    private CandidateService candidateService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CandidateModel createCandidate(@Valid @RequestBody CandidateModel candidateModel) {
        return candidateService.createCandidate(AvailabilityFinderUtil.convertCandidateModelToEntity(candidateModel));
    }

    @PostMapping(WebUrlConstants.AVAILABILITY)
    @ResponseStatus(HttpStatus.CREATED)
    public CandidateAvailabilityModel createCandidateAvailability(
        @Valid @RequestBody CandidateAvailabilityModel candidateAvailabilityModel) {
      return candidateService.createCandidateAvailability(
          AvailabilityFinderUtil.convertCandidateAvailabilityModelToEntity(candidateAvailabilityModel));

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CandidateModel> getAllCandidates() {
        return candidateService.getAllCandidates();
    }

    @GetMapping(WebUrlConstants.ID)
    @ResponseStatus(HttpStatus.OK)
    public CandidateModel getCandidateById(@PathVariable String id) {
        return candidateService.getCandidateById(id);
    }

    @GetMapping(WebUrlConstants.AVAILABILITY_BY_ID)
    @ResponseStatus(HttpStatus.OK)
    public CandidateAvailabilityReturnModel getCandidateAvailabilityById(@PathVariable String id) {
        return candidateService.getCandidateAvailabilityById(id);
    }


    @DeleteMapping(WebUrlConstants.ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCandidateById(@PathVariable String id) {
        candidateService.deleteCandidateById(id);
    }


}
