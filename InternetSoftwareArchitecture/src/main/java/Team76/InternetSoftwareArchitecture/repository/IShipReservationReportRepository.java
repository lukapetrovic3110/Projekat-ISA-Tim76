package Team76.InternetSoftwareArchitecture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Team76.InternetSoftwareArchitecture.model.ShipReservationReport;

@Repository
public interface IShipReservationReportRepository extends JpaRepository<ShipReservationReport, Long> {
	
	@Query(value = "SELECT reservation_ship_id FROM ship_reservation_report", nativeQuery = true)
	List<Long> getAllShipReservationIdFromShipReservationReports();

}
