package Team76.InternetSoftwareArchitecture.iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.AddCottageDTO;
import Team76.InternetSoftwareArchitecture.dto.CottageDTO;
import Team76.InternetSoftwareArchitecture.dto.DeleteCottageDTO;
import Team76.InternetSoftwareArchitecture.model.Cottage;

@Service
public interface ICottageService {
	
	Cottage saveCottage(AddCottageDTO addCottageDTO);
	
	Cottage editCottage(AddCottageDTO addCottageDTO, Long cottageId);
	
	Boolean deleteCottage(DeleteCottageDTO deleteCottageDTO);
	
	Cottage findById(Long id);
	
	CottageDTO getCottageById(Long id);
	
	void saveImageForCottage(Long cottageId, Long imageId);
	
	void deleteImagesForCottage(Long cottageId);
	
	List<CottageDTO> getAllCottagesForCottageOwner(Long cottageOwnerId);

	public List<Cottage> all();
	
	public List<CottageDTO> findAll();
	
}
