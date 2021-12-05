package Team76.InternetSoftwareArchitecture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Team76.InternetSoftwareArchitecture.model.Client;

@Repository
public interface IClientRepository extends JpaRepository<Client, Long> {
	Client findByUserId(Long userId);
}
