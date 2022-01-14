package Team76.InternetSoftwareArchitecture.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.HistoryReservationCottageDTO;
import Team76.InternetSoftwareArchitecture.iservice.IReservationCottageService;
import Team76.InternetSoftwareArchitecture.model.Address;
import Team76.InternetSoftwareArchitecture.model.Client;
import Team76.InternetSoftwareArchitecture.model.ReservationCottage;
import Team76.InternetSoftwareArchitecture.model.ReservationStatus;
import Team76.InternetSoftwareArchitecture.repository.IReservationCottageRepository;

@Service
public class ReservationCottageService implements IReservationCottageService {
	
	private IReservationCottageRepository reservationCottageRepository;
	
	@Autowired
	public ReservationCottageService(IReservationCottageRepository reservationCottageRepository) {
		this.reservationCottageRepository = reservationCottageRepository;
	}


	@Override
	public List<HistoryReservationCottageDTO> findAllHistoryReservationByClient() {
		Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<ReservationCottage> allFinishedReservation = reservationCottageRepository.findByReservationStatus(ReservationStatus.FINISHED);
		List<HistoryReservationCottageDTO> historyReservationDTOs = new ArrayList<HistoryReservationCottageDTO>();
		
		for (ReservationCottage reservationCottage : allFinishedReservation)
			if (reservationCottage.getClient().getUserId() == client.getUserId())
				historyReservationDTOs.add(new HistoryReservationCottageDTO(reservationCottage.getCottage().getCottageId(), reservationCottage.getDateAndTime().toString(), reservationCottage.getDuration(), reservationCottage.getPrice(), reservationCottage.getMaxNumberOfPersons(), reservationCottage.getCottage().getName(), reservationCottage.getCottage().getNumberOfRooms(), reservationCottage.getCottage().getNumberOfBedsPerRoom(), Address.mapAddress(reservationCottage.getCottage().getAddress()), reservationCottage.getCottage().getCottageOwner().getUserId(), reservationCottage.getCottage().getCottageOwner().getFirstName(), reservationCottage.getCottage().getCottageOwner().getLastName(),reservationCottage.getCottage().getCottageOwner().getEmail(), reservationCottage.getCottage().getCottageOwner().getPhoneNumber(), Address.mapAddress(reservationCottage.getCottage().getCottageOwner().getAddress())));
		
		return historyReservationDTOs;
	}

	@Override
	public List<ReservationCottage> findAllScheduledReservationByClient() {
		Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<ReservationCottage> allScheduledReservation = reservationCottageRepository.findByReservationStatus(ReservationStatus.SCHEDULED);
		List<ReservationCottage> clientScheduledReservations = new ArrayList<ReservationCottage>();
		
		for (ReservationCottage reservationCottage : allScheduledReservation)
			if (reservationCottage.getClient().getUserId() == client.getUserId())
				clientScheduledReservations.add(reservationCottage);
			
		return clientScheduledReservations;
	}

	@Override
	public Boolean cancelReservation(Long reservationId) {
		ReservationCottage reservationShip = reservationCottageRepository.findByReservationCottageId(reservationId);
		Date startDate = reservationShip.getDateAndTime();
		Date currentDate = new Date(System.currentTimeMillis());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		calendar.add(Calendar.DAY_OF_MONTH, 3);

		if (startDate.before(calendar.getTime()))
			return false;
		
		reservationShip.setReservationStatus(ReservationStatus.CANCELLED);
		reservationCottageRepository.save(reservationShip);
		return true;
	}

}
