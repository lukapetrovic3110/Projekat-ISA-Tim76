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

import Team76.InternetSoftwareArchitecture.dto.AdventureFastReservationDTO;
import Team76.InternetSoftwareArchitecture.dto.CreateReservationRequestDTO;
import Team76.InternetSoftwareArchitecture.dto.CreateReservationResponseDTO;
import Team76.InternetSoftwareArchitecture.dto.HistoryReservationAdventureDTO;
import Team76.InternetSoftwareArchitecture.iservice.IReservationAdventureService;
import Team76.InternetSoftwareArchitecture.model.Address;
import Team76.InternetSoftwareArchitecture.model.Adventure;
import Team76.InternetSoftwareArchitecture.model.AdventureAdditionalService;
import Team76.InternetSoftwareArchitecture.model.Client;
import Team76.InternetSoftwareArchitecture.model.FishingEquipmentForAdventure;
import Team76.InternetSoftwareArchitecture.model.ReservationAdventure;
import Team76.InternetSoftwareArchitecture.model.ReservationStatus;
import Team76.InternetSoftwareArchitecture.repository.IAdventureRepository;
import Team76.InternetSoftwareArchitecture.repository.IClientRepository;
import Team76.InternetSoftwareArchitecture.repository.IReservationAdventureRepository;

