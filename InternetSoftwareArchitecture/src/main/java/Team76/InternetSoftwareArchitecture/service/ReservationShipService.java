package Team76.InternetSoftwareArchitecture.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.HistoryReservationShipDTO;
import Team76.InternetSoftwareArchitecture.dto.ShipFastReservationDTO;
import Team76.InternetSoftwareArchitecture.dto.ShipReservationCalendarDTO;
import Team76.InternetSoftwareArchitecture.dto.ShipReservationCalendarInformationDTO;
import Team76.InternetSoftwareArchitecture.iservice.IReservationShipService;
import Team76.InternetSoftwareArchitecture.model.Address;
import Team76.InternetSoftwareArchitecture.model.Client;
import Team76.InternetSoftwareArchitecture.model.FishingEquipmentForShip;
import Team76.InternetSoftwareArchitecture.model.ReservationShip;
import Team76.InternetSoftwareArchitecture.model.ReservationStatus;
import Team76.InternetSoftwareArchitecture.model.Ship;
import Team76.InternetSoftwareArchitecture.model.ShipAdditionalService;
import Team76.InternetSoftwareArchitecture.repository.IClientRepository;
import Team76.InternetSoftwareArchitecture.repository.IReservationShipRepository;
import Team76.InternetSoftwareArchitecture.repository.IShipRepository;

