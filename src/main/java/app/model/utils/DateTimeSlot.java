package app.model.utils;

import app.constant.ApplicationConstants;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = ApplicationConstants.DATE_TIME_AVAILABILITY)
public class DateTimeSlot implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name= ApplicationConstants.DATE)
    private LocalDate day;

    @Column(name= ApplicationConstants.FROM_TIME)
    private LocalTime from;

    @Column(name= ApplicationConstants.TO_TIME)
    private LocalTime to;



}
