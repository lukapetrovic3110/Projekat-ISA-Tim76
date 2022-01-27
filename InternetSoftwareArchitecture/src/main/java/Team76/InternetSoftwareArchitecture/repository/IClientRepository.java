package Team76.InternetSoftwareArchitecture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Team76.InternetSoftwareArchitecture.model.Client;

@Repository
public interface IClientRepository extends JpaRepository<Client, Long> {
	
	Client findByUserId(Long userId);
	
	@Query(value = "SELECT client_user_id FROM user_cottage_subscriptions WHERE cottage_subscriptions_cottage_id=:cottageId", nativeQuery = true)
	List<Long> getAllSubscribedClientIdForCottage(Long cottageId);
	
	@Query(value = "SELECT client_user_id FROM user_ship_subscriptions WHERE ship_subscriptions_ship_id=:shipId", nativeQuery = true)
	List<Long> getAllSubscribedClientIdForShip(Long shipId);

}
