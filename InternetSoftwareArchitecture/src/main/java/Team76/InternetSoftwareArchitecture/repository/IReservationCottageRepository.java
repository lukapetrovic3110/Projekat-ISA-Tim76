package Team76.InternetSoftwareArchitecture.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Team76.InternetSoftwareArchitecture.model.ReservationCottage;
import Team76.InternetSoftwareArchitecture.model.ReservationStatus;

@Repository
public interface IReservationCottageRepository extends JpaRepository<ReservationCottage, Long> {
	
	List<ReservationCottage> findByReservationStatus(ReservationStatus reservationStatus);

	ReservationCottage findByReservationCottageId(Long reservationId);
	
	@Query(value = "SELECT * FROM reservation_cottage WHERE cottage_id=:cottageId and client_id IS NULL and reservation_status = 'WAITING'", nativeQuery = true)
	List<ReservationCottage> findAllFastReservationsForCottage(Long cottageId);
	
	@Query(value = "SELECT * FROM reservation_cottage WHERE cottage_id=:cottageId", nativeQuery = true)
	List<ReservationCottage> findAllReservationsForCottage(Long cottageId);

	@Query(value = "SELECT * FROM reservation_cottage WHERE cottage_id=:cottageId and (reservation_status = 'STARTED' or reservation_status = 'SCHEDULED')", nativeQuery = true)
	List<ReservationCottage> findAllScheduledAndStartedReservationForCottage(Long cottageId);
	
	@Query(value = "SELECT * FROM reservation_cottage WHERE cottage_id=:cottageId and client_id=:clientId and reservation_status = 'CANCELLED'", nativeQuery = true)
	List<ReservationCottage> findAllCanceledReservationForCottageByClientId(Long cottageId, Long clientId);

	@Query(value = "SELECT * FROM reservation_cottage WHERE date_and_time=:cottageReservationDateAndTime", nativeQuery = true)
	ReservationCottage findByDateAndTime(Date cottageReservationDateAndTime);

}
