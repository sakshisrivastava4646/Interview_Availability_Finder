package app.model.utils.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DateTimeSlotModel implements Serializable {

    private int id;

    private LocalDate day;

    private LocalTime from;

    private LocalTime to;



}
