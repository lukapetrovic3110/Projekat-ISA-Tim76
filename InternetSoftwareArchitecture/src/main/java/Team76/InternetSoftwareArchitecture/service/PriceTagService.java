package Team76.InternetSoftwareArchitecture.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.iservice.IPriceTagService;
import Team76.InternetSoftwareArchitecture.model.PriceTag;
import Team76.InternetSoftwareArchitecture.repository.IPriceTagRepository;

@Service
public class PriceTagService implements IPriceTagService {
	
	private IPriceTagRepository priceTagRepository;
	
	
	@Autowired
	public PriceTagService(IPriceTagRepository priceTagRepository) {
		super();
		this.priceTagRepository = priceTagRepository;
	}

	@Override
	public PriceTag savePriceTag(PriceTag priceTag) {
		return priceTagRepository.save(priceTag);
	}

}
