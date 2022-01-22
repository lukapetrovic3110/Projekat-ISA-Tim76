package Team76.InternetSoftwareArchitecture.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8083")
@RestController
@RequestMapping(value = "/navigationEquipment", produces = MediaType.APPLICATION_JSON_VALUE)
public class NavigationEquipmentController {

}
