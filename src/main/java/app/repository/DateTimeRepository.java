package app.repository;

import app.model.utils.DateTimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * The interface Date time repository.
 */
@Repository
public interface DateTimeRepository extends JpaRepository<DateTimeSlot, Integer> {
  /**
   * Delete availability.
   * @param id the id
   */
  @Modifying
  @Query(value = "delete from Date_Time_Availability dt where dt.id = :id", nativeQuery = true)
  void deleteAvailability(String id);
}
