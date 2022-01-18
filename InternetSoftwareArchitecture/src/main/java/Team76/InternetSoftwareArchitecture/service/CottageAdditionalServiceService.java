package Team76.InternetSoftwareArchitecture.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.CottageAdditionalServiceDTO;
import Team76.InternetSoftwareArchitecture.iservice.ICottageAdditionalServiceService;
import Team76.InternetSoftwareArchitecture.model.CottageAdditionalService;
import Team76.InternetSoftwareArchitecture.repository.ICottageAdditionalServiceRepository;

@Service
public class CottageAdditionalServiceService implements ICottageAdditionalServiceService {
	
	private ICottageAdditionalServiceRepository cottageAdditionalServiceRepository;

	@Autowired
	public CottageAdditionalServiceService(ICottageAdditionalServiceRepository cottageAdditionalServiceRepository) {
		super();
		this.cottageAdditionalServiceRepository = cottageAdditionalServiceRepository;
	}

	@Override
	public List<CottageAdditionalServiceDTO> getAllCottageAdditionalServices() {
		List<CottageAdditionalServiceDTO> cottageAdditionalServiceDTOs = new ArrayList<CottageAdditionalServiceDTO>();
		List<CottageAdditionalService> cottageAdditionalServices = cottageAdditionalServiceRepository.findAll();
		
		for (CottageAdditionalService cottageAdditionalService : cottageAdditionalServices) {
			String cottageAdditionalServiceType = cottageAdditionalService.getCottageAdditionalServiceType().toString();
			cottageAdditionalServiceDTOs.add(new CottageAdditionalServiceDTO(cottageAdditionalServiceType));
		}
		
		return cottageAdditionalServiceDTOs;
	}

}
