package Team76.InternetSoftwareArchitecture.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.ShipAdditionalServiceDTO;
import Team76.InternetSoftwareArchitecture.iservice.IShipAdditionalServiceService;
import Team76.InternetSoftwareArchitecture.model.ShipAdditionalService;
import Team76.InternetSoftwareArchitecture.repository.IShipAdditionalServiceRepository;

@Service
public class ShipAdditionalServiceService implements IShipAdditionalServiceService {
	
	private IShipAdditionalServiceRepository shipAdditionalServiceRepository;
	
	@Autowired
	public ShipAdditionalServiceService(IShipAdditionalServiceRepository shipAdditionalServiceRepository) {
		super();
		this.shipAdditionalServiceRepository = shipAdditionalServiceRepository;
	}

	@Override
	public List<ShipAdditionalServiceDTO> getAllShipAdditionalServices() {
		List<ShipAdditionalServiceDTO> shipAdditionalServiceDTOs = new ArrayList<ShipAdditionalServiceDTO>();
		List<ShipAdditionalService> shipAdditionalServices = shipAdditionalServiceRepository.findAll();
		
		for (ShipAdditionalService shipAdditionalService : shipAdditionalServices) {
			String shipAdditionalServiceType = shipAdditionalService.getShipAdditionalServiceType().toString();
			shipAdditionalServiceDTOs.add(new ShipAdditionalServiceDTO(shipAdditionalServiceType));
		}
		
		return shipAdditionalServiceDTOs;
	}

}
