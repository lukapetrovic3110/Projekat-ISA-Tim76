package Team76.InternetSoftwareArchitecture.iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.AddCottageDTO;
import Team76.InternetSoftwareArchitecture.model.Cottage;

@Service
public interface ICottageService {
	
	Cottage saveCottage(AddCottageDTO addCottageDTO);
	
	Cottage findById(Long id);
	
	void saveImageForCottage(Long cottageId, Long imageId);

	public List<Cottage> findAll();
	
}
