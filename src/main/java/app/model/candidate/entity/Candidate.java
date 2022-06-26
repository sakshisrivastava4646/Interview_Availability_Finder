package app.model.candidate.entity;

import app.constant.ApplicationConstants;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = ApplicationConstants.CANDIDATE)
@Getter
@Setter
@RequiredArgsConstructor
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name= ApplicationConstants.EMAIL)
    private String email;

    @Column(name = ApplicationConstants.NAME)
    private String name;


}
