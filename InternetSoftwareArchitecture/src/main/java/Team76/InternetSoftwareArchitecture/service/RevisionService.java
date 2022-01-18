package Team76.InternetSoftwareArchitecture.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.ObjectRevisionDTO;
import Team76.InternetSoftwareArchitecture.dto.RevisionRequestDTO;
import Team76.InternetSoftwareArchitecture.dto.UserRevisionDTO;
import Team76.InternetSoftwareArchitecture.iservice.IRevisionService;
import Team76.InternetSoftwareArchitecture.model.Client;
import Team76.InternetSoftwareArchitecture.model.Revision;
import Team76.InternetSoftwareArchitecture.model.RevisionStatus;
import Team76.InternetSoftwareArchitecture.repository.IAdventureRepository;
import Team76.InternetSoftwareArchitecture.repository.ICottageRepository;
import Team76.InternetSoftwareArchitecture.repository.IRevisionRepository;
import Team76.InternetSoftwareArchitecture.repository.IShipRepository;
import Team76.InternetSoftwareArchitecture.repository.IUserRepository;

@Service
public class RevisionService implements IRevisionService {
	
	private IRevisionRepository revisionRepository;
	private IUserRepository userRepository;
	private ICottageRepository cottageRepository;
	private IShipRepository shipRepository;
	private IAdventureRepository adventureRepository;
	
	@Autowired
	public RevisionService(IRevisionRepository revisionRepository, IUserRepository userRepository, ICottageRepository cottageRepository,
			IShipRepository shipRepository, IAdventureRepository adventureRepository) {
		this.revisionRepository = revisionRepository;
		this.userRepository = userRepository;
		this.cottageRepository = cottageRepository;
		this.shipRepository = shipRepository;
		this.adventureRepository = adventureRepository;
	}

	@Override
	public Revision writeRevision(RevisionRequestDTO revisionRequestDTO) {
		Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Revision revision = new Revision();
		revision.setRating(revisionRequestDTO.getRating());
		revision.setComment(revisionRequestDTO.getComment());
		revision.setClient((Client)userRepository.findByEmail(client.getEmail()));	
		revision.setRevisionStatus(RevisionStatus.WAITING);
		switch (revisionRequestDTO.getRevisionType()) {
		case COTTAGE:
			revision.setCottage(cottageRepository.findByCottageId(revisionRequestDTO.getRevisionEntityId()));
			break;
		case SHIP:
			revision.setShip(shipRepository.findByShipId(revisionRequestDTO.getRevisionEntityId()));
			break;
		case ADVENTURE:
			revision.setAdventure(adventureRepository.findByAdventureId(revisionRequestDTO.getRevisionEntityId()));
			break;
		default:
			revision.setUser(userRepository.findByUserId(revisionRequestDTO.getRevisionEntityId()));
			break;
		}
		return revisionRepository.save(revision);
	}

	@Override
	public List<ObjectRevisionDTO> getCottageRevisions() {
		List<Revision> cottageRevisions = new ArrayList<Revision>();
		cottageRevisions = revisionRepository.getCottageRevisions();
		return cottageRevisions.stream().map(c -> new ObjectRevisionDTO(c.getRevisionId(), c.getCottage().getName(), c.getComment(), c.getRating(), c.getClient().getFirstName(), c.getClient().getLastName(), c.getClient().getEmail(), c.getCottage().getCottageOwner().getFirstName(), c.getCottage().getCottageOwner().getLastName(), c.getCottage().getCottageOwner().getEmail())).collect(Collectors.toList());
	}

	@Override
	public List<ObjectRevisionDTO> getShipRevisions() {
		List<Revision> shipRevisions = new ArrayList<Revision>();
		shipRevisions = revisionRepository.getShipRevisions();
		return shipRevisions.stream().map(s -> new ObjectRevisionDTO(s.getRevisionId(), s.getShip().getName(), s.getComment(), s.getRating(), s.getClient().getFirstName(), s.getClient().getLastName(), s.getClient().getEmail(), s.getShip().getShipOwner().getFirstName(), s.getShip().getShipOwner().getLastName(), s.getShip().getShipOwner().getEmail())).collect(Collectors.toList());
	}
	
	@Override
	public List<ObjectRevisionDTO> getAdventureRevisions() {
		List<Revision> adventureRevisions = new ArrayList<Revision>();
		adventureRevisions = revisionRepository.getAdventureRevisions();
		return adventureRevisions.stream().map(a -> new ObjectRevisionDTO(a.getRevisionId(), a.getAdventure().getName(), a.getComment(), a.getRating(), a.getClient().getFirstName(), a.getClient().getLastName(), a.getClient().getEmail(), a.getAdventure().getFishingInstructor().getFirstName(), a.getAdventure().getFishingInstructor().getLastName(), a.getAdventure().getFishingInstructor().getEmail())).collect(Collectors.toList());
	}
	
	
	@Override
	public List<UserRevisionDTO> getUserRevisions() {
		List<Revision> userRevisions = new ArrayList<Revision>();
		userRevisions = revisionRepository.getUserRevisions();
		return userRevisions.stream().map(u -> new UserRevisionDTO(u.getRevisionId(), u.getUser().getFirstName(), u.getUser().getLastName(),  u.getUser().getEmail(),  u.getComment(), u.getRating(), u.getClient().getFirstName(), u.getClient().getLastName(), u.getClient().getEmail())).collect(Collectors.toList());
	
	}
	
}
