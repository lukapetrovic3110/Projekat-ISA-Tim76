package Team76.InternetSoftwareArchitecture.iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.HistoryReservationShipDTO;
import Team76.InternetSoftwareArchitecture.dto.ShipFastReservationDTO;
import Team76.InternetSoftwareArchitecture.dto.ShipReservationCalendarDTO;
import Team76.InternetSoftwareArchitecture.model.ReservationShip;

@Service
public interface IReservationShipService {
	
	List<HistoryReservationShipDTO> findAllHistoryReservationByClient();
	
	List<ReservationShip> findAllScheduledReservationByClient();

	Boolean cancelReservation(Long reservationId);
	
	List<ShipFastReservationDTO> findAllFastReservationsForShip(Long shipId);

	Boolean scheduleFastReservation(Long fastReservationId);
	
	ShipReservationCalendarDTO getAvailabilityCalendarInformation(Long shipId);
	
}
