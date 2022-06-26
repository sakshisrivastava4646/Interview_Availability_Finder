package app.service.candidate;

import app.model.candidate.entity.CandidateAvailability;
import app.model.candidate.model.CandidateAvailabilityModel;
import app.model.candidate.model.CandidateAvailabilityReturnModel;
import app.model.candidate.entity.Candidate;
import app.model.candidate.model.CandidateModel;
import java.util.List;

public interface CandidateService {
    CandidateModel createCandidate(Candidate candidateModel);

    CandidateAvailabilityModel createCandidateAvailability(CandidateAvailability candidateAvailabilityModel);

    List<CandidateModel> getAllCandidates();

    CandidateModel getCandidateById(String id);

    CandidateAvailabilityReturnModel getCandidateAvailabilityById(String id);

    void deleteCandidateById(String id);
}
