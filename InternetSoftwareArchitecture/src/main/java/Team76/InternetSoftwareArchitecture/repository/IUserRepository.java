package Team76.InternetSoftwareArchitecture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Team76.InternetSoftwareArchitecture.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long>{
	
	User findByEmail(String email);
}
