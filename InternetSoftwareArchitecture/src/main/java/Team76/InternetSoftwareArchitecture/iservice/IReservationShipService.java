package Team76.InternetSoftwareArchitecture.iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.HistoryReservationShipDTO;

@Service
public interface IReservationShipService {
	public List<HistoryReservationShipDTO> findAllHistoryReservationByClient();
}
