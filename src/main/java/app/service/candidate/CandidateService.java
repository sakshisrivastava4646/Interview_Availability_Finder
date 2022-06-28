package app.service.candidate;

import app.model.candidate.entity.CandidateAvailability;
import app.model.candidate.model.CandidateAvailabilityModel;
import app.model.candidate.model.CandidateAvailabilityReturnModel;
import app.model.candidate.entity.Candidate;
import app.model.candidate.model.CandidateModel;
import java.util.List;

/**
 * The interface Candidate service.
 */
public interface CandidateService {
  /**
   * Create candidate candidate model.
   * @param candidateModel the candidate model
   * @return the candidate model
   */
  CandidateModel createCandidate(Candidate candidateModel);

  /**
   * Create candidate availability candidate availability model.
   * @param candidateAvailabilityModel the candidate availability model
   * @return the candidate availability model
   */
  CandidateAvailabilityModel createCandidateAvailability(CandidateAvailability candidateAvailabilityModel);

  /**
   * Gets all candidates.
   * @return the all candidates
   */
  List<CandidateModel> getAllCandidates();

  /**
   * Gets candidate by id.
   * @param id the id
   * @return the candidate by id
   */
  CandidateModel getCandidateById(String id);

  /**
   * Gets candidate availability by id.
   * @param id the id
   * @return the candidate availability by id
   */
  CandidateAvailabilityReturnModel getCandidateAvailabilityById(String id);

  /**
   * Delete candidate by id.
   * @param id the id
   */
  void deleteCandidateById(String id);
}
