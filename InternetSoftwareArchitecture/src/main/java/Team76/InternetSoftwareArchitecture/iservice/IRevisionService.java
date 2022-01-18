package Team76.InternetSoftwareArchitecture.iservice;

import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.RevisionRequestDTO;
import Team76.InternetSoftwareArchitecture.model.Revision;

@Service
public interface IRevisionService {

	Revision writeRevision(RevisionRequestDTO revisionRequestDTO);
}
