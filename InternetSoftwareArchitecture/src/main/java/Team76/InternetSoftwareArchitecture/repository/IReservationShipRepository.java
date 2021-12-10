package Team76.InternetSoftwareArchitecture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Team76.InternetSoftwareArchitecture.model.ReservationShip;
import Team76.InternetSoftwareArchitecture.model.ReservationStatus;

@Repository
public interface IReservationShipRepository extends JpaRepository<ReservationShip, Long>{
	
	List<ReservationShip> findByReservationStatus(ReservationStatus reservationStatus);
}
