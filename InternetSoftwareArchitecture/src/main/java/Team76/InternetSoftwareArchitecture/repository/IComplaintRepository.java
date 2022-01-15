package Team76.InternetSoftwareArchitecture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Team76.InternetSoftwareArchitecture.model.Complaint;

@Repository
public interface IComplaintRepository extends JpaRepository<Complaint, Long>{

}
