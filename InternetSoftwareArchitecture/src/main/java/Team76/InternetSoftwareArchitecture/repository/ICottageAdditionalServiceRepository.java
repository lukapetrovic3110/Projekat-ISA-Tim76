package Team76.InternetSoftwareArchitecture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Team76.InternetSoftwareArchitecture.model.CottageAdditionalService;

@Repository
public interface ICottageAdditionalServiceRepository extends JpaRepository<CottageAdditionalService, Long> {

}
