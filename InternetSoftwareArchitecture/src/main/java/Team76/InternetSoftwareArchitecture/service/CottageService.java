package Team76.InternetSoftwareArchitecture.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.AddCottageDTO;
import Team76.InternetSoftwareArchitecture.iservice.ICottageService;
import Team76.InternetSoftwareArchitecture.model.Cottage;
import Team76.InternetSoftwareArchitecture.model.PriceList;
import Team76.InternetSoftwareArchitecture.model.PriceTag;
import Team76.InternetSoftwareArchitecture.model.Rule;
import Team76.InternetSoftwareArchitecture.repository.IAddressRepository;
import Team76.InternetSoftwareArchitecture.repository.ICottageRepository;

@Service
public class CottageService implements ICottageService {
	
	private ICottageRepository cottageRepository;
	
	private IAddressRepository addressRepository;

	@Autowired
	public CottageService(ICottageRepository cottageRepository, IAddressRepository addressRepository) {
		super();
		this.cottageRepository = cottageRepository;
		this.addressRepository = addressRepository;
	}

	@Override
	public Cottage saveCottage(AddCottageDTO addCottageDTO) {
		Cottage cottage = new Cottage();
		cottage.setName(addCottageDTO.getName());
		cottage.setDescription(addCottageDTO.getDescription());
		cottage.setAddress(addressRepository.save(addCottageDTO.getAddress()));
		cottage.setNumberOfRooms(addCottageDTO.getNumberOfRooms());
		cottage.setNumberOfBedsPerRoom(addCottageDTO.getNumberOfBedsPerRoom());
		cottage.setAvailabilityStart(addCottageDTO.getAvailabilityStart());
		cottage.setAvailabilityEnd(addCottageDTO.getAvailabilityEnd());
		
		Set<PriceTag> priceTags = new HashSet<PriceTag>();
		for (String priceTag : addCottageDTO.getPriceList()) {
			String[] priceTagInfo = priceTag.split(";");
			priceTags.add(new PriceTag(Double.parseDouble(priceTagInfo[1]), priceTagInfo[0]));
		}
		cottage.setPriceList(new PriceList(priceTags));
		
		Set<Rule> cottageRules = new HashSet<Rule>();
		for (String cottageRule : addCottageDTO.getCottageRules()) {
			cottageRules.add(new Rule(cottageRule));
		}
		cottage.setCottageRules(cottageRules);
		
		Cottage cottagedb = cottageRepository.save(cottage);
		cottageRepository.saveCottageOwnerForCottage(cottagedb.getCottageId(), addCottageDTO.getCottageOwnerId());
		
		return findById(cottagedb.getCottageId());
	}

	@Override
	public Cottage findById(Long id) {
		return cottageRepository.findByCottageId(id);
	}

	@Override
	public void saveImageForCottage(Long cottageId, Long imageId) {
		cottageRepository.saveImageForCottage(cottageId, imageId);
	}

}
