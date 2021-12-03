package Team76.InternetSoftwareArchitecture.iservice;

import Team76.InternetSoftwareArchitecture.model.Client;

public interface IClientService {
	Client findByUserId(Long userId);
}
