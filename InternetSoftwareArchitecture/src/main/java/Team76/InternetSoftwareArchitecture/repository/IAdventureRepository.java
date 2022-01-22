package Team76.InternetSoftwareArchitecture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Team76.InternetSoftwareArchitecture.model.Adventure;

@Repository
public interface IAdventureRepository extends JpaRepository<Adventure, Long> {
	Adventure findByAdventureId(Long adventureId);
	
    @Query(value = "SELECT * FROM adventure WHERE status = 'ACTIVE'", nativeQuery = true)
	List<Adventure> getAllAdventureWithStatusActive();
}
