package app.repository.interviewer;

import app.model.interviewer.entity.Interviewer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewerRepository extends JpaRepository<Interviewer, Integer> {
    @Query(value="select i.name from Interviewer i",  nativeQuery = true)
    List<String> getAllNames();

  @Query(value="select c.name from Interviewer c where c.email= ':email'",  nativeQuery = true)
  List<String> getEmailTocheckExisitng(String email);
}
