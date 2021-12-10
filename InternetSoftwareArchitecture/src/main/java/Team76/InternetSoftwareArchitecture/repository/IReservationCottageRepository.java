package Team76.InternetSoftwareArchitecture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Team76.InternetSoftwareArchitecture.model.ReservationCottage;
import Team76.InternetSoftwareArchitecture.model.ReservationStatus;

@Repository
public interface IReservationCottageRepository extends JpaRepository<ReservationCottage, Long> {
	
	List<ReservationCottage> findByReservationStatus(ReservationStatus reservationStatus);
}
