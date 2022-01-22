package Team76.InternetSoftwareArchitecture.iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.ShipAdditionalServiceDTO;

@Service
public interface IShipAdditionalServiceService {
	
	List<ShipAdditionalServiceDTO> getAllShipAdditionalServices();
	
}
