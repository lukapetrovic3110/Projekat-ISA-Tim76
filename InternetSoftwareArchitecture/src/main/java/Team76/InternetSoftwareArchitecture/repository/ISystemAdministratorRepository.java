package Team76.InternetSoftwareArchitecture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Team76.InternetSoftwareArchitecture.model.SystemAdministrator;

@Repository
public interface ISystemAdministratorRepository extends JpaRepository<SystemAdministrator, Long> {
	SystemAdministrator findByUserId(Long userId);
}
