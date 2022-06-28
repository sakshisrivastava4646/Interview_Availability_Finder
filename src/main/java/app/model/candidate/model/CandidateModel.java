package app.model.candidate.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;

/**
 * The type Candidate model.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidateModel {

    private int id;

    private String email;

    private String name;


}
