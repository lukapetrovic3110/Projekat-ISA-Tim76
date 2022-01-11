package Team76.InternetSoftwareArchitecture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Team76.InternetSoftwareArchitecture.model.ReservationAdventure;
import Team76.InternetSoftwareArchitecture.model.ReservationStatus;

@Repository
public interface IReservationAdventureRepository extends JpaRepository<ReservationAdventure, Long> {
	
	List<ReservationAdventure> findByReservationStatus(ReservationStatus reservationStatus);
	
	ReservationAdventure findByReservationAdventureId(Long reservationAdventureId);
}
