package Team76.InternetSoftwareArchitecture.repository;

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
}
