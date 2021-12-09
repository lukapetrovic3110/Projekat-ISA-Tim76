package Team76.InternetSoftwareArchitecture.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.iservice.IPriceListService;
import Team76.InternetSoftwareArchitecture.model.PriceList;
import Team76.InternetSoftwareArchitecture.repository.IPriceListRepository;

@Service
public class PriceListService implements IPriceListService {
	
	private IPriceListRepository priceListRepository;
		
	@Autowired
	public PriceListService(IPriceListRepository priceListRepository) {
		super();
		this.priceListRepository = priceListRepository;
	}

	@Override
	public PriceList savePriceList(PriceList priceList) {
		return priceListRepository.save(priceList);
	}

}
