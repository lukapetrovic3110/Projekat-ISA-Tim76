package Team76.InternetSoftwareArchitecture.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Team76.InternetSoftwareArchitecture.model.User;

public interface IUserRepository extends JpaRepository<User, Long>{
	
	User findByEmail(String email);
}
