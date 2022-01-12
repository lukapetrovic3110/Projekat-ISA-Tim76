package Team76.InternetSoftwareArchitecture.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.CottageReservationClientInformationDTO;
import Team76.InternetSoftwareArchitecture.dto.CottageReservationInformationDTO;
import Team76.InternetSoftwareArchitecture.dto.HistoryReservationCottageDTO;
import Team76.InternetSoftwareArchitecture.iservice.IReservationCottageService;
import Team76.InternetSoftwareArchitecture.model.Client;
import Team76.InternetSoftwareArchitecture.model.ReservationCottage;
import Team76.InternetSoftwareArchitecture.model.ReservationStatus;
import Team76.InternetSoftwareArchitecture.repository.ICottageRepository;
import Team76.InternetSoftwareArchitecture.repository.IReservationCottageRepository;

@Service
public class ReservationCottageService implements IReservationCottageService {
	
	private IReservationCottageRepository reservationCottageRepository;
	
	private ICottageRepository cottageRepository;
	
	
	@Autowired
	public ReservationCottageService(IReservationCottageRepository reservationCottageRepository, ICottageRepository cottageRepository) {
		this.reservationCottageRepository = reservationCottageRepository;
		this.cottageRepository = cottageRepository;
	}


	@Override
	public List<HistoryReservationCottageDTO> findAllHistoryReservationByClient() {
		Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<ReservationCottage> allFinishedReservation = reservationCottageRepository.findByReservationStatus(ReservationStatus.FINISHED);
		List<HistoryReservationCottageDTO> historyReservationDTOs = new ArrayList<HistoryReservationCottageDTO>();
		
		for (ReservationCottage reservationCottage : allFinishedReservation)
			if (reservationCottage.getClient().getUserId() == client.getUserId())
				historyReservationDTOs.add(new HistoryReservationCottageDTO(reservationCottage.getDateAndTime().toString(), reservationCottage.getDuration(), reservationCottage.getPrice(), reservationCottage.getMaxNumberOfPersons(), reservationCottage.getCottage().getName(), reservationCottage.getCottage().getNumberOfRooms(), reservationCottage.getCottage().getNumberOfBedsPerRoom()));
		
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


	@Override
	public List<CottageReservationInformationDTO> findAllReservationsForCottageOwner(Long cottageOwnerId) {
		List<CottageReservationInformationDTO> reservationsForCottageOwnerDTO = new ArrayList<CottageReservationInformationDTO>();
		List<ReservationCottage> reservationsForCottageOwner = new ArrayList<ReservationCottage>();
		List<Long> cottageOwnerCottagesId = cottageRepository.getAllCottagesIdForCottageOwner(cottageOwnerId);
		List<ReservationCottage> allCottageReservations = reservationCottageRepository.findAll();
		
		for (ReservationCottage reservationCottage : allCottageReservations) {
			if (cottageOwnerCottagesId.contains(reservationCottage.getReservationCottageId())) {
				reservationsForCottageOwner.add(reservationCottage);
			}
		}
		
		for (ReservationCottage reservationCottage : reservationsForCottageOwner) {
			CottageReservationClientInformationDTO cottageReservationClientInformationDTO = new CottageReservationClientInformationDTO(reservationCottage.getClient().getFirstName(), reservationCottage.getClient().getLastName(), reservationCottage.getClient().getEmail(), reservationCottage.getClient().getPhoneNumber(), reservationCottage.getClient().getAddress());
			CottageReservationInformationDTO cottageReservationInformationDTO = new CottageReservationInformationDTO(reservationCottage.getDateAndTime(), reservationCottage.getDuration(), reservationCottage.getMaxNumberOfPersons(), reservationCottage.getPrice(), reservationCottage.getCottage().getName(), reservationCottage.getCottage().getNumberOfRooms(), reservationCottage.getCottage().getNumberOfBedsPerRoom(), cottageReservationClientInformationDTO, reservationCottage.getReservationStatus());
			
			reservationsForCottageOwnerDTO.add(cottageReservationInformationDTO);
		}
		
		return reservationsForCottageOwnerDTO;
	}

}
