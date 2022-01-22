package Team76.InternetSoftwareArchitecture.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.FishingEquipmentForShipDTO;
import Team76.InternetSoftwareArchitecture.iservice.IFishingEquipmentForShipService;
import Team76.InternetSoftwareArchitecture.model.FishingEquipmentForShip;
import Team76.InternetSoftwareArchitecture.repository.IFishingEquipmentForShipRepository;

@Service
public class FishingEquipmentForShipService implements IFishingEquipmentForShipService {

	private IFishingEquipmentForShipRepository fishingEquipmentForShipRepository;

	@Autowired
	public FishingEquipmentForShipService(IFishingEquipmentForShipRepository fishingEquipmentForShipRepository) {
		super();
		this.fishingEquipmentForShipRepository = fishingEquipmentForShipRepository;
	}

	@Override
	public List<FishingEquipmentForShipDTO> getAllFishingEquipmentForShip() {
		List<FishingEquipmentForShipDTO> fishingEquipmentForShipDTOs = new ArrayList<FishingEquipmentForShipDTO>();
		List<FishingEquipmentForShip> fishingEquipmentForShip = fishingEquipmentForShipRepository.findAll();
		
		for (FishingEquipmentForShip fishingEquipment : fishingEquipmentForShip) {
			String fishingEquipmentType = fishingEquipment.getFishingEquipmentForShipType().toString();
			fishingEquipmentForShipDTOs.add(new FishingEquipmentForShipDTO(fishingEquipmentType));
		}
		
		return fishingEquipmentForShipDTOs;
	}	
}
