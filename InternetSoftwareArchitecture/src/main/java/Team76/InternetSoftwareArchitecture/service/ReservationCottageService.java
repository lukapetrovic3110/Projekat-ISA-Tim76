	package Team76.InternetSoftwareArchitecture.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.CottageFastReservationDTO;
import Team76.InternetSoftwareArchitecture.dto.CottageReservationCalendarDTO;
import Team76.InternetSoftwareArchitecture.dto.CottageReservationCalendarInformationDTO;
import Team76.InternetSoftwareArchitecture.dto.CottageReservationClientInformationDTO;
import Team76.InternetSoftwareArchitecture.dto.CottageReservationInformationDTO;
import Team76.InternetSoftwareArchitecture.dto.CottageReservationReportDTO;
import Team76.InternetSoftwareArchitecture.dto.DeleteCottageReservationDTO;
import Team76.InternetSoftwareArchitecture.dto.HistoryReservationCottageDTO;
import Team76.InternetSoftwareArchitecture.iservice.IReservationCottageService;
import Team76.InternetSoftwareArchitecture.model.Address;
import Team76.InternetSoftwareArchitecture.model.Client;
import Team76.InternetSoftwareArchitecture.model.Cottage;
import Team76.InternetSoftwareArchitecture.model.CottageAdditionalService;
import Team76.InternetSoftwareArchitecture.model.CottageAdditionalServiceType;
import Team76.InternetSoftwareArchitecture.model.CottageReservationReport;
import Team76.InternetSoftwareArchitecture.model.ReservationCottage;
import Team76.InternetSoftwareArchitecture.model.ReservationStatus;
import Team76.InternetSoftwareArchitecture.repository.IClientRepository;
import Team76.InternetSoftwareArchitecture.repository.ICottageRepository;
import Team76.InternetSoftwareArchitecture.repository.ICottageReservationReportRepository;
import Team76.InternetSoftwareArchitecture.repository.IReservationCottageRepository;

