package Team76.InternetSoftwareArchitecture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Team76.InternetSoftwareArchitecture.model.Revision;

@Repository
public interface IRevisionRepository extends JpaRepository<Revision, Long> {
	
	@Query(value = "SELECT * FROM revision WHERE cottage_id IS NOT NULL and revision_status = 'WAITING'", nativeQuery = true)
	List<Revision> getCottageRevisions();

	@Query(value = "SELECT * FROM revision WHERE ship_id IS NOT NULL and revision_status = 'WAITING'", nativeQuery = true)
	List<Revision> getShipRevisions();

	@Query(value = "SELECT * FROM revision WHERE adventure_id IS NOT NULL and revision_status = 'WAITING'", nativeQuery = true)
	List<Revision> getAdventureRevisions();

	@Query(value = "SELECT * FROM revision WHERE user_id IS NOT NULL and revision_status = 'WAITING'", nativeQuery = true)
	List<Revision> getUserRevisions();
	
	Revision findByRevisionId(Long revisionId);
	
}
