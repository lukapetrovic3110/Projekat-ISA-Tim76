package Team76.InternetSoftwareArchitecture.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.iservice.ISystemAdministratorService;
import Team76.InternetSoftwareArchitecture.model.SystemAdministrator;
import Team76.InternetSoftwareArchitecture.repository.ISystemAdministratorRepository;

@Service
public class SystemAdministratorService implements ISystemAdministratorService{
	
	private ISystemAdministratorRepository systemAdministratorRepository;
	
	@Autowired
	public SystemAdministratorService(ISystemAdministratorRepository systemAdministratorRepository) {
		this.systemAdministratorRepository = systemAdministratorRepository;
	}

	@Override
	public SystemAdministrator findSystemAdministrator() {
		SystemAdministrator systemAdministrator = (SystemAdministrator) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return systemAdministratorRepository.findByUserId(systemAdministrator.getUserId());
	}
	
	@Override
	public SystemAdministrator update(SystemAdministrator systemAdministrator) {
		SystemAdministrator existingSystemAdministrator = systemAdministratorRepository.findByUserId(systemAdministrator.getUserId());
		existingSystemAdministrator.setFirstName(systemAdministrator.getFirstName());
		existingSystemAdministrator.setLastName(systemAdministrator.getLastName());
		existingSystemAdministrator.setAddress(systemAdministrator.getAddress());
		existingSystemAdministrator.setPhoneNumber(systemAdministrator.getPhoneNumber());
		return systemAdministratorRepository.save(existingSystemAdministrator);
	}
	
}
