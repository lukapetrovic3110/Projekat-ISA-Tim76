package Team76.InternetSoftwareArchitecture.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.AcceptRevisionRequestDTO;
import Team76.InternetSoftwareArchitecture.dto.DeclineRevisionRequestDTO;
import Team76.InternetSoftwareArchitecture.dto.ObjectRevisionDTO;
import Team76.InternetSoftwareArchitecture.dto.RevisionRequestDTO;
import Team76.InternetSoftwareArchitecture.dto.UserRevisionDTO;
import Team76.InternetSoftwareArchitecture.iservice.IRevisionService;
import Team76.InternetSoftwareArchitecture.model.Adventure;
import Team76.InternetSoftwareArchitecture.model.Client;
import Team76.InternetSoftwareArchitecture.model.Cottage;
import Team76.InternetSoftwareArchitecture.model.CottageOwner;
import Team76.InternetSoftwareArchitecture.model.FishingInstructor;
import Team76.InternetSoftwareArchitecture.model.Revision;
import Team76.InternetSoftwareArchitecture.model.RevisionStatus;
import Team76.InternetSoftwareArchitecture.model.Ship;
import Team76.InternetSoftwareArchitecture.model.ShipOwner;
import Team76.InternetSoftwareArchitecture.model.SystemAdministrator;
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
	private EmailService emailService;
	
	@Autowired
	public RevisionService(IRevisionRepository revisionRepository, IUserRepository userRepository, ICottageRepository cottageRepository,
			IShipRepository shipRepository, IAdventureRepository adventureRepository, EmailService emailService) {
		this.revisionRepository = revisionRepository;
		this.userRepository = userRepository;
		this.cottageRepository = cottageRepository;
		this.shipRepository = shipRepository;
		this.adventureRepository = adventureRepository;
		this.emailService = emailService;
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

	@Override
	public Boolean acceptRevisionRequest(AcceptRevisionRequestDTO acceptRevisionRequestDTO) {
		SystemAdministrator systemAdministrator = (SystemAdministrator) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		StringBuilder systemAdministratorName = new StringBuilder();
		systemAdministratorName.append(systemAdministrator.getFirstName());
		systemAdministratorName.append(" ");
		systemAdministratorName.append(systemAdministrator.getLastName());
		Revision revision = revisionRepository.findByRevisionId(acceptRevisionRequestDTO.getRevisionRequestId());
		Double rating = revision.getRating();
		revision.setRevisionStatus(RevisionStatus.APPROVED);
		Client client = (Client) userRepository.findByEmail(acceptRevisionRequestDTO.getClientEmail());
		StringBuilder clientName = new StringBuilder();
		clientName.append(client.getFirstName());
		clientName.append(" ");
		clientName.append(client.getLastName());
		
		switch (acceptRevisionRequestDTO.getRevisionType()) {
		case COTTAGE:
			Cottage cottage = cottageRepository.findByCottageId(revision.getCottage().getCottageId());
			cottage.setRating((rating + cottage.getRating())/2.);
			cottageRepository.save(cottage);
			try {
				sendAcceptedEmail(acceptRevisionRequestDTO.getUserEmail(), revision.getComment(), revision.getRating().toString(), clientName.toString(), systemAdministratorName.toString());
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		case SHIP:
			Ship ship = shipRepository.findByShipId(revision.getShip().getShipId());
			ship.setRating((rating + ship.getRating())/2.);
			shipRepository.save(ship);
			try {
				sendAcceptedEmail(acceptRevisionRequestDTO.getUserEmail(), revision.getComment(), revision.getRating().toString(), clientName.toString(), systemAdministratorName.toString());
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		case ADVENTURE:
			Adventure adventure = adventureRepository.findByAdventureId(revision.getAdventure().getAdventureId());
			adventure.setRating((rating + adventure.getRating())/2.);
			adventureRepository.save(adventure);
			try {
				sendAcceptedEmail(acceptRevisionRequestDTO.getUserEmail(), revision.getComment(), revision.getRating().toString(), clientName.toString(), systemAdministratorName.toString());
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		case COTTAGE_OWNER:
			CottageOwner cottageOwner = (CottageOwner)userRepository.findByEmail(acceptRevisionRequestDTO.getUserEmail());
			cottageOwner.setRating((rating + cottageOwner.getRating())/2.);
			userRepository.save(cottageOwner);
			try {
				sendAcceptedEmail(acceptRevisionRequestDTO.getUserEmail(), revision.getComment(), revision.getRating().toString(), clientName.toString(), systemAdministratorName.toString());
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		case SHIP_OWNER:
			ShipOwner shipOwner = (ShipOwner)userRepository.findByEmail(acceptRevisionRequestDTO.getUserEmail());
			shipOwner.setRating((rating + shipOwner.getRating())/2.);
			userRepository.save(shipOwner);
			try {
				sendAcceptedEmail(acceptRevisionRequestDTO.getUserEmail(), revision.getComment(), revision.getRating().toString(), clientName.toString(), systemAdministratorName.toString());
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		case FISHING_INSTRUCTOR:
			FishingInstructor fishingInstructor = (FishingInstructor)userRepository.findByEmail(acceptRevisionRequestDTO.getUserEmail());
			fishingInstructor.setRating((rating + fishingInstructor.getRating())/2.);
			userRepository.save(fishingInstructor);
			try {
				sendAcceptedEmail(acceptRevisionRequestDTO.getUserEmail(), revision.getComment(), revision.getRating().toString(), clientName.toString(), systemAdministratorName.toString());
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		default:
			System.out.println("Error Revision Type");
			return false;
		}
	}

	@Override
	public Boolean declineRevisionRequest(DeclineRevisionRequestDTO declineRevisionRequestDTO) {
		Revision revision = revisionRepository.findByRevisionId(declineRevisionRequestDTO.getRevisionRequestId());
		revision.setRevisionStatus(RevisionStatus.DECLINED);
		if(revisionRepository.save(revision) != null)
			return true;
		else 
			return false;
	}
	
	private void sendAcceptedEmail(String userEmail, String revisionComment, String revisionRating, String clientName, String systemAdministratorName) {
		emailService.sendNotificationAsync(userEmail, "New revision", createMessageText("You have received a new revision.", revisionComment, revisionRating, clientName, systemAdministratorName));
	}
	
	private String createMessageText(String captionMessageText, String revisionComment, String revisionRating, String clientName, String systemAdministratorName) {
		StringBuilder messageText = new StringBuilder();
		messageText.append(captionMessageText);
		messageText.append("\nRevision comment: \"");
		messageText.append(revisionComment);
		messageText.append("\"");
		messageText.append("\nRevision rating: ");
		messageText.append(revisionRating);
		messageText.append("\nRevision by ");
		messageText.append(clientName);
		messageText.append("\nYours,\n");
		messageText.append(systemAdministratorName);
		return messageText.toString();
	}
	
}