@Service
public class ReservationAdventureService implements IReservationAdventureService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private IReservationAdventureRepository reservationAdventureRepository;
	private IClientRepository clientRepository;
	private EmailService emailService;
	private IAdventureRepository adventureRepository;
	
	@Autowired
	public ReservationAdventureService(IReservationAdventureRepository reservationAdventureRepository,
			IClientRepository clientRepository, EmailService emailService, IAdventureRepository adventureRepository) {
		super();
		this.reservationAdventureRepository = reservationAdventureRepository;
		this.clientRepository = clientRepository;
		this.emailService = emailService;
		this.adventureRepository = adventureRepository;
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
	public List<AdventureFastReservationDTO> findAllFastReservationsForAdventure(Long adventureId) {
		List<ReservationAdventure> adventureFastReservations = reservationAdventureRepository.findAllFastReservationsForAdventure(adventureId);
		return adventureFastReservations.stream().map(adventureFastReservation -> new AdventureFastReservationDTO(
				adventureFastReservation.getReservationAdventureId(),
				adventureFastReservation.getDateAndTime(),
				adventureFastReservation.getDuration(),
				adventureFastReservation.getMaxNumberOfPersons(),
				adventureFastReservation.getAdventureAdditionalService(),
				adventureFastReservation.getFishingEquipmentForAdventure(),
				adventureFastReservation.getPrice(),
				adventureFastReservation.getDiscountPercentage())).collect(Collectors.toList());
	}
	
	@Override
	public Boolean scheduleFastReservation(Long fastReservationId) {
		Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ReservationAdventure reservationAdventure = reservationAdventureRepository.findByReservationAdventureId(fastReservationId);
		reservationAdventure.setClient(clientRepository.findByUserId(client.getUserId()));
		reservationAdventure.setReservationStatus(ReservationStatus.SCHEDULED);
		reservationAdventureRepository.save(reservationAdventure);
		Adventure adventure = reservationAdventure.getAdventure();
		try {
			sendReservationEmail(client.getEmail(), "Successfully scheduled fast reservation for adventure", createMessage(reservationAdventure, adventure));
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	private String createMessage(ReservationAdventure reservationAdventure, Adventure adventure) {
		StringBuilder textMessage = new StringBuilder();
		textMessage.append("Congrats, you have scheduled adventure - ");
		textMessage.append(adventure.getName());
		textMessage.append(" from ");
		SimpleDateFormat sdf = new SimpleDateFormat("dd.M.yyyy. HH:mm:ss");
		Date startDateAndTime = reservationAdventure.getDateAndTime();
		Calendar startReservationDate = Calendar.getInstance();
		startReservationDate.setTime(startDateAndTime);
		textMessage.append(sdf.format(startReservationDate.getTime()));
		textMessage.append(" to ");
		Calendar endReservationDate = Calendar.getInstance();
		endReservationDate.setTime(startDateAndTime);
		endReservationDate.add(Calendar.HOUR_OF_DAY, reservationAdventure.getDuration());
		textMessage.append(sdf.format(endReservationDate.getTime()));
		textMessage.append(".");
		if (reservationAdventure.getAdventureAdditionalService().size() > 0) {
			textMessage.append("\nThe reservation include additional service");
			for (AdventureAdditionalService a : reservationAdventure.getAdventureAdditionalService()) {
				textMessage.append("\n-");
				textMessage.append(a.getAdventureAdditionalServiceType().toString().replace("_", " "));
			}
		}
		if (reservationAdventure.getFishingEquipmentForAdventure().size() > 0) {
			textMessage.append("\nThe reservation include fishing equipment for adventure");
			for (FishingEquipmentForAdventure f : reservationAdventure.getFishingEquipmentForAdventure()) {
				textMessage.append("\n-");
				textMessage.append(f.getFishingEquipmentForAdventureType().toString().replace("_", " "));
			}
		}
		if (reservationAdventure.getDiscountPercentage() !=  null) {
			textMessage.append("\nYou have received a discount ");
			textMessage.append(reservationAdventure.getDiscountPercentage());
			textMessage.append("%, so your bill is now ");
			Double newPrice = reservationAdventure.getPrice() * (1 - reservationAdventure.getDiscountPercentage()/100.);
			textMessage.append(newPrice.toString());
			textMessage.append(" instead of ");
			textMessage.append(reservationAdventure.getPrice().toString() + " RSD.");
		} else {
			textMessage.append("\nYour bill is ");
			textMessage.append(reservationAdventure.getPrice().toString() + " RSD.");
		}
		textMessage.append("\nContact the fishing instructor for details, phone number: ");
		textMessage.append(adventure.getFishingInstructor().getPhoneNumber());
		textMessage.append(", email: ");
		textMessage.append(adventure.getFishingInstructor().getEmail() + ".");
		textMessage.append("\nBest regards and see you!");
		return textMessage.toString();
	}
	
	private void sendReservationEmail(String clientEmail, String caption, String text) {
		emailService.sendNotificationAsync(clientEmail, caption, text);
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

	@Override
	public CreateReservationResponseDTO createReservation(CreateReservationRequestDTO createReservationRequestDTO) {
		Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ReservationAdventure reservationAdventure = new ReservationAdventure();
		Adventure adventure = adventureRepository.findByAdventureId(createReservationRequestDTO.getId());
		reservationAdventure.setAdventure(adventure);
		reservationAdventure.setClient(clientRepository.findByUserId(client.getUserId()));
		reservationAdventure.setReservationStatus(ReservationStatus.SCHEDULED);
		reservationAdventure.setDateAndTime(createReservationRequestDTO.getReservationDateAndTime());
		reservationAdventure.setDuration(createReservationRequestDTO.getDuration());
		reservationAdventure.setMaxNumberOfPersons(createReservationRequestDTO.getNumberOfGuests());
		Double reseravationAdventurePrice = adventure.getPricePerHour() * createReservationRequestDTO.getDuration();
		reservationAdventure.setPrice(reseravationAdventurePrice);
		reservationAdventureRepository.save(reservationAdventure);
		StringBuilder answer = new StringBuilder();
		answer.append("The reservation was successfully created and scheduled!");
		CreateReservationResponseDTO createReservationResponseDTO = new CreateReservationResponseDTO(true, answer.toString());
		try {
			sendReservationEmail(client.getEmail(), "Successfully created and scheduled reservation for adventure", createMessage(reservationAdventure, reservationAdventure.getAdventure()));
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return createReservationResponseDTO;
	}
	
	
	@Scheduled(cron = "1 * * * * *")
	public void checkIfReservationsFinishedOrStarted() {
		logger.info("I'm checking to see if any adventure reservations have been finished or started in the meantime.");
		List<ReservationAdventure> allScheduledReservation = reservationAdventureRepository.findByReservationStatus(ReservationStatus.SCHEDULED);
		Date currentDate = new Date(System.currentTimeMillis());
		for (ReservationAdventure reservationAdventure : allScheduledReservation) {
			Date startReservationDate = reservationAdventure.getDateAndTime();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startReservationDate);
			calendar.add(Calendar.HOUR_OF_DAY, reservationAdventure.getDuration());
			Date endReservationDate = calendar.getTime(); 
			
			if(startReservationDate.getTime() <= currentDate.getTime() && currentDate.getTime() <= endReservationDate.getTime()) {
				reservationAdventure.setReservationStatus(ReservationStatus.STARTED);
				reservationAdventureRepository.save(reservationAdventure);
			}
			
			if(currentDate.after(endReservationDate)) {
				reservationAdventure.setReservationStatus(ReservationStatus.FINISHED);
				reservationAdventureRepository.save(reservationAdventure);
			}
		}
		
		List<ReservationAdventure> allWaitingReservation = reservationAdventureRepository.findByReservationStatus(ReservationStatus.WAITING);
		for (ReservationAdventure reservationAdventure : allWaitingReservation) {
			Date waitingReservationDate = reservationAdventure.getDateAndTime();
			if(currentDate.after(waitingReservationDate)) {
				reservationAdventure.setReservationStatus(ReservationStatus.FINISHED);
				reservationAdventureRepository.save(reservationAdventure);
			}
		}
		
	}

}
