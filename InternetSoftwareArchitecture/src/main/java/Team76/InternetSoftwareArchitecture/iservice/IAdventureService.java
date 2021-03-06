package Team76.InternetSoftwareArchitecture.iservice;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.AdventureDTO;
import Team76.InternetSoftwareArchitecture.model.Adventure;

@Service
public interface IAdventureService {
	
	List<Adventure> all();
	
	List<AdventureDTO> findAllAdventureWithStatusActive();
	
	AdventureDTO getAdventureById(Long adventureId);

	List<AdventureDTO> findAvailableAdventuresForSelectedDateIntervalAndNumberOfGuests(Date startDateAndTime, Integer duration, Integer numberOfGuests);
	
}
