package Team76.InternetSoftwareArchitecture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Team76.InternetSoftwareArchitecture.model.CottageReservationReport;

@Repository
public interface ICottageReservationReportRepository extends JpaRepository<CottageReservationReport, Long> {

	@Query(value = "SELECT reservation_cottage_id FROM cottage_reservation_report", nativeQuery = true)
	List<Long> getAllCottageReservationIdFromCottageReservationReports();
}
