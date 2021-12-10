package Team76.InternetSoftwareArchitecture.iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.HistoryReservationCottageDTO;
import Team76.InternetSoftwareArchitecture.model.ReservationCottage;

@Service
public interface IReservationCottageService {
	public List<HistoryReservationCottageDTO> findAllHistoryReservationByClient();
	
	public List<ReservationCottage> findAllScheduledReservationByClient();
}
