package app.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * The type Web url constants.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WebUrlConstants {

  public static final String CANDIDATE_AVAILABILITY_FINDER = "availabilityFinder/api/candidates";
  public static final String AVAILABILITY = "/availability";
  public static final String ID = "/{id}";
  public static final String AVAILABILITY_BY_ID = "/availability/{id}";
  public static final String INTERVIEWERS_AVAILABILITY_FINDER = "availabilityFinder/api/interviewers";
  public static final String AVAILABILITY_FINDER_INTERVIEW_SLOTS = "availabilityFinder/api/interview-slots";

}
