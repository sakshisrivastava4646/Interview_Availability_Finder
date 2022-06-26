package app.repository;

import app.model.utils.DateTimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DateTimeRepository extends JpaRepository<DateTimeSlot, Integer> {
  @Modifying
  @Query(value = "delete from Date_Time_Availability dt where dt.id = :id", nativeQuery = true)
  void deleteAvailability(String id);
}
