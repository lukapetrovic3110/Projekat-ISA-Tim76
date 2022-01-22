package Team76.InternetSoftwareArchitecture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Team76.InternetSoftwareArchitecture.model.ReservationAdventure;
import Team76.InternetSoftwareArchitecture.model.ReservationStatus;

@Repository
public interface IReservationAdventureRepository extends JpaRepository<ReservationAdventure, Long> {
	
	List<ReservationAdventure> findByReservationStatus(ReservationStatus reservationStatus);
	
	ReservationAdventure findByReservationAdventureId(Long reservationAdventureId);

	@Query(value = "SELECT * FROM reservation_adventure WHERE adventure_id=:adventureId and client_id IS NULL and reservation_status = 'WAITING'", nativeQuery = true)
	List<ReservationAdventure> findAllFastReservationsForAdventure(Long adventureId);

	@Query(value = "SELECT * FROM reservation_adventure WHERE adventure_id=:adventureId and (reservation_status = 'STARTED' or reservation_status = 'SCHEDULED')", nativeQuery = true)
	List<ReservationAdventure> findAllScheduledAndStartedReservationForAdventure(Long adventureId);

	@Query(value = "SELECT * FROM reservation_adventure WHERE adventure_id=:adventureId and client_id=:clientId and reservation_status = 'CANCELLED'", nativeQuery = true)
	List<ReservationAdventure> findAllCanceledReservationForAdventureByClientId(Long adventureId, Long clientId);
}
