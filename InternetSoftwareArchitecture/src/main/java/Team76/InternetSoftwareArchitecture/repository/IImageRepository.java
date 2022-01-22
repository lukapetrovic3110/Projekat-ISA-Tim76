package Team76.InternetSoftwareArchitecture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Team76.InternetSoftwareArchitecture.model.Image;

@Repository
public interface IImageRepository extends JpaRepository<Image, Long> {
	
	@Query(value = "SELECT images_image_id FROM cottage_images WHERE cottage_cottage_id=:cottageId", nativeQuery = true)
	List<Long> getAllImageIdForCottage(Long cottageId);
	
	@Query(value = "SELECT images_image_id FROM ship_images WHERE ship_ship_id=:shipId", nativeQuery = true)
	List<Long> getAllImageIdForShip(Long shipId);
	
}
