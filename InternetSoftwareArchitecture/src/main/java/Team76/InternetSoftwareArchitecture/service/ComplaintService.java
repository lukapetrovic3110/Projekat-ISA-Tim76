package Team76.InternetSoftwareArchitecture.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.AnswerComplaintRequestDTO;
import Team76.InternetSoftwareArchitecture.dto.ComplaintRequestDTO;
import Team76.InternetSoftwareArchitecture.dto.ObjectComplaintDTO;
import Team76.InternetSoftwareArchitecture.dto.UserComplaintDTO;
import Team76.InternetSoftwareArchitecture.iservice.IComplaintService;
import Team76.InternetSoftwareArchitecture.model.Client;
import Team76.InternetSoftwareArchitecture.model.Complaint;
import Team76.InternetSoftwareArchitecture.model.ComplaintStatus;
import Team76.InternetSoftwareArchitecture.model.SystemAdministrator;
import Team76.InternetSoftwareArchitecture.repository.IComplaintRepository;
import Team76.InternetSoftwareArchitecture.repository.ICottageRepository;
import Team76.InternetSoftwareArchitecture.repository.IShipRepository;
import Team76.InternetSoftwareArchitecture.repository.IUserRepository;

@Service
public class ComplaintService implements IComplaintService {
	
	private IComplaintRepository complaintRepository;
	private ICottageRepository cottageRepository;
	private IShipRepository shipRepository;
	private IUserRepository userRepository;
	private EmailService emailService;
	
	@Autowired
	public ComplaintService(IComplaintRepository complaintRepository, ICottageRepository cottageRepository, IShipRepository shipRepository,
			IUserRepository userRepository, EmailService emailService) {
		this.complaintRepository = complaintRepository;
		this.shipRepository = shipRepository;
		this.cottageRepository = cottageRepository;
		this.userRepository = userRepository;
		this.emailService = emailService;
	}

	@Override
	public Complaint writeCottageComplaint(ComplaintRequestDTO complaintRequestDTO) {
		Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Complaint complaint = new Complaint();
		complaint.setClient((Client)userRepository.findByEmail(client.getEmail()));
		complaint.setComment(complaintRequestDTO.getComment());
		complaint.setCottage(cottageRepository.findByCottageId(complaintRequestDTO.getComplaintEntityId()));
		complaint.setComplaintStatus(ComplaintStatus.WAITING);
		return complaintRepository.save(complaint);
	}
	
	@Override
	public Complaint writeShipComplaint(ComplaintRequestDTO complaintRequestDTO) {
		Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Complaint complaint = new Complaint();
		complaint.setClient((Client)userRepository.findByEmail(client.getEmail()));
		complaint.setComment(complaintRequestDTO.getComment());
		complaint.setShip(shipRepository.findByShipId(complaintRequestDTO.getComplaintEntityId()));
		complaint.setComplaintStatus(ComplaintStatus.WAITING);
		return complaintRepository.save(complaint);
	}
	
	
	@Override
	public Complaint writeUserComplaint(ComplaintRequestDTO complaintRequestDTO) {
		Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Complaint complaint = new Complaint();
		complaint.setClient((Client)userRepository.findByEmail(client.getEmail()));
		complaint.setComment(complaintRequestDTO.getComment());
		complaint.setUser(userRepository.findByUserId(complaintRequestDTO.getComplaintEntityId()));
		complaint.setComplaintStatus(ComplaintStatus.WAITING);
		return complaintRepository.save(complaint);
	}
	
	@Override
	public List<ObjectComplaintDTO> getCottageComplaints() {
		List<Complaint> cottageComplaints = new ArrayList<Complaint>();
		cottageComplaints = complaintRepository.getCottageComplaints();
		return cottageComplaints.stream().map(c -> new ObjectComplaintDTO(c.getComplaintId(), c.getCottage().getName(), c.getComment(), c.getClient().getFirstName(), c.getClient().getLastName(), c.getClient().getEmail(), c.getCottage().getCottageOwner().getFirstName(), c.getCottage().getCottageOwner().getLastName(), c.getCottage().getCottageOwner().getEmail())).
				collect(Collectors.toList());
	}

