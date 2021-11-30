package Team76.InternetSoftwareArchitecture.iservice;

import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.model.Address;

@Service
public interface IAddressService {
	
	public Address findById(Long id);

	Address saveAddress(Address address);
	
}
