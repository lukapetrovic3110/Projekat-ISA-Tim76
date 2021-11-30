package Team76.InternetSoftwareArchitecture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Team76.InternetSoftwareArchitecture.model.Authority;

@Repository
public interface IAuthorityRepository extends JpaRepository<Authority, Long> {
	
	Authority findByName(String name);
}
