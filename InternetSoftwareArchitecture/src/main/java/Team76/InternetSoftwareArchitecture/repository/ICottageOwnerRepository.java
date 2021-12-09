package Team76.InternetSoftwareArchitecture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Team76.InternetSoftwareArchitecture.model.AccountApproval;
import Team76.InternetSoftwareArchitecture.model.CottageOwner;
import Team76.InternetSoftwareArchitecture.model.UserType;

@Repository
public interface ICottageOwnerRepository extends JpaRepository<CottageOwner, Long> {
	
	@Query("select u from User u where u.accountApproval = ?1 and u.userType = ?2")
	List<CottageOwner> findAllCottageOwnersByAccountApproval(AccountApproval accountApproval, UserType userType);
	
	CottageOwner findByEmail(String email);
	
	CottageOwner findByUserId(Long userId);

}
