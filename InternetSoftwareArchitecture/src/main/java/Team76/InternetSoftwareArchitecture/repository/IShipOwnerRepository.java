package Team76.InternetSoftwareArchitecture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Team76.InternetSoftwareArchitecture.model.AccountApproval;
import Team76.InternetSoftwareArchitecture.model.ShipOwner;
import Team76.InternetSoftwareArchitecture.model.UserType;

@Repository
public interface IShipOwnerRepository extends JpaRepository<ShipOwner, Long> {
	
	@Query("select u from User u where u.accountApproval = ?1 and u.userType = ?2")
	List<ShipOwner> findAllShipOwnersByAccountApproval(AccountApproval accountApproval, UserType userType);
}
