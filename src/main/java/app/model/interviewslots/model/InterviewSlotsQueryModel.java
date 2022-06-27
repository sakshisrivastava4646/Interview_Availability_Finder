package app.model.interviewslots.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class InterviewSlotsQueryModel {
    private  int candidateId;
    private  List<Integer> interviewersIds;

}
