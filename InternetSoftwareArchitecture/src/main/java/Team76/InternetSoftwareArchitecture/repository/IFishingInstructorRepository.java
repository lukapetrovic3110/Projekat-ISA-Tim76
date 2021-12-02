package Team76.InternetSoftwareArchitecture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Team76.InternetSoftwareArchitecture.model.AccountApproval;
import Team76.InternetSoftwareArchitecture.model.FishingInstructor;
import Team76.InternetSoftwareArchitecture.model.User;

@Repository
public interface IFishingInstructorRepository extends JpaRepository<FishingInstructor, Long> {
	
	@Query("select u from User u where u.accountApproval = ?1")
	List<User> findAllFishingInstructorsByAccountApproval(AccountApproval accountApproval);

}
