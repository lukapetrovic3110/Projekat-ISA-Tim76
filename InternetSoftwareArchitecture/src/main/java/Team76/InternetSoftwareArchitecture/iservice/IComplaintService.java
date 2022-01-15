package Team76.InternetSoftwareArchitecture.iservice;

import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.ComplaintRequestDTO;
import Team76.InternetSoftwareArchitecture.model.Complaint;

@Service
public interface IComplaintService {
	
	Complaint writeCottageComplaint(ComplaintRequestDTO complaintRequestDTO);
	Complaint writeCottageOwnerComplaint(ComplaintRequestDTO complaintRequestDTO);
	Complaint writeShipOwnerComplaint(ComplaintRequestDTO complaintRequestDTO);
	Complaint writeShipComplaint(ComplaintRequestDTO complaintRequestDTO);
}
