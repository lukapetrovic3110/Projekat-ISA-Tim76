package Team76.InternetSoftwareArchitecture.iservice;

import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.model.PriceList;

@Service
public interface IPriceListService {
	
	PriceList savePriceList(PriceList priceList);
}
