package Team76.InternetSoftwareArchitecture.iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.AnswerComplaintRequestDTO;
import Team76.InternetSoftwareArchitecture.dto.ComplaintRequestDTO;
import Team76.InternetSoftwareArchitecture.dto.ObjectComplaintDTO;
import Team76.InternetSoftwareArchitecture.dto.UserComplaintDTO;
import Team76.InternetSoftwareArchitecture.model.Complaint;

@Service
public interface IComplaintService {
	
	Complaint writeCottageComplaint(ComplaintRequestDTO complaintRequestDTO);
	Complaint writeShipComplaint(ComplaintRequestDTO complaintRequestDTO);
	Complaint writeUserComplaint(ComplaintRequestDTO complaintRequestDTO);
	List<ObjectComplaintDTO> getCottageComplaints();
	List<ObjectComplaintDTO> getShipComplaints();
	List<UserComplaintDTO> getUserComplaints();
	Boolean accpetCottageComplaintRequest(AnswerComplaintRequestDTO answerComplaintRequestDTO);
	Boolean declineCottageComplaintRequest(AnswerComplaintRequestDTO answerComplaintRequestDTO);
	Boolean acceptShipComplaintRequest(AnswerComplaintRequestDTO answerComplaintRequestDTO);
	Boolean declineShipComplaintRequest(AnswerComplaintRequestDTO answerComplaintRequestDTO);
	Boolean acceptUserComplaintRequest(AnswerComplaintRequestDTO answerComplaintRequestDTO);
	Boolean declineUserComplaintRequest(AnswerComplaintRequestDTO answerComplaintRequestDTO);
}
