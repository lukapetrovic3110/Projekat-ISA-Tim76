package Team76.InternetSoftwareArchitecture.iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.HistoryReservationAdventureDTO;

@Service
public interface IReservationAdventureService {
	public List<HistoryReservationAdventureDTO> findAllHistoryReservationByClient();
}
