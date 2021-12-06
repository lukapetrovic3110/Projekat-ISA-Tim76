package Team76.InternetSoftwareArchitecture.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.iservice.IRuleService;
import Team76.InternetSoftwareArchitecture.model.Rule;
import Team76.InternetSoftwareArchitecture.repository.IRuleRepository;

@Service
public class RuleService implements IRuleService {

	private IRuleRepository ruleRepository;

	@Autowired
	public RuleService(IRuleRepository ruleRepository) {
		super();
		this.ruleRepository = ruleRepository;
	}

	@Override
	public Rule saveRule(Rule rule) {
		return ruleRepository.save(rule);
	}
	
}
