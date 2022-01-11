package Team76.InternetSoftwareArchitecture.iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.HistoryReservationAdventureDTO;
import Team76.InternetSoftwareArchitecture.model.ReservationAdventure;

@Service
public interface IReservationAdventureService {
	public List<HistoryReservationAdventureDTO> findAllHistoryReservationByClient();

	public List<ReservationAdventure> findAllScheduledReservationByClient();
	
	public Boolean cancelReservation(Long reservationId);
}
