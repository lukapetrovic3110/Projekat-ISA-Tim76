package Team76.InternetSoftwareArchitecture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Team76.InternetSoftwareArchitecture.model.Client;
import Team76.InternetSoftwareArchitecture.service.ClientService;

@CrossOrigin(origins = "http://localhost:8083")
@RestController
@RequestMapping(value = "/client", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {
	
	private ClientService clientService;
	
	@Autowired
	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@GetMapping(value = "/{clientId}")
	public ResponseEntity<Client> getClient(@PathVariable Long clientId) {
		return new ResponseEntity<Client>(clientService.findByUserId(clientId), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@PostMapping(value = "/update")
	public ResponseEntity<Client> update(@RequestBody Client client) throws Exception {
		return new ResponseEntity<Client>(clientService.update(client), HttpStatus.OK);

	}

}
