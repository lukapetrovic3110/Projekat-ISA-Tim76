package Team76.InternetSoftwareArchitecture.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.CottageFastReservationDTO;
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
				cottageOwnerCottagesId.contains(reservationCottage.getCottage().getCottageId());
				reservationsForCottageOwner.add(reservationCottage);
			} else if (reservationStatus.equals(ReservationStatus.FINISHED)) {
				if (reservationCottage.getReservationStatus().equals(reservationStatus) && !cottageReservationIdFromCottageReservationReport.contains(reservationCottage.getReservationCottageId())) {
					cottageOwnerCottagesId.contains(reservationCottage.getCottage().getCottageId());
					reservationsForCottageOwner.add(reservationCottage);
				}
			} else {
				if (reservationCottage.getReservationStatus().equals(reservationStatus)) {
					cottageOwnerCottagesId.contains(reservationCottage.getCottage().getCottageId());
					reservationsForCottageOwner.add(reservationCottage);
				}
			}
		}
		
		for (ReservationCottage reservationCottage : reservationsForCottageOwner) {
			CottageReservationClientInformationDTO cottageReservationClientInformationDTO = new CottageReservationClientInformationDTO(reservationCottage.getClient().getFirstName(), reservationCottage.getClient().getLastName(), reservationCottage.getClient().getEmail(), reservationCottage.getClient().getPhoneNumber(), reservationCottage.getClient().getAddress());
			CottageReservationInformationDTO cottageReservationInformationDTO = new CottageReservationInformationDTO(reservationCottage.getReservationCottageId(), reservationCottage.getDateAndTime(), reservationCottage.getDuration(), reservationCottage.getMaxNumberOfPersons(), reservationCottage.getPrice(), reservationCottage.getCottage().getName(), reservationCottage.getCottage().getNumberOfRooms(), reservationCottage.getCottage().getNumberOfBedsPerRoom(), cottageReservationClientInformationDTO, reservationCottage.getReservationStatus());
			
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
		List<ReservationCottage> cottageReservations = reservationCottageRepository.findAllFastReservationsForCottage(cottageId);
		List<CottageFastReservationDTO> cottageFastReservationsDTO = new ArrayList<CottageFastReservationDTO>();
		
		for (ReservationCottage cottageReservation : cottageReservations) {
			if (cottageReservation.getReservationStatus().equals(ReservationStatus.WAITING)) {
				cottageFastReservationsDTO.add(new CottageFastReservationDTO(cottageReservation.getReservationCottageId() ,cottageReservation.getDateAndTime(), cottageReservation.getDuration(), cottageReservation.getMaxNumberOfPersons(), cottageReservation.getCottageAdditionalServices(), cottageReservation.getPrice(), cottageReservation.getDiscountPercentage()));
			}
		}
		
		return cottageFastReservationsDTO;
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
		
		return new CottageFastReservationDTO(reservationCottage.getReservationCottageId() ,reservationCottage.getDateAndTime(), reservationCottage.getDuration(), reservationCottage.getMaxNumberOfPersons(), reservationCottage.getCottageAdditionalServices(), reservationCottage.getPrice(), reservationCottage.getDiscountPercentage());
	}
	
	private void sendCottageReservationEmail(String clientEmail, String cottageName) {
		StringBuilder text = new StringBuilder();
		text.append("New reservation is available.");
		emailService.sendNotificaitionAsync(clientEmail, cottageName, text.toString());
	}

	@Override
	public Boolean deleteFastReservation(DeleteCottageReservationDTO deleteCottageReservationDTO) {
		ReservationCottage reservationCottage = reservationCottageRepository.getOne(deleteCottageReservationDTO.getCottageReservationId());
		reservationCottage.setReservationStatus(ReservationStatus.CANCELLED);
		reservationCottageRepository.save(reservationCottage);
		
		return true;
	}
	
	@Scheduled(cron = "1 * * * * *") // test na svaki minut
	public void checkIfReservationsFinished() {
		logger.info("I'm checking to see if any cottage reservations have been finished in the meantime.");
		List<ReservationCottage> allScheduledReservation = reservationCottageRepository.findByReservationStatus(ReservationStatus.SCHEDULED);
		Date currentDate = new Date(System.currentTimeMillis());
		for (ReservationCottage reservationCottage : allScheduledReservation)
			if(currentDate.after(reservationCottage.getDateAndTime())) {
				reservationCottage.setReservationStatus(ReservationStatus.FINISHED);
				reservationCottageRepository.save(reservationCottage);
			}
	}
	
}
