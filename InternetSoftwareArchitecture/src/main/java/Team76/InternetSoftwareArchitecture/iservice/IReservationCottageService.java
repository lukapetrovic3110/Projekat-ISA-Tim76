package Team76.InternetSoftwareArchitecture.iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.CottageFastReservationDTO;
import Team76.InternetSoftwareArchitecture.dto.CottageReservationInformationDTO;
import Team76.InternetSoftwareArchitecture.dto.CottageReservationReportDTO;
import Team76.InternetSoftwareArchitecture.dto.DeleteCottageReservationDTO;
import Team76.InternetSoftwareArchitecture.dto.HistoryReservationCottageDTO;
import Team76.InternetSoftwareArchitecture.model.ReservationCottage;
import Team76.InternetSoftwareArchitecture.model.ReservationStatus;

@Service
public interface IReservationCottageService {
	
	List<HistoryReservationCottageDTO> findAllHistoryReservationByClient();
	
	List<ReservationCottage> findAllScheduledReservationByClient();
	
	Boolean cancelReservation(Long reservationId);
	
	List<CottageReservationInformationDTO> findAllReservationsForCottageOwner(Long cottageOwnerId, ReservationStatus reservationStatus);
	
	CottageReservationReportDTO saveReport(CottageReservationReportDTO cottageReservationReportDTO);
	
	List<CottageFastReservationDTO> findAllFastReservationsForCottage(Long cottageId);
	
	CottageFastReservationDTO saveFastReservation(Long cottageId, CottageFastReservationDTO cottageFastReservationDTO);
	
	Boolean deleteFastReservation(DeleteCottageReservationDTO deleteCottageDTO);
	
	Boolean scheduleFastReservation(Long fastReservationId);

}
