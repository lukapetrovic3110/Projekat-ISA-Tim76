package Team76.InternetSoftwareArchitecture.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.iservice.IAddressService;
import Team76.InternetSoftwareArchitecture.model.Address;
import Team76.InternetSoftwareArchitecture.repository.IAddressRepository;

@Service
public class AddressService implements IAddressService{

	private IAddressRepository addressRepository;
	
	@Autowired
	public AddressService(IAddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}

	@Override
	public Address findById(Long id) {
		return addressRepository.findById(id).orElse(null);
	}

	@Override
	public Address saveAddress(Address address) {
		return addressRepository.save(address);
	}


}
