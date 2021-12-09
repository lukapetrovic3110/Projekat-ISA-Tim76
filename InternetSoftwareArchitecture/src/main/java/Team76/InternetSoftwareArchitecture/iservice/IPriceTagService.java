package Team76.InternetSoftwareArchitecture.iservice;

import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.model.PriceTag;

@Service
public interface IPriceTagService {
	
	PriceTag savePriceTag(PriceTag priceTag);
}
