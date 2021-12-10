package Team76.InternetSoftwareArchitecture.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.HistoryReservationAdventureDTO;
import Team76.InternetSoftwareArchitecture.iservice.IReservationAdventureService;
import Team76.InternetSoftwareArchitecture.model.Client;
import Team76.InternetSoftwareArchitecture.model.ReservationAdventure;
import Team76.InternetSoftwareArchitecture.model.ReservationStatus;
import Team76.InternetSoftwareArchitecture.repository.IReservationAdventureRepository;

@Service
public class ReservationAdventureService implements IReservationAdventureService {
	
	private IReservationAdventureRepository reservationAdventureRepository;
	
	@Autowired
	public ReservationAdventureService(IReservationAdventureRepository reservationAdventureRepository) {
		this.reservationAdventureRepository = reservationAdventureRepository;
	}



	@Override
	public List<HistoryReservationAdventureDTO> findAllHistoryReservationByClient() {
		Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<ReservationAdventure> allFinishedReservation = reservationAdventureRepository.findByReservationStatus(ReservationStatus.FINISHED);
		List<HistoryReservationAdventureDTO> historyReservationDTOs = new ArrayList<HistoryReservationAdventureDTO>();
		
		for (ReservationAdventure reservationAdventure : allFinishedReservation)
			if (reservationAdventure.getClient().getUserId() == client.getUserId()) {
				historyReservationDTOs.add(new HistoryReservationAdventureDTO(reservationAdventure.getDateAndTime().toString(), reservationAdventure.getDuration(), reservationAdventure.getPrice(), reservationAdventure.getMaxNumberOfPersons(), reservationAdventure.getAdventure().getName(), reservationAdventure.getAdventure().getFishingInstructor().getFirstName() + " " + reservationAdventure.getAdventure().getFishingInstructor().getLastName(), reservationAdventure.getAdventure().getDescription(), reservationAdventure.getAdventure().getMaxNumberOfPersons()));
			}
		
		return historyReservationDTOs;
	}

}
