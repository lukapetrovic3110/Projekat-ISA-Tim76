package Team76.InternetSoftwareArchitecture.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.ShipDTO;
import Team76.InternetSoftwareArchitecture.iservice.IShipService;
import Team76.InternetSoftwareArchitecture.model.Image;
import Team76.InternetSoftwareArchitecture.model.Ship;
import Team76.InternetSoftwareArchitecture.repository.IShipRepository;

@Service
public class ShipService implements IShipService  {
	
	private IShipRepository shipRepository;
	
	@Autowired
	public ShipService(IShipRepository shipRepository) {
		this.shipRepository = shipRepository;
	}
	
	@Override
	public List<Ship> all() {
		return shipRepository.findAll();
	}
	
	@Override
	public List<ShipDTO> findAll() {
		List<Ship> ships = all();
		List<ShipDTO> shipDTOs = new ArrayList<ShipDTO>();
		
		for (Ship ship : ships) {
			List<Image> images = ship.getImages();
			List<String> shipImages = new ArrayList<String>();
			for (Image image : images) {
				shipImages.add(image.getName());
			}
			ShipDTO shipDTO = new ShipDTO(ship.getShipId(), ship.getName(), ship.getDescription(), ship.getAddress().getStreet(), ship.getAddress().getStreetNumber(), ship.getAddress().getCity(), ship.getAddress().getCountry(), ship.getAddress().getLongitude(), ship.getAddress().getLatitude(), ship.getRating(), ship.getLength(), ship.getEngineNumber(), ship.getEnginePower(), ship.getMaxSpeed(), ship.getCapacity(), ship.getShipType(), ship.getShipOwner().getFirstName(), ship.getShipOwner().getLastName(), ship.getShipOwner().getEmail(), ship.getShipOwner().getPhoneNumber(), shipImages); 
			shipDTOs.add(shipDTO);
		}
		return shipDTOs;
	}
	
}
