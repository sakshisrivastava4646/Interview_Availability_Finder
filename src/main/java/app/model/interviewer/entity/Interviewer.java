package app.model.interviewer.entity;

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

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = ApplicationConstants.INTERVIEWER)
public class Interviewer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name=ApplicationConstants.EMAIL)
    private String email;

    @Column(name = ApplicationConstants.NAME)
    private String name;

}
