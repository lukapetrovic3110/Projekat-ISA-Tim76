package Team76.InternetSoftwareArchitecture.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.HistoryReservationShipDTO;
import Team76.InternetSoftwareArchitecture.iservice.IReservationShipService;
import Team76.InternetSoftwareArchitecture.model.Address;
import Team76.InternetSoftwareArchitecture.model.Client;
import Team76.InternetSoftwareArchitecture.model.ReservationShip;
import Team76.InternetSoftwareArchitecture.model.ReservationStatus;
import Team76.InternetSoftwareArchitecture.repository.IReservationShipRepository;

@Service
public class ReservationShipService implements IReservationShipService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
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
				historyReservationDTOs.add(new HistoryReservationShipDTO(reservationShip.getShip().getShipId(), reservationShip.getDateAndTime().toString(), reservationShip.getDuration(), reservationShip.getPrice(), reservationShip.getMaxNumberOfPersons(), reservationShip.getShip().getName(), reservationShip.getShip().getCapacity(), Address.mapAddress(reservationShip.getShip().getAddress()), reservationShip.getShip().getShipOwner().getUserId(), reservationShip.getShip().getShipOwner().getFirstName(), reservationShip.getShip().getShipOwner().getLastName(), reservationShip.getShip().getShipOwner().getEmail(), reservationShip.getShip().getShipOwner().getPhoneNumber(), Address.mapAddress(reservationShip.getShip().getShipOwner().getAddress())));
			
	
		return historyReservationDTOs; 
	}
	
	@Override
	public List<ReservationShip> findAllScheduledReservationByClient() {
		Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<ReservationShip> allScheduledReservation = reservationShipRepository.findByReservationStatus(ReservationStatus.SCHEDULED);
		List<ReservationShip> clientScheduledReservations = new ArrayList<ReservationShip>();
		
		for (ReservationShip reservationShip : allScheduledReservation)
			if (reservationShip.getClient().getUserId() == client.getUserId())
				clientScheduledReservations.add(reservationShip);
			
		return clientScheduledReservations;
	}

	@Override
	public Boolean cancelReservation(Long reservationId) {
		ReservationShip reservationShip = reservationShipRepository.findByReservationShipId(reservationId);
		Date startDate = reservationShip.getDateAndTime();
		Date currentDate = new Date(System.currentTimeMillis());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		calendar.add(Calendar.DAY_OF_MONTH, 3);
		if (startDate.before(calendar.getTime()))
			return false;
		reservationShip.setReservationStatus(ReservationStatus.CANCELLED);
		reservationShipRepository.save(reservationShip);
		return true;
	}
	
	@Scheduled(cron = "1 * * * * *")
	public void checkIfReservationsFinished() {
		logger.info("I'm checking to see if any ship reservations have been finished in the meantime.");
		List<ReservationShip> allScheduledReservation = reservationShipRepository.findByReservationStatus(ReservationStatus.SCHEDULED);
		Date currentDate = new Date(System.currentTimeMillis());
		for (ReservationShip reservationShip : allScheduledReservation)
			if(currentDate.after(reservationShip.getDateAndTime())) {
				reservationShip.setReservationStatus(ReservationStatus.FINISHED);
				reservationShipRepository.save(reservationShip);
			}
	}
	
	/*
	@Scheduled(fixedRate = 60000) // test na svaki minut
	public void reportCurrentTime() {
		Date currentDate = new Date(System.currentTimeMillis());
		logger.info("The time is now {}", currentDate.toString());
	} */

}
