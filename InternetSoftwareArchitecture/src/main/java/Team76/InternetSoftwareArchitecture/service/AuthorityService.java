package Team76.InternetSoftwareArchitecture.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.iservice.IAuthorityService;
import Team76.InternetSoftwareArchitecture.model.Authority;
import Team76.InternetSoftwareArchitecture.repository.IAuthorityRepository;

@Service
public class AuthorityService implements IAuthorityService{

	private IAuthorityRepository authorityRepository;
	
	@Autowired
	public AuthorityService(IAuthorityRepository authorityRepository) {
		this.authorityRepository = authorityRepository;
	}

	@Override
	public Set<Authority> findById(Long authorityId) {
		Authority authority = authorityRepository.getById(authorityId);
		Set<Authority> authorities = new HashSet<Authority>();
		authorities.add(authority);
		return authorities;
	}

	@Override
	public Set<Authority> findByName(String name) {
		Authority authority = authorityRepository.findByName(name);
		Set<Authority> authorities = new HashSet<Authority>();
		authorities.add(authority);
		return authorities;
	}

}
