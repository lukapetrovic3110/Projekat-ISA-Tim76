package Team76.InternetSoftwareArchitecture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Team76.InternetSoftwareArchitecture.model.PriceTag;

@Repository
public interface IPriceTagRepository extends JpaRepository<PriceTag, Long> {

}
