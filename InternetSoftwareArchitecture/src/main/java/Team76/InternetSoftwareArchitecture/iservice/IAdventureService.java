package Team76.InternetSoftwareArchitecture.iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.AdventureDTO;
import Team76.InternetSoftwareArchitecture.model.Adventure;

@Service
public interface IAdventureService {
	
	public List<Adventure> all();
	
	public List<AdventureDTO> findAll();
}
