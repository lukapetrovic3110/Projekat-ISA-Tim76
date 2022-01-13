package Team76.InternetSoftwareArchitecture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Team76.InternetSoftwareArchitecture.model.AccountApproval;
import Team76.InternetSoftwareArchitecture.model.FishingInstructor;
import Team76.InternetSoftwareArchitecture.model.UserType;

@Repository
public interface IFishingInstructorRepository extends JpaRepository<FishingInstructor, Long> {
	
	@Query("select u from User u where u.accountApproval = ?1 and u.userType = ?2")
	List<FishingInstructor> findAllFishingInstructorsByAccountApproval(AccountApproval accountApproval, UserType userType);
	
	FishingInstructor findByEmail(String email);
	
	FishingInstructor findByUserId(Long userId);

}
