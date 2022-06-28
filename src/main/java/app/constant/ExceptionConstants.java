package app.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * The type Exception constants.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionConstants {

  /**
   * The constant EMAIL_ALREADY_EXISTS.
   */
  public static final String EMAIL_ALREADY_EXISTS = "Email already exists!";
  /**
   * The constant NAME_REQUIRED.
   */
  public static final String NAME_REQUIRED = "You must provide a name!";
  /**
   * The constant NAME_ALREADY_EXISTS.
   */
  public static final String NAME_ALREADY_EXISTS = "Name already exists!";
  /**
   * The constant AVAILABILITY_ALREADY_EXISTS.
   */
  public static final String AVAILABILITY_ALREADY_EXISTS = "Availability Already exists!";
  /**
   * The constant CANDIDATE_DOES_NOT_EXIST.
   */
  public static final String CANDIDATE_DOES_NOT_EXIST = "Candidate does not exist!";
  /**
   * The constant INTERVIEWER_DOES_NOT_EXIST.
   */
  public static final String INTERVIEWER_DOES_NOT_EXIST = "Interviewer does not exist!";
  /**
   * The constant INVALID_FROM_TO_TIME.
   */
  public static final String INVALID_FROM_TO_TIME = "Start hour of slot must be before end hour of slot!";
  /**
   * The constant INVALID_TIME.
   */
  public static final String INVALID_TIME = "Availability slot must be from the beginning of the hour until the beginning of the next hour!";
  /**
   * The constant CANDIDATE_HAS_NO_AVAILABILITY_DEFINED.
   */
  public static final String CANDIDATE_HAS_NO_AVAILABILITY_DEFINED = "Candidate has no availability defined!";
  /**
   * The constant INTERVIEWER_HAS_NO_AVAILABILITY_DEFINED.
   */
  public static final String INTERVIEWER_HAS_NO_AVAILABILITY_DEFINED = "Interviewer has no availability defined!";
}