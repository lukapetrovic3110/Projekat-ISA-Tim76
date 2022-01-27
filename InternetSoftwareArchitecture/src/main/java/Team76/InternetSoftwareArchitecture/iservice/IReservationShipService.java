package Team76.InternetSoftwareArchitecture.iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.DeleteShipReservationDTO;
import Team76.InternetSoftwareArchitecture.dto.CreateReservationRequestDTO;
import Team76.InternetSoftwareArchitecture.dto.CreateReservationResponseDTO;
import Team76.InternetSoftwareArchitecture.dto.HistoryReservationShipDTO;
import Team76.InternetSoftwareArchitecture.dto.ShipFastReservationDTO;
import Team76.InternetSoftwareArchitecture.dto.ShipReservationCalendarDTO;
import Team76.InternetSoftwareArchitecture.dto.ShipReservationInformationDTO;
import Team76.InternetSoftwareArchitecture.dto.ShipReservationReportDTO;
import Team76.InternetSoftwareArchitecture.model.ReservationShip;
import Team76.InternetSoftwareArchitecture.model.ReservationStatus;

@Service
public interface IReservationShipService {
	
	List<HistoryReservationShipDTO> findAllHistoryReservationByClient();
	
	List<ReservationShip> findAllScheduledReservationByClient();

	Boolean cancelReservation(Long reservationId);
	
	List<ShipFastReservationDTO> findAllFastReservationsForShip(Long shipId);
	
	ShipReservationReportDTO saveReport(ShipReservationReportDTO shipReservationReportDTO);
	
	ShipFastReservationDTO saveFastReservation(Long shipId, ShipFastReservationDTO shipFastReservationDTO);
	
	List<ShipReservationInformationDTO> findAllReservationsForShipOwner(Long shipOwnerId, ReservationStatus reservationStatus);

	Boolean scheduleFastReservation(Long fastReservationId);
	
	Boolean deleteFastReservation(DeleteShipReservationDTO deleteShipReservationDTO);
	
	ShipReservationCalendarDTO getAvailabilityCalendarInformation(Long shipId);
	
	CreateReservationResponseDTO createReservation(CreateReservationRequestDTO createReservationRequestDTO);

}
