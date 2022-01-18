package Team76.InternetSoftwareArchitecture.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.HistoryReservationAdventureDTO;
import Team76.InternetSoftwareArchitecture.iservice.IReservationAdventureService;
import Team76.InternetSoftwareArchitecture.model.Address;
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
			if (reservationAdventure.getClient().getUserId() == client.getUserId())
				historyReservationDTOs.add(new HistoryReservationAdventureDTO(reservationAdventure.getAdventure().getAdventureId(), reservationAdventure.getDateAndTime().toString(), reservationAdventure.getDuration(), reservationAdventure.getPrice(), reservationAdventure.getMaxNumberOfPersons(), reservationAdventure.getAdventure().getName(), reservationAdventure.getAdventure().getDescription(), reservationAdventure.getAdventure().getMaxNumberOfPersons(), Address.mapAddress(reservationAdventure.getAdventure().getAddress()), reservationAdventure.getAdventure().getFishingInstructor().getUserId(), reservationAdventure.getAdventure().getFishingInstructor().getFirstName(), reservationAdventure.getAdventure().getFishingInstructor().getLastName(), reservationAdventure.getAdventure().getFishingInstructor().getEmail(), reservationAdventure.getAdventure().getFishingInstructor().getPhoneNumber(), reservationAdventure.getAdventure().getInstructorBiography(), Address.mapAddress(reservationAdventure.getAdventure().getFishingInstructor().getAddress())));
		
		return historyReservationDTOs;
	}
	
	@Override
	public List<ReservationAdventure> findAllScheduledReservationByClient() {
		Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<ReservationAdventure> allScheduledReservation = reservationAdventureRepository.findByReservationStatus(ReservationStatus.SCHEDULED);
		List<ReservationAdventure> clientScheduledReservations = new ArrayList<ReservationAdventure>();
		
		for (ReservationAdventure reservationAdventure : allScheduledReservation)
			if (reservationAdventure.getClient().getUserId() == client.getUserId())
				clientScheduledReservations.add(reservationAdventure);
			
		return clientScheduledReservations;
	}


	@Override
	public Boolean cancelReservation(Long reservationId) {
		ReservationAdventure reservationAdventure = reservationAdventureRepository.findByReservationAdventureId(reservationId);
		Date startDate = reservationAdventure.getDateAndTime();
		Date currentDate = new Date(System.currentTimeMillis());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		calendar.add(Calendar.DAY_OF_MONTH, 3);

		if (startDate.before(calendar.getTime()))
			return false;
		
		reservationAdventure.setReservationStatus(ReservationStatus.CANCELLED);
		reservationAdventureRepository.save(reservationAdventure);
		return true;
	}

}
