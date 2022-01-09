package Team76.InternetSoftwareArchitecture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Team76.InternetSoftwareArchitecture.model.UserDeleteAccount;

@Repository
public interface IUserDeleteAccountRepository extends JpaRepository<UserDeleteAccount, Long> {

	@Query("SELECT u FROM UserDeleteAccount u WHERE u.userDeleteAccountStatus = 'WAITING'")
	List<UserDeleteAccount> findWaitingProfileDeleteRequests();
	
	@Query(value = "SELECT * FROM user_delete_account WHERE user_delete_account_id=:userDeleteAccountId", nativeQuery = true)
	UserDeleteAccount findUserDeleteAccountByUserDeleteAccountId(Long userDeleteAccountId);
	
}
