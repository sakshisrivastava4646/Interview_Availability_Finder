package app.model.interviewslots.model;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class InterviewSlotsQueryModel {
    private  int candidateId;
    private  List<Integer> interviewersIds;

}
