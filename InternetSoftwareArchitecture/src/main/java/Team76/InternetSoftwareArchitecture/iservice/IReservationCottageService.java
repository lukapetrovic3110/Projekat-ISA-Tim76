package Team76.InternetSoftwareArchitecture.iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.CottageReservationInformationDTO;
import Team76.InternetSoftwareArchitecture.dto.HistoryReservationCottageDTO;
import Team76.InternetSoftwareArchitecture.model.ReservationCottage;

@Service
public interface IReservationCottageService {
	
	List<HistoryReservationCottageDTO> findAllHistoryReservationByClient();
	
	List<ReservationCottage> findAllScheduledReservationByClient();
	
	Boolean cancelReservation(Long reservationId);
	
	List<CottageReservationInformationDTO> findAllReservationsForCottageOwner(Long cottageOwnerId);
}
