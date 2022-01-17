package Team76.InternetSoftwareArchitecture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Team76.InternetSoftwareArchitecture.model.Complaint;

@Repository
public interface IComplaintRepository extends JpaRepository<Complaint, Long>{
			
    @Query(value = "SELECT * FROM complaint WHERE cottage_id IS NOT NULL and complaint_status = 'WAITING'", nativeQuery = true)
	List<Complaint> getCottageComplaints();
    
    
    @Query(value = "SELECT * FROM complaint WHERE ship_id IS NOT NULL and complaint_status = 'WAITING'", nativeQuery = true)
  	List<Complaint> getShipComplaints();

    @Query(value = "SELECT * FROM complaint WHERE user_id IS NOT NULL and complaint_status = 'WAITING'", nativeQuery = true)
	List<Complaint> getUserComplaints();
}
