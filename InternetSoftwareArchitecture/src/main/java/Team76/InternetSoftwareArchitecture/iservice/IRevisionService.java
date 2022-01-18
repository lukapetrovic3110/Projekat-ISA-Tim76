package Team76.InternetSoftwareArchitecture.iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.AcceptRevisionRequestDTO;
import Team76.InternetSoftwareArchitecture.dto.DeclineRevisionRequestDTO;
import Team76.InternetSoftwareArchitecture.dto.ObjectRevisionDTO;
import Team76.InternetSoftwareArchitecture.dto.RevisionRequestDTO;
import Team76.InternetSoftwareArchitecture.dto.UserRevisionDTO;
import Team76.InternetSoftwareArchitecture.model.Revision;

@Service
public interface IRevisionService {

	Revision writeRevision(RevisionRequestDTO revisionRequestDTO);
	List<ObjectRevisionDTO> getCottageRevisions();
	List<ObjectRevisionDTO> getShipRevisions();
	List<ObjectRevisionDTO> getAdventureRevisions();
	List<UserRevisionDTO> getUserRevisions();
	Boolean acceptRevisionRequest(AcceptRevisionRequestDTO acceptRevisionRequestDTO);
	Boolean declineRevisionRequest(DeclineRevisionRequestDTO declineRevisionRequestDTO);
}
