package Team76.InternetSoftwareArchitecture.iservice;

import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.model.SystemAdministrator;

@Service
public interface ISystemAdministratorService {
	SystemAdministrator findSystemAdministrator();
	SystemAdministrator update(SystemAdministrator systemAdministrator);
}
