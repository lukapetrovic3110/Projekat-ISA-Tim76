package Team76.InternetSoftwareArchitecture.iservice;

import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.model.Rule;

@Service
public interface IRuleService {
	
	Rule saveRule(Rule rule);
}