	@Override
	public List<ObjectComplaintDTO> getShipComplaints() {
		List<Complaint> shipComplaints = new ArrayList<Complaint>();
		shipComplaints = complaintRepository.getShipComplaints();
		return shipComplaints.stream().map(s -> new ObjectComplaintDTO(s.getComplaintId(), s.getShip().getName(), s.getComment(), s.getClient().getFirstName(), s.getClient().getLastName(), s.getClient().getEmail(), s.getShip().getShipOwner().getFirstName(), s.getShip().getShipOwner().getLastName(), s.getShip().getShipOwner().getEmail())).
				collect(Collectors.toList());
	}

	@Override
	public List<UserComplaintDTO> getUserComplaints() {
		List<Complaint> userComplaints = new ArrayList<Complaint>();
		userComplaints = complaintRepository.getUserComplaints();
		return userComplaints.stream().map(u -> new UserComplaintDTO(u.getComplaintId(), u.getUser().getFirstName(), u.getUser().getLastName(),  u.getUser().getEmail(),  u.getComment(), u.getClient().getFirstName(), u.getClient().getLastName(), u.getClient().getEmail())).collect(Collectors.toList());
	}
	
	@Override
	public Boolean accpetCottageComplaintRequest(AnswerComplaintRequestDTO answerComplaintRequestDTO) {
		SystemAdministrator systemAdministrator = (SystemAdministrator) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Complaint c = complaintRepository.findById(answerComplaintRequestDTO.getComplaintRequestId()).get();
		c.setComplaintStatus(ComplaintStatus.APPROVED);
		complaintRepository.save(c);
		try {
			StringBuilder systemAdministratorName = new StringBuilder();
			systemAdministratorName.append(systemAdministrator.getFirstName());
			systemAdministratorName.append(" ");
			systemAdministratorName.append(systemAdministrator.getLastName());
			sendAcceptedCottageComplaintEmails(answerComplaintRequestDTO, c.getComment(), systemAdministratorName.toString());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean declineCottageComplaintRequest(AnswerComplaintRequestDTO answerComplaintRequestDTO) {
		SystemAdministrator systemAdministrator = (SystemAdministrator) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Complaint c = complaintRepository.findById(answerComplaintRequestDTO.getComplaintRequestId()).get();
		c.setComplaintStatus(ComplaintStatus.DECLINED);
		complaintRepository.save(c);
		try {
			StringBuilder systemAdministratorName = new StringBuilder();
			systemAdministratorName.append(systemAdministrator.getFirstName());
			systemAdministratorName.append(" ");
			systemAdministratorName.append(systemAdministrator.getLastName());
			sendDeclinedCottageComplaintEmails(answerComplaintRequestDTO, c.getComment(), systemAdministratorName.toString());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean acceptShipComplaintRequest(AnswerComplaintRequestDTO answerComplaintRequestDTO) {
		SystemAdministrator systemAdministrator = (SystemAdministrator) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Complaint c = complaintRepository.findById(answerComplaintRequestDTO.getComplaintRequestId()).get();
		c.setComplaintStatus(ComplaintStatus.APPROVED);
		complaintRepository.save(c);
		try {
			StringBuilder systemAdministratorName = new StringBuilder();
			systemAdministratorName.append(systemAdministrator.getFirstName());
			systemAdministratorName.append(" ");
			systemAdministratorName.append(systemAdministrator.getLastName());
			sendAcceptedShipComplaintEmails(answerComplaintRequestDTO, c.getComment(), systemAdministratorName.toString());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean declineShipComplaintRequest(AnswerComplaintRequestDTO answerComplaintRequestDTO) {
		SystemAdministrator systemAdministrator = (SystemAdministrator) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Complaint c = complaintRepository.findById(answerComplaintRequestDTO.getComplaintRequestId()).get();
		c.setComplaintStatus(ComplaintStatus.DECLINED);
		complaintRepository.save(c);
		try {
			StringBuilder systemAdministratorName = new StringBuilder();
			systemAdministratorName.append(systemAdministrator.getFirstName());
			systemAdministratorName.append(" ");
			systemAdministratorName.append(systemAdministrator.getLastName());
			sendDeclinedShipComplaintEmails(answerComplaintRequestDTO, c.getComment(), systemAdministratorName.toString());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean acceptUserComplaintRequest(AnswerComplaintRequestDTO answerComplaintRequestDTO) {
		SystemAdministrator systemAdministrator = (SystemAdministrator) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Complaint c = complaintRepository.findById(answerComplaintRequestDTO.getComplaintRequestId()).get();
		c.setComplaintStatus(ComplaintStatus.APPROVED);
		complaintRepository.save(c);
		try {
			StringBuilder systemAdministratorName = new StringBuilder();
			systemAdministratorName.append(systemAdministrator.getFirstName());
			systemAdministratorName.append(" ");
			systemAdministratorName.append(systemAdministrator.getLastName());
			sendAcceptedUserComplaintEmails(answerComplaintRequestDTO, c.getComment(), systemAdministratorName.toString());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean declineUserComplaintRequest(AnswerComplaintRequestDTO answerComplaintRequestDTO) {
		SystemAdministrator systemAdministrator = (SystemAdministrator) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Complaint c = complaintRepository.findById(answerComplaintRequestDTO.getComplaintRequestId()).get();
		c.setComplaintStatus(ComplaintStatus.DECLINED);
		complaintRepository.save(c);
		try {
			StringBuilder systemAdministratorName = new StringBuilder();
			systemAdministratorName.append(systemAdministrator.getFirstName());
			systemAdministratorName.append(" ");
			systemAdministratorName.append(systemAdministrator.getLastName());
			sendDeclinedUserComplaintEmails(answerComplaintRequestDTO, c.getComment(), systemAdministratorName.toString());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private void sendAcceptedCottageComplaintEmails(AnswerComplaintRequestDTO answerComplaintRequestDTO, String complaintComment, String systemAdministratorName) {
		StringBuilder text1 = new StringBuilder();
		text1.append("We have accepted your cottage complaint request.");
		text1.append("\nYour complaint: \"");
		text1.append(complaintComment);
		text1.append("\"");
		text1.append("\nAnswer: ");
		text1.append(answerComplaintRequestDTO.getComment());
		text1.append("\n");
		text1.append(systemAdministratorName);
		emailService.sendNotificaitionAsync(answerComplaintRequestDTO.getClientEmail(), "Accepted cottage complaint request", text1.toString());
		
		StringBuilder text2 = new StringBuilder();
		text2.append("We have accepted the complaint against your cottage.");
		text2.append("\nComplaint: \"");
		text2.append(complaintComment);
		text2.append("\"");
		text2.append("\nAnswer: ");
		text2.append(answerComplaintRequestDTO.getComment());
		text2.append("\n");
		text2.append(systemAdministratorName);
		emailService.sendNotificaitionAsync(answerComplaintRequestDTO.getUserEmail(), "Cottage complaint", text2.toString());
	}
	
	private void sendAcceptedShipComplaintEmails(AnswerComplaintRequestDTO answerComplaintRequestDTO, String complaintComment,String systemAdministratorName) {
		StringBuilder text1 = new StringBuilder();
		text1.append("We have accepted your ship complaint request.");
		text1.append("\nYour complaint: \"");
		text1.append(complaintComment);
		text1.append("\"");
		text1.append("\nAnswer: ");
		text1.append(answerComplaintRequestDTO.getComment());
		text1.append("\n");
		text1.append(systemAdministratorName);
		emailService.sendNotificaitionAsync(answerComplaintRequestDTO.getClientEmail(), "Accepted ship complaint request", text1.toString());
		
		StringBuilder text2 = new StringBuilder();
		text2.append("We have accepted the complaint against your ship.");
		text2.append("\nComplaint: \"");
		text2.append(complaintComment);
		text2.append("\"");
		text2.append("\nAnswer: ");
		text2.append(answerComplaintRequestDTO.getComment());
		text2.append("\n");
		text2.append(systemAdministratorName);
		emailService.sendNotificaitionAsync(answerComplaintRequestDTO.getUserEmail(), "Ship complaint", text2.toString());
	}
	
	private void sendAcceptedUserComplaintEmails(AnswerComplaintRequestDTO answerComplaintRequestDTO, String complaintComment, String systemAdministratorName) {
		StringBuilder text1 = new StringBuilder();
		text1.append("We have accepted your complaint request.");
		text1.append("\nYour complaint: \"");
		text1.append(complaintComment);
		text1.append("\"");
		text1.append("\nAnswer: ");
		text1.append(answerComplaintRequestDTO.getComment());
		text1.append("\n");
		text1.append(systemAdministratorName);
		emailService.sendNotificaitionAsync(answerComplaintRequestDTO.getClientEmail(), "Accepted complaint request", text1.toString());
		
		StringBuilder text2 = new StringBuilder();
		text2.append("We have accepted the complaint against you.");
		text2.append("\nComplaint: \"");
		text2.append(complaintComment);
		text2.append("\"");
		text2.append("\nAnswer: ");
		text2.append(answerComplaintRequestDTO.getComment());
		text2.append("\n");
		text2.append(systemAdministratorName);
		emailService.sendNotificaitionAsync(answerComplaintRequestDTO.getUserEmail(), "Complaint", text2.toString());
		
	}
	
	private void sendDeclinedCottageComplaintEmails(AnswerComplaintRequestDTO answerComplaintRequestDTO, String complaintComment, String systemAdministratorName) {
		StringBuilder text1 = new StringBuilder();
		text1.append("We have declined your cottage complaint request.");
		text1.append("\nYour complaint: \"");
		text1.append(complaintComment);
		text1.append("\"");
		text1.append("\nAnswer: ");
		text1.append(answerComplaintRequestDTO.getComment());
		text1.append("\n");
		text1.append(systemAdministratorName);
		emailService.sendNotificaitionAsync(answerComplaintRequestDTO.getClientEmail(), "Declined cottage complaint request", text1.toString());
		
		StringBuilder text2 = new StringBuilder();
		text2.append("We have declined the complaint against your cottage.");
		text2.append("\nComplaint: \"");
		text2.append(complaintComment);
		text2.append("\"");
		text2.append("\nAnswer: ");
		text2.append(answerComplaintRequestDTO.getComment());
		text2.append("\n");
		text2.append(systemAdministratorName);
		emailService.sendNotificaitionAsync(answerComplaintRequestDTO.getUserEmail(), "Cottage complaint", text2.toString());
	}
	
	private void sendDeclinedShipComplaintEmails(AnswerComplaintRequestDTO answerComplaintRequestDTO, String complaintComment, String systemAdministratorName) {
		StringBuilder text1 = new StringBuilder();
		text1.append("We have declined your ship complaint request.");
		text1.append("\nYour complaint: \"");
		text1.append(complaintComment);
		text1.append("\"");
		text1.append("\nAnswer: ");
		text1.append(answerComplaintRequestDTO.getComment());
		text1.append("\n");
		text1.append(systemAdministratorName);
		emailService.sendNotificaitionAsync(answerComplaintRequestDTO.getClientEmail(), "Declined ship complaint request", text1.toString());
		
		StringBuilder text2 = new StringBuilder();
		text2.append("We have declined the complaint against your ship.");
		text2.append("\nComplaint: \"");
		text2.append(complaintComment);
		text2.append("\"");
		text2.append("\nAnswer: ");
		text2.append(answerComplaintRequestDTO.getComment());
		text2.append("\n");
		text2.append(systemAdministratorName);
		emailService.sendNotificaitionAsync(answerComplaintRequestDTO.getUserEmail(), "Ship complaint", text2.toString());
	}
	
	private void sendDeclinedUserComplaintEmails(AnswerComplaintRequestDTO answerComplaintRequestDTO, String complaintComment, String systemAdministratorName) {
		StringBuilder text1 = new StringBuilder();
		text1.append("We have declined your complaint request.");
		text1.append("\nYour complaint: \"");
		text1.append(complaintComment);
		text1.append("\"");
		text1.append("\nAnswer: ");
		text1.append(answerComplaintRequestDTO.getComment());
		text1.append("\n");
		text1.append(systemAdministratorName);
		emailService.sendNotificaitionAsync(answerComplaintRequestDTO.getClientEmail(), "Declined complaint request", text1.toString());
		
		StringBuilder text2 = new StringBuilder();
		text2.append("We have declined the complaint against you.");
		text2.append("\nComplaint: \"");
		text2.append(complaintComment);
		text2.append("\"");
		text2.append("\nAnswer: ");
		text2.append(answerComplaintRequestDTO.getComment());
		text2.append("\n");
		text2.append(systemAdministratorName);
		emailService.sendNotificaitionAsync(answerComplaintRequestDTO.getUserEmail(), "Complaint", text2.toString());
	}
}
