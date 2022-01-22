package Team76.InternetSoftwareArchitecture.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.ShipNavigationEquipmentDTO;
import Team76.InternetSoftwareArchitecture.iservice.INavigationEquipmentService;
import Team76.InternetSoftwareArchitecture.model.NavigationEquipment;
import Team76.InternetSoftwareArchitecture.repository.INavigationEquipmentRepository;

@Service
public class NavigationEquipmentService implements INavigationEquipmentService {

	private INavigationEquipmentRepository navigationEquipmentRepository;

	@Autowired
	public NavigationEquipmentService(INavigationEquipmentRepository navigationEquipmentRepository) {
		super();
		this.navigationEquipmentRepository = navigationEquipmentRepository;
	}

	@Override
	public List<ShipNavigationEquipmentDTO> getAllShipNavigationEquipment() {
		List<ShipNavigationEquipmentDTO> shipNavigationEquipmentDTOs = new ArrayList<ShipNavigationEquipmentDTO>();
		List<NavigationEquipment> shipNavigationEquipment = navigationEquipmentRepository.findAll();
		
		for (NavigationEquipment navigationEquipment : shipNavigationEquipment) {
			String shipNavigationEquipmentType = navigationEquipment.getNavigationEquipmentType().toString();
			shipNavigationEquipmentDTOs.add(new ShipNavigationEquipmentDTO(shipNavigationEquipmentType));
		}
		
		return shipNavigationEquipmentDTOs;
	}
	
	
	
}
