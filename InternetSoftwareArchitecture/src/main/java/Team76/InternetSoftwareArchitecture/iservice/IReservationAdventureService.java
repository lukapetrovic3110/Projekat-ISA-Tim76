package Team76.InternetSoftwareArchitecture.iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.AdventureFastReservationDTO;
import Team76.InternetSoftwareArchitecture.dto.HistoryReservationAdventureDTO;
import Team76.InternetSoftwareArchitecture.model.ReservationAdventure;

@Service
public interface IReservationAdventureService {
	
	List<HistoryReservationAdventureDTO> findAllHistoryReservationByClient();
	List<ReservationAdventure> findAllScheduledReservationByClient();
	Boolean cancelReservation(Long reservationId);
	List<AdventureFastReservationDTO> findAllFastReservationsForAdventure(Long adventureId);
	Boolean scheduleFastReservation(Long fastReservationId);
}
