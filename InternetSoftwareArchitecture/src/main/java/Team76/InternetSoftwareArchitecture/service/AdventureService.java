package Team76.InternetSoftwareArchitecture.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.AdventureDTO;
import Team76.InternetSoftwareArchitecture.iservice.IAdventureService;
import Team76.InternetSoftwareArchitecture.model.Adventure;
import Team76.InternetSoftwareArchitecture.model.Image;
import Team76.InternetSoftwareArchitecture.repository.IAdventureRepository;

@Service
public class AdventureService implements IAdventureService {

	private IAdventureRepository adventureRepository;

	@Autowired
	public AdventureService(IAdventureRepository adventureRepository) {
		this.adventureRepository = adventureRepository;
	}

	@Override
	public List<Adventure> all() {
		return adventureRepository.findAll();
	}

	@Override
	public List<AdventureDTO> findAll() {
		List<Adventure> adventures = all();
		List<AdventureDTO> adventureDTOs = new ArrayList<AdventureDTO>();

		for (Adventure adventure : adventures) {
			List<Image> images = adventure.getImages();
			List<String> adventureImages = new ArrayList<String>();
			for (Image image : images) {
				adventureImages.add(image.getName());
			}
			AdventureDTO adventureDTO = new AdventureDTO(adventure.getAdventureId(), adventure.getName(), adventure.getDescription(), adventure.getRating(), adventure.getInstructorBiography(), adventure.getMaxNumberOfPersons(), adventure.getAddress().getStreet(), adventure.getAddress().getStreetNumber(), adventure.getAddress().getCity(), adventure.getAddress().getCountry(), adventure.getAddress().getLongitude(), adventure.getAddress().getLatitude(), adventure.getAvailabilityStart(), adventure.getAvailabilityEnd(), adventure.getFishingInstructor().getFirstName(), adventure.getFishingInstructor().getLastName(), adventure.getFishingInstructor().getEmail(), adventure.getFishingInstructor().getPhoneNumber(), adventureImages);
			adventureDTOs.add(adventureDTO);
		}

		return adventureDTOs;
	}

}
