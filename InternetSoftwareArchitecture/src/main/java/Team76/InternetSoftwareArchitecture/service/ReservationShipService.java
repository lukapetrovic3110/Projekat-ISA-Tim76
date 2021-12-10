package Team76.InternetSoftwareArchitecture.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.HistoryReservationShipDTO;
import Team76.InternetSoftwareArchitecture.iservice.IReservationShipService;
import Team76.InternetSoftwareArchitecture.model.Client;
import Team76.InternetSoftwareArchitecture.model.ReservationShip;
import Team76.InternetSoftwareArchitecture.model.ReservationStatus;
import Team76.InternetSoftwareArchitecture.repository.IReservationShipRepository;

@Service
public class ReservationShipService implements IReservationShipService {
	
	private IReservationShipRepository reservationShipRepository;
	
	@Autowired
	public ReservationShipService(IReservationShipRepository reservationShipRepository) {
		this.reservationShipRepository = reservationShipRepository;
	}
	
	@Override
	public List<HistoryReservationShipDTO> findAllHistoryReservationByClient() {
		Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();	
		List<ReservationShip> allFinishedReservation = reservationShipRepository.findByReservationStatus(ReservationStatus.FINISHED);
		List<HistoryReservationShipDTO> historyReservationDTOs = new ArrayList<HistoryReservationShipDTO>();
		
		for (ReservationShip reservationShip : allFinishedReservation) 
			if (reservationShip.getClient().getUserId() == client.getUserId())
				historyReservationDTOs.add(new HistoryReservationShipDTO(reservationShip.getDateAndTime().toString(),reservationShip.getDuration(), reservationShip.getPrice(), reservationShip.getMaxNumberOfPersons(), reservationShip.getShip().getName(), reservationShip.getShip().getCapacity()));
			
	
		return historyReservationDTOs; 
	}
	

}
