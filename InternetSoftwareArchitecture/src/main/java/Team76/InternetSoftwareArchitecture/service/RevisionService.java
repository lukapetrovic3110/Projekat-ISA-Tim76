package Team76.InternetSoftwareArchitecture.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.RevisionRequestDTO;
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
			System.out.println("COTTAGE");
			revision.setCottage(cottageRepository.findByCottageId(revisionRequestDTO.getRevisionEntityId()));
			break;
		case SHIP:
			System.out.println("COTTAGE");
			revision.setShip(shipRepository.findByShipId(revisionRequestDTO.getRevisionEntityId()));
			break;
		case ADVENTURE:
			System.out.println("COTTAGE");
			revision.setAdventure(adventureRepository.findByAdventureId(revisionRequestDTO.getRevisionEntityId()));
			break;
		default:
			System.out.println("COTTAGE");
			revision.setUser(userRepository.findByUserId(revisionRequestDTO.getRevisionEntityId()));
			break;
		}
		return revisionRepository.save(revision);
	}
	
}
