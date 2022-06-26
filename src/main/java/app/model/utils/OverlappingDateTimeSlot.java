package app.model.utils;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
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
public class OverlappingDateTimeSlot implements Serializable {
    private LocalDate day;

    private LocalTime from;

    private LocalTime to;


}
