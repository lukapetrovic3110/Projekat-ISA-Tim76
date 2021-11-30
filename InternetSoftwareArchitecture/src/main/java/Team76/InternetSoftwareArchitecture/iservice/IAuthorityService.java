package Team76.InternetSoftwareArchitecture.iservice;

import java.util.Set;

import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.model.Authority;

@Service
public interface IAuthorityService {
	
	Set<Authority> findById(Long id);
	Set<Authority> findByName(String name);
}
