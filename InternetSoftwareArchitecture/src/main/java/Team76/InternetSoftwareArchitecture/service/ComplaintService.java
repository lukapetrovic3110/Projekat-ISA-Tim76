package Team76.InternetSoftwareArchitecture.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.ComplaintRequestDTO;
import Team76.InternetSoftwareArchitecture.iservice.IComplaintService;
import Team76.InternetSoftwareArchitecture.model.Client;
import Team76.InternetSoftwareArchitecture.model.Complaint;
import Team76.InternetSoftwareArchitecture.model.ComplaintStatus;
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
	
	@Autowired
	public ComplaintService(IComplaintRepository complaintRepository, ICottageRepository cottageRepository, IShipRepository shipRepository,
			IUserRepository userRepository) {
		this.complaintRepository = complaintRepository;
		this.shipRepository = shipRepository;
		this.cottageRepository = cottageRepository;
		this.userRepository = userRepository;
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
}
