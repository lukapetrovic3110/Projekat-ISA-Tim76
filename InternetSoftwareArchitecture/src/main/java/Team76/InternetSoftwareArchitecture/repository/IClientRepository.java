package Team76.InternetSoftwareArchitecture.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Team76.InternetSoftwareArchitecture.model.Client;

public interface IClientRepository extends JpaRepository<Client, Long> {
	Client findByUserId(Long userId);
}
