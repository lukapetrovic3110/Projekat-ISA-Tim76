package Team76.InternetSoftwareArchitecture.iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.ShipDTO;
import Team76.InternetSoftwareArchitecture.model.Ship;

@Service
public interface IShipService {
	
	List<Ship> all();
	
	List<ShipDTO> findAll();
	
	ShipDTO getShipById(Long shipId);
}
