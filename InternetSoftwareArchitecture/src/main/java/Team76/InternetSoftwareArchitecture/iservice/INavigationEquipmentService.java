package Team76.InternetSoftwareArchitecture.iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.ShipNavigationEquipmentDTO;

@Service
public interface INavigationEquipmentService {
	
	List<ShipNavigationEquipmentDTO> getAllShipNavigationEquipment();
}
