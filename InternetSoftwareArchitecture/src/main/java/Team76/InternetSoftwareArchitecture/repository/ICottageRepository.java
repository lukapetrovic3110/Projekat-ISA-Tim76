package Team76.InternetSoftwareArchitecture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import Team76.InternetSoftwareArchitecture.model.Cottage;

@Repository
public interface ICottageRepository extends JpaRepository<Cottage, Long> {
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO cottage_images (cottage_cottage_id, images_image_id) VALUES (:cottageId, :imageId)", nativeQuery = true)
	void saveImageForCottage(Long cottageId, Long imageId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE cottage SET cottage_owner_id=:cottageOwnerId WHERE cottage_id=:cottageId", nativeQuery = true)
	void saveCottageOwnerForCottage(@Param("cottageId") Long cottageId, @Param("cottageOwnerId") Long cottageOwnerId);
	
	@Query(value = "SELECT * FROM cottage WHERE cottage_owner_id=:cottageOwnerId", nativeQuery = true)
	List<Cottage> getAllCottagesForCottageOwner(Long cottageOwnerId);
	
	@Query(value = "SELECT cottage_id FROM cottage WHERE cottage_owner_id=:cottageOwnerId", nativeQuery = true)
	List<Long> getAllCottageIdForCottageOwner(Long cottageOwnerId);
	
	Cottage findByCottageId(Long id);

}
