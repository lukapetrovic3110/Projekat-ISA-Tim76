package Team76.InternetSoftwareArchitecture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import Team76.InternetSoftwareArchitecture.model.Ship;

@Repository
public interface IShipRepository extends JpaRepository<Ship, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_ship_subscriptions (client_user_id, ship_subscriptions_ship_id) VALUES (:userId, :shipId)", nativeQuery = true)
    void addNewShipSubscriptions(Long userId, Long shipId);
    
    @Query(value = "SELECT * FROM ship WHERE status = 'ACTIVE'", nativeQuery = true)
	List<Ship> getAllShipWithStatusActive();
    
    @Query(value = "SELECT * FROM ship WHERE ship_owner_id=:shipOwnerId and status = 'ACTIVE'", nativeQuery = true)
	List<Ship> getAllShipsForShipOwner(Long shipOwnerId);
    
    @Query(value = "SELECT ship_id FROM ship WHERE ship_owner_id=:shipOwnerId", nativeQuery = true)
	List<Long> getAllShipsIdForShipOwner(Long shipOwnerId);

    Ship findByShipId(Long shipId);
}
