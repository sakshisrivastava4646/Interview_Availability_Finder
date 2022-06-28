package app.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * The type Web url constants.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WebUrlConstants {

  /**
   * The constant CANDIDATE_AVAILABILITY_FINDER.
   */
  public static final String CANDIDATE_AVAILABILITY_FINDER = "availabilityFinder/api/candidates";
  /**
   * The constant AVAILABILITY.
   */
  public static final String AVAILABILITY = "/availability";
  /**
   * The constant ID.
   */
  public static final String ID = "/{id}";
  /**
   * The constant AVAILABILITY_BY_ID.
   */
  public static final String AVAILABILITY_BY_ID = "/availability/{id}";
  /**
   * The constant INTERVIEWERS_AVAILABILITY_FINDER.
   */
  public static final String INTERVIEWERS_AVAILABILITY_FINDER = "availabilityFinder/api/interviewers";
  /**
   * The constant AVAILABILITY_FINDER_INTERVIEW_SLOTS.
   */
  public static final String AVAILABILITY_FINDER_INTERVIEW_SLOTS = "availabilityFinder/api/interview-slots";

}
