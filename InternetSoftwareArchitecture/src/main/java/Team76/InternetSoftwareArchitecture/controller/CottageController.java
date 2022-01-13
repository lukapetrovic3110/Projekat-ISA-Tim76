package Team76.InternetSoftwareArchitecture.controller;

import java.util.List;

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
import org.springframework.web.util.UriComponentsBuilder;

import Team76.InternetSoftwareArchitecture.dto.AddCottageDTO;
import Team76.InternetSoftwareArchitecture.dto.CottageDTO;
import Team76.InternetSoftwareArchitecture.model.Cottage;
import Team76.InternetSoftwareArchitecture.service.CottageService;

@CrossOrigin(origins = "http://localhost:8083")
@RestController
@RequestMapping(value = "/cottage", produces = MediaType.APPLICATION_JSON_VALUE)
public class CottageController {
	
	private CottageService cottageService;
	
	@Autowired	
	public CottageController(CottageService cottageService) {
		super();
		this.cottageService = cottageService;
	}

	@PreAuthorize("hasRole('ROLE_COTTAGE_OWNER')")
	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody AddCottageDTO addCottageDTO, UriComponentsBuilder uriComponentsBuilder) {
		return new ResponseEntity<>(cottageService.saveCottage(addCottageDTO), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ROLE_COTTAGE_OWNER')")
	@GetMapping("/{id}")
	public ResponseEntity<Cottage> get(@PathVariable Long id) {
		return new ResponseEntity<Cottage>(cottageService.findById(id), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_COTTAGE_OWNER')")
	@GetMapping("/getAll/{id}")
	public ResponseEntity<List<Cottage>> getAll(@PathVariable Long id) {
		return new ResponseEntity<List<Cottage>>(cottageService.getAllCottagesForCottageOwner(id), HttpStatus.OK);
	}
  
	@GetMapping("/all")
	public ResponseEntity<List<Cottage>> all() {
		return new ResponseEntity<List<Cottage>>(cottageService.all(), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<CottageDTO>> findAll() {
		return new ResponseEntity<List<CottageDTO>>(cottageService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/findCottage/{cottageId}")
	public ResponseEntity<CottageDTO> getCottageById(@PathVariable Long cottageId) {
		return new ResponseEntity<CottageDTO>(cottageService.getCottageById(cottageId), HttpStatus.OK);
	}

}