@Service
public class ReservationShipService implements IReservationShipService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private IReservationShipRepository reservationShipRepository;
	private IClientRepository clientRepository;
	private IShipRepository shipRepository;
	private EmailService emailService;
	
	@Autowired
	public ReservationShipService(IReservationShipRepository reservationShipRepository, IClientRepository clientRepository, EmailService emailService, IShipRepository shipRepository) {
		super();
		this.reservationShipRepository = reservationShipRepository;
		this.clientRepository = clientRepository;
		this.emailService = emailService;
		this.shipRepository = shipRepository;
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
	public List<ShipFastReservationDTO> findAllFastReservationsForShip(Long shipId) {
		List<ReservationShip> shipFastReservations = reservationShipRepository.findAllFastReservationsForShip(shipId);
		return shipFastReservations.stream().map(shipFastReservation -> new ShipFastReservationDTO(
				shipFastReservation.getReservationShipId(),
				shipFastReservation.getDateAndTime(),
				shipFastReservation.getDuration(),
				shipFastReservation.getMaxNumberOfPersons(),
				shipFastReservation.getShipAdditionalServices(),
				shipFastReservation.getFishingEquipmentForShip(),
				shipFastReservation.getPrice(),
				shipFastReservation.getDiscountPercentage())).collect(Collectors.toList());
	}
	
	@Override
	public Boolean scheduleFastReservation(Long fastReservationId) {
		Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ReservationShip reservationShip = reservationShipRepository.findByReservationShipId(fastReservationId);
		reservationShip.setClient(clientRepository.findByUserId(client.getUserId()));
		reservationShip.setReservationStatus(ReservationStatus.SCHEDULED);
		reservationShipRepository.save(reservationShip);
		Ship ship = reservationShip.getShip();
		try {
			sendFastReservationEmail(client.getEmail(), createMessage(reservationShip, ship));
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	private String createMessage(ReservationShip reservationShip, Ship ship) {
		StringBuilder textMessage = new StringBuilder();
		textMessage.append("You have a scheduled ship - ");
		textMessage.append(ship.getName());
		textMessage.append(" from ");
		SimpleDateFormat sdf = new SimpleDateFormat("dd.M.yyyy. HH:mm:ss");
		Date startDateAndTime = reservationShip.getDateAndTime();
		Calendar startReservationDate = Calendar.getInstance();
		startReservationDate.setTime(startDateAndTime);
		textMessage.append(sdf.format(startReservationDate.getTime()));
		textMessage.append(" to ");
		Calendar endReservationDate = Calendar.getInstance();
		endReservationDate.setTime(startDateAndTime);
		endReservationDate.add(Calendar.HOUR_OF_DAY, reservationShip.getDuration());
		textMessage.append(sdf.format(endReservationDate.getTime()));
		textMessage.append(".");
		if (reservationShip.getShipAdditionalServices().size() > 0) {
			textMessage.append("\nThe reservation include additional service");
			for (ShipAdditionalService s : reservationShip.getShipAdditionalServices()) {
				textMessage.append("\n-");
				textMessage.append(s.getShipAdditionalServiceType().toString().replace("_", " "));
			}
		}
		if (reservationShip.getFishingEquipmentForShip().size() > 0) {
			textMessage.append("\nThe reservation include fishing equipment");
			for (FishingEquipmentForShip f : reservationShip.getFishingEquipmentForShip()) {
				textMessage.append("\n-");
				textMessage.append(f.getFishingEquipmentForShipType().toString().replace("_", " "));
			}
		}
		if (reservationShip.getDiscountPercentage() !=  null) {
			textMessage.append("\nYou have received a discount ");
			textMessage.append(reservationShip.getDiscountPercentage());
			textMessage.append("%, so your bill is now ");
			Double newPrice = reservationShip.getPrice() * (1 - reservationShip.getDiscountPercentage()/100.);
			textMessage.append(newPrice.toString());
			textMessage.append(" instead of ");
			textMessage.append(reservationShip.getPrice().toString() + " RSD.");
		} else {
			textMessage.append("\nYour bill is ");
			textMessage.append(reservationShip.getPrice().toString() + " RSD.");
		}
		textMessage.append("\nContact the owner for details, phone number: ");
		textMessage.append(ship.getShipOwner().getPhoneNumber());
		textMessage.append(", email: ");
		textMessage.append(ship.getShipOwner().getEmail() + ".");
		textMessage.append("\nBest regards and see you!");
		return textMessage.toString();
	}
	
	private void sendFastReservationEmail(String clientEmail, String text) {
		emailService.sendNotificaitionAsync(clientEmail, "Successfully scheduled fast reservation for ship", text);
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
	public void checkIfReservationsFinishedOrStarted() {
		logger.info("I'm checking to see if any ship reservations have been finished or started in the meantime.");
		List<ReservationShip> allScheduledReservation = reservationShipRepository.findByReservationStatus(ReservationStatus.SCHEDULED);
		Date currentDate = new Date(System.currentTimeMillis());
		for (ReservationShip reservationShip : allScheduledReservation) {
			Date startReservationDate = reservationShip.getDateAndTime();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startReservationDate);
			calendar.add(Calendar.HOUR_OF_DAY, reservationShip.getDuration());
			Date endReservationDate = calendar.getTime(); 
			
			if(startReservationDate.getTime() <= currentDate.getTime() && currentDate.getTime() <= endReservationDate.getTime()) {
				reservationShip.setReservationStatus(ReservationStatus.STARTED);
				reservationShipRepository.save(reservationShip);
			}
			
			if(currentDate.after(endReservationDate)) {
				reservationShip.setReservationStatus(ReservationStatus.FINISHED);
				reservationShipRepository.save(reservationShip);
			}
		}
		
		List<ReservationShip> allWaitingReservation = reservationShipRepository.findByReservationStatus(ReservationStatus.WAITING);
		for (ReservationShip reservationShip : allWaitingReservation) {
			Date waitingReservationDate = reservationShip.getDateAndTime();
			if(currentDate.after(waitingReservationDate)) {
				reservationShip.setReservationStatus(ReservationStatus.FINISHED);
				reservationShipRepository.save(reservationShip);
			}
		}
	}

	@Override
	public ShipReservationCalendarDTO getAvailabilityCalendarInformation(Long shipId) {
		ShipReservationCalendarDTO shipReservationCalendarDTO = new ShipReservationCalendarDTO();
		shipReservationCalendarDTO.setShipReservations(new ArrayList<ShipReservationCalendarInformationDTO>());
		List<ReservationShip> shipReservations = reservationShipRepository.findAllReservationsForShip(shipId);
		Ship ship = shipRepository.getOne(shipId);
		shipReservationCalendarDTO.setAvailabilityStart(ship.getAvailabilityStart());
		shipReservationCalendarDTO.setAvailabilityEnd(ship.getAvailabilityEnd());
		
		for (ReservationShip reservationShip : shipReservations) {
			if (reservationShip.getReservationStatus().equals(ReservationStatus.STARTED) || reservationShip.getReservationStatus().equals(ReservationStatus.SCHEDULED) || reservationShip.getReservationStatus().equals(ReservationStatus.WAITING)) {
				ShipReservationCalendarInformationDTO shipReservationCalendarInformationDTO;
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(reservationShip.getDateAndTime());
				calendar.add(Calendar.DATE, reservationShip.getDuration());
				if (reservationShip.getClient() == null) {
					shipReservationCalendarInformationDTO = new ShipReservationCalendarInformationDTO(reservationShip.getDateAndTime(), calendar.getTime(), reservationShip.getShip().getName(), "", "", "", reservationShip.getReservationStatus());
				} else {
					shipReservationCalendarInformationDTO = new ShipReservationCalendarInformationDTO(reservationShip.getDateAndTime(), calendar.getTime(), reservationShip.getShip().getName(), reservationShip.getClient().getFirstName(), reservationShip.getClient().getLastName(), reservationShip.getClient().getEmail(), reservationShip.getReservationStatus());
				}
				
				shipReservationCalendarDTO.getShipReservations().add(shipReservationCalendarInformationDTO);
			}
		}
		
		return shipReservationCalendarDTO;
	}

}