@Service
public class ReservationCottageService implements IReservationCottageService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private IReservationCottageRepository reservationCottageRepository;
	private ICottageRepository cottageRepository;
	private ICottageReservationReportRepository cottageReservationReportRepository;
	private IClientRepository clientRepository;
	private EmailService emailService;
	
	@Autowired
	public ReservationCottageService(IReservationCottageRepository reservationCottageRepository, ICottageRepository cottageRepository, ICottageReservationReportRepository cottageReservationReportRepository, IClientRepository clientRepository, EmailService emailService) {
		this.reservationCottageRepository = reservationCottageRepository;
		this.cottageRepository = cottageRepository;
		this.cottageReservationReportRepository = cottageReservationReportRepository;
		this.clientRepository = clientRepository;
		this.emailService = emailService;
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


	@Override
	public List<CottageReservationInformationDTO> findAllReservationsForCottageOwner(Long cottageOwnerId, ReservationStatus reservationStatus) {
		List<CottageReservationInformationDTO> reservationsForCottageOwnerDTO = new ArrayList<CottageReservationInformationDTO>();
		List<ReservationCottage> reservationsForCottageOwner = new ArrayList<ReservationCottage>();
		List<Long> cottageOwnerCottagesId = cottageRepository.getAllCottageIdForCottageOwner(cottageOwnerId);
		List<ReservationCottage> allCottageReservations = reservationCottageRepository.findAll();
		List<Long> cottageReservationIdFromCottageReservationReport = cottageReservationReportRepository.getAllCottageReservationIdFromCottageReservationReports();
		
		for (ReservationCottage reservationCottage : allCottageReservations) {
			if (reservationStatus == null) {
				if (cottageOwnerCottagesId.contains(reservationCottage.getCottage().getCottageId())) {
					reservationsForCottageOwner.add(reservationCottage);
				}
			} else if (reservationStatus.equals(ReservationStatus.FINISHED)) {
				if (reservationCottage.getReservationStatus().equals(reservationStatus) && !cottageReservationIdFromCottageReservationReport.contains(reservationCottage.getReservationCottageId()) && cottageOwnerCottagesId.contains(reservationCottage.getCottage().getCottageId())) {
					reservationsForCottageOwner.add(reservationCottage);
				}
			} else {
				if (reservationCottage.getReservationStatus().equals(reservationStatus) && cottageOwnerCottagesId.contains(reservationCottage.getCottage().getCottageId())) {
					cottageOwnerCottagesId.contains(reservationCottage.getCottage().getCottageId());
					reservationsForCottageOwner.add(reservationCottage);
				}
			}
		}
		
		for (ReservationCottage reservationCottage : reservationsForCottageOwner) {
			CottageReservationInformationDTO cottageReservationInformationDTO;
			if (reservationCottage.getClient() == null) {
				cottageReservationInformationDTO = new CottageReservationInformationDTO(reservationCottage.getReservationCottageId(), reservationCottage.getDateAndTime(), reservationCottage.getDuration(), reservationCottage.getMaxNumberOfPersons(), reservationCottage.getPrice(), reservationCottage.getCottage().getName(), reservationCottage.getCottage().getNumberOfRooms(), reservationCottage.getCottage().getNumberOfBedsPerRoom(), null, reservationCottage.getReservationStatus());
			} else {
				CottageReservationClientInformationDTO cottageReservationClientInformationDTO = new CottageReservationClientInformationDTO(reservationCottage.getClient().getFirstName(), reservationCottage.getClient().getLastName(), reservationCottage.getClient().getEmail(), reservationCottage.getClient().getPhoneNumber(), reservationCottage.getClient().getAddress());
				cottageReservationInformationDTO = new CottageReservationInformationDTO(reservationCottage.getReservationCottageId(), reservationCottage.getDateAndTime(), reservationCottage.getDuration(), reservationCottage.getMaxNumberOfPersons(), reservationCottage.getPrice(), reservationCottage.getCottage().getName(), reservationCottage.getCottage().getNumberOfRooms(), reservationCottage.getCottage().getNumberOfBedsPerRoom(), cottageReservationClientInformationDTO, reservationCottage.getReservationStatus());
			}
			
			reservationsForCottageOwnerDTO.add(cottageReservationInformationDTO);
		}
		
		return reservationsForCottageOwnerDTO;
	}


	@Override
	public CottageReservationReportDTO saveReport(CottageReservationReportDTO cottageReservationReportDTO) {
		Optional<ReservationCottage> reservationCottage = reservationCottageRepository.findById(cottageReservationReportDTO.getCottageReservationId());
		Boolean clientArrival = cottageReservationReportDTO.getClientArrival().equals("client-arrived") ? true : false;
		CottageReservationReport cottageReservationReport = new CottageReservationReport(cottageReservationReportDTO.getComment(), clientArrival, reservationCottage.get());
		
		CottageReservationReport report = cottageReservationReportRepository.save(cottageReservationReport);
		String clientArrivalFormatted = report.getClientArrived() ? "client-arrived" : "client-didnt-arrive";
		return new CottageReservationReportDTO(report.getComment(), clientArrivalFormatted, report.getReservationCottage().getClient().getEmail(), report.getReservationCottage().getReservationCottageId());
	}


	@Override
	public List<CottageFastReservationDTO> findAllFastReservationsForCottage(Long cottageId) {
		List<ReservationCottage> cottageFastReservations = reservationCottageRepository.findAllFastReservationsForCottage(cottageId);
		return cottageFastReservations.stream().map(cottageReservation -> new CottageFastReservationDTO(
						cottageReservation.getReservationCottageId(),
						cottageReservation.getDateAndTime(), 
						cottageReservation.getDuration(), 
						cottageReservation.getMaxNumberOfPersons(), 
						cottageReservation.getCottageAdditionalServices(), 
						cottageReservation.getPrice(),
						cottageReservation.getDiscountPercentage())).collect(Collectors.toList());
	}
	
	@Override
	public Boolean scheduleFastReservation(Long fastReservationId) {
		Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ReservationCottage reservationCottage = reservationCottageRepository.findByReservationCottageId(fastReservationId);
		reservationCottage.setClient(clientRepository.findByUserId(client.getUserId()));
		reservationCottage.setReservationStatus(ReservationStatus.SCHEDULED);
		reservationCottageRepository.save(reservationCottage);
		Cottage cottage = reservationCottage.getCottage();
		try {
			sendFastReservationEmail(client.getEmail(),createMessage(reservationCottage, cottage));		
			return true;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}


	private String createMessage(ReservationCottage reservationCottage, Cottage cottage) {
		StringBuilder textMessage = new StringBuilder();
		textMessage.append("You have a scheduled cottage - ");
		textMessage.append(cottage.getName());
		textMessage.append(" from ");
		SimpleDateFormat sdf = new SimpleDateFormat("dd.M.yyyy. HH:mm:ss");
		Date startDateAndTime = reservationCottage.getDateAndTime();
		Calendar startReservationDate = Calendar.getInstance();
		startReservationDate.setTime(startDateAndTime);
		textMessage.append(sdf.format(startReservationDate.getTime()));
		textMessage.append(" to ");
		Calendar endReservationDate = Calendar.getInstance();
		endReservationDate.setTime(startDateAndTime);
		endReservationDate.add(Calendar.DAY_OF_MONTH, reservationCottage.getDuration());
		textMessage.append(sdf.format(endReservationDate.getTime()));
		textMessage.append(".");
		if (reservationCottage.getCottageAdditionalServices().size() > 0) {
			textMessage.append("\nThe reservation include additional service");
			for (CottageAdditionalService s : reservationCottage.getCottageAdditionalServices()) {
				textMessage.append("\n-");
				textMessage.append(s.getCottageAdditionalServiceType().toString().replace("_", " "));
			}
		}
		if (reservationCottage.getDiscountPercentage() !=  null) {
			textMessage.append("\nYou have received a discount ");
			textMessage.append(reservationCottage.getDiscountPercentage());
			textMessage.append("%, so your bill is now ");
			Double newPrice = reservationCottage.getPrice() * (1 - reservationCottage.getDiscountPercentage()/100.);
			textMessage.append(newPrice.toString());
			textMessage.append(" instead of ");
			textMessage.append(reservationCottage.getPrice().toString() + " RSD.");
		} else {
			textMessage.append("\nYour bill is ");
			textMessage.append(reservationCottage.getPrice().toString() + " RSD.");
		}
		textMessage.append("\nContact the owner for details, phone number: ");
		textMessage.append(cottage.getCottageOwner().getPhoneNumber());
		textMessage.append(", email: ");
		textMessage.append(cottage.getCottageOwner().getEmail() + ".");
		textMessage.append("\nBest regards and see you!");
		return textMessage.toString();
	}
	
	private void sendFastReservationEmail(String clientEmail, String text) {
		emailService.sendNotificaitionAsync(clientEmail, "Successfully scheduled fast reservation for cottage", text);
	}


	@Override
	public CottageFastReservationDTO saveFastReservation(Long cottageId, CottageFastReservationDTO cottageFastReservationDTO) {
		Cottage cottage = cottageRepository.findByCottageId(cottageId);
		Set<CottageAdditionalService> cottageAdditionalServices = new HashSet<CottageAdditionalService>();
		for (CottageAdditionalService cottageAdditionalService : cottageFastReservationDTO.getCottageAdditionalServices()) {
			cottageAdditionalServices.add(new CottageAdditionalService(CottageAdditionalServiceType.valueOf(cottageAdditionalService.getCottageAdditionalServiceType().toString().replace(" ", "_"))));
		}
		
		ReservationCottage fastReservationCottage = new ReservationCottage(cottageFastReservationDTO.getDateAndTime(), cottageFastReservationDTO.getDuration(), cottageFastReservationDTO.getMaxNumberOfPersons(), cottageFastReservationDTO.getPrice(), cottage, null, cottageAdditionalServices, cottageFastReservationDTO.getDiscountPercentage(), ReservationStatus.WAITING);
		ReservationCottage reservationCottage = reservationCottageRepository.save(fastReservationCottage);
		
		List<Long> subscribedClientIdForCottage = clientRepository.getAllSubscribedClientIdForCottage(cottageId);
		if (!subscribedClientIdForCottage.isEmpty()) {
			List<Client> allClients = clientRepository.findAll();
			for (Client client : allClients) {
				if (subscribedClientIdForCottage.contains(client.getUserId())) {
					sendCottageReservationEmail(client.getEmail(), cottage.getName());
				}
			}
		}
		
		return new CottageFastReservationDTO(reservationCottage.getReservationCottageId(), reservationCottage.getDateAndTime(), reservationCottage.getDuration(), reservationCottage.getMaxNumberOfPersons(), reservationCottage.getCottageAdditionalServices(), reservationCottage.getPrice(), reservationCottage.getDiscountPercentage());
	}
	
	private void sendCottageReservationEmail(String clientEmail, String cottageName) {
		StringBuilder text = new StringBuilder();
		text.append("New reservation is available.");
		emailService.sendNotificaitionAsync(clientEmail, cottageName, text.toString());
	}

	@Override
	public Boolean deleteFastReservation(DeleteCottageReservationDTO deleteCottageReservationDTO) {
		ReservationCottage reservationCottage = reservationCottageRepository.findByReservationCottageId(deleteCottageReservationDTO.getCottageReservationId());
		reservationCottage.setReservationStatus(ReservationStatus.CANCELLED);
		reservationCottageRepository.save(reservationCottage);
		
		return true;
	}


	@Override
	public CottageReservationCalendarDTO getAvailabilityCalendarInformation(Long cottageId) {
		CottageReservationCalendarDTO cottageReservationCalendarDTO = new CottageReservationCalendarDTO();
		cottageReservationCalendarDTO.setCottageReservations(new ArrayList<CottageReservationCalendarInformationDTO>());
		List<ReservationCottage> cottageReservations = reservationCottageRepository.findAllReservationsForCottage(cottageId);
		Cottage cottage = cottageRepository.getOne(cottageId);
		cottageReservationCalendarDTO.setAvailabilityStart(cottage.getAvailabilityStart());
		cottageReservationCalendarDTO.setAvailabilityEnd(cottage.getAvailabilityEnd());
		
		for (ReservationCottage reservationCottage : cottageReservations) {
			if (reservationCottage.getReservationStatus().equals(ReservationStatus.STARTED) || reservationCottage.getReservationStatus().equals(ReservationStatus.SCHEDULED) || reservationCottage.getReservationStatus().equals(ReservationStatus.WAITING)) {
				CottageReservationCalendarInformationDTO cottageReservationCalendarInformationDTO;
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(reservationCottage.getDateAndTime());
				calendar.add(Calendar.DATE, reservationCottage.getDuration());
				if (reservationCottage.getClient() == null) {
					cottageReservationCalendarInformationDTO = new CottageReservationCalendarInformationDTO(reservationCottage.getDateAndTime(), calendar.getTime(), reservationCottage.getCottage().getName(), "", "", "", reservationCottage.getReservationStatus());
				} else {
					cottageReservationCalendarInformationDTO = new CottageReservationCalendarInformationDTO(reservationCottage.getDateAndTime(), calendar.getTime(), reservationCottage.getCottage().getName(), reservationCottage.getClient().getFirstName(), reservationCottage.getClient().getLastName(), reservationCottage.getClient().getEmail(), reservationCottage.getReservationStatus());
				}
				
				cottageReservationCalendarDTO.getCottageReservations().add(cottageReservationCalendarInformationDTO);
			}
		}
		
		return cottageReservationCalendarDTO;
	}
	
	@Scheduled(cron = "1 * * * * *") // test na svaki minut
	public void checkIfReservationsFinishedOrStarted() {
		logger.info("I'm checking to see if any cottage reservations have been finished or started in the meantime.");
		List<ReservationCottage> allScheduledReservation = reservationCottageRepository.findByReservationStatus(ReservationStatus.SCHEDULED);
		Date currentDate = new Date(System.currentTimeMillis());
		for (ReservationCottage reservationCottage : allScheduledReservation) {
			Date startReservationDate = reservationCottage.getDateAndTime();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startReservationDate);
			calendar.add(Calendar.DAY_OF_MONTH, reservationCottage.getDuration());
			Date endReservationDate = calendar.getTime(); 
			
			
			if(startReservationDate.getTime() <= currentDate.getTime() && currentDate.getTime() <= endReservationDate.getTime()) {
				reservationCottage.setReservationStatus(ReservationStatus.STARTED);
				reservationCottageRepository.save(reservationCottage);
			}
			
			if(currentDate.after(endReservationDate)) {
				reservationCottage.setReservationStatus(ReservationStatus.FINISHED);
				reservationCottageRepository.save(reservationCottage);
			}
			
		}
		
		List<ReservationCottage> allWaitingReservation = reservationCottageRepository.findByReservationStatus(ReservationStatus.WAITING);
		for (ReservationCottage reservationCottage : allWaitingReservation) {
			Date waitingReservationDate = reservationCottage.getDateAndTime();
			if(currentDate.after(waitingReservationDate)) {
				reservationCottage.setReservationStatus(ReservationStatus.FINISHED);
				reservationCottageRepository.save(reservationCottage);
			}
		}
	}
	
}
