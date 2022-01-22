package Team76.InternetSoftwareArchitecture.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.AdventureDTO;
import Team76.InternetSoftwareArchitecture.iservice.IAdventureService;
import Team76.InternetSoftwareArchitecture.model.Adventure;
import Team76.InternetSoftwareArchitecture.model.Client;
import Team76.InternetSoftwareArchitecture.model.Image;
import Team76.InternetSoftwareArchitecture.model.ReservationAdventure;
import Team76.InternetSoftwareArchitecture.repository.IAdventureRepository;
import Team76.InternetSoftwareArchitecture.repository.IReservationAdventureRepository;

@Service
public class AdventureService implements IAdventureService {

	private IAdventureRepository adventureRepository;
	private IReservationAdventureRepository reservationAdventureRepository;

	@Autowired
	public AdventureService(IAdventureRepository adventureRepository, IReservationAdventureRepository reservationAdventureRepository) {
		this.adventureRepository = adventureRepository;
		this.reservationAdventureRepository = reservationAdventureRepository;
	}

	@Override
	public List<Adventure> all() {
		return adventureRepository.findAll();
	}

	@Override
	public List<AdventureDTO> findAllAdventureWithStatusActive() {
		List<Adventure> adventures = adventureRepository.getAllAdventureWithStatusActive();
		List<AdventureDTO> adventureDTOs = new ArrayList<AdventureDTO>();

		for (Adventure adventure : adventures) {
			List<Image> images = adventure.getImages();
			List<String> adventureImages = new ArrayList<String>();
			for (Image image : images) {
				adventureImages.add(image.getName());
			}
			AdventureDTO adventureDTO = new AdventureDTO(adventure.getAdventureId(), adventure.getName(), adventure.getDescription(), adventure.getRating(), adventure.getInstructorBiography(), adventure.getMaxNumberOfPersons(), adventure.getAddress().getStreet(), adventure.getAddress().getStreetNumber(), adventure.getAddress().getCity(), adventure.getAddress().getCountry(), adventure.getAddress().getLongitude(), adventure.getAddress().getLatitude(), adventure.getPricePerHour(), adventure.getAvailabilityStart(), adventure.getAvailabilityEnd(), adventure.getFishingInstructor().getFirstName(), adventure.getFishingInstructor().getLastName(), adventure.getFishingInstructor().getEmail(), adventure.getFishingInstructor().getPhoneNumber(), adventure.getFishingInstructor().getUserId() ,adventureImages);
			adventureDTOs.add(adventureDTO);
		}

		return adventureDTOs;
	}

	@Override
	public AdventureDTO getAdventureById(Long adventureId) {
		Adventure a = adventureRepository.findById(adventureId).get();
		List<Image> images = a.getImages();
		List<String> adventureImages = new ArrayList<String>();
		for (Image image : images) {
			adventureImages.add(image.getName());
		}
		AdventureDTO adventureDTO = new AdventureDTO(a.getAdventureId(), a.getName(), a.getDescription(), a.getRating(), a.getInstructorBiography(), a.getMaxNumberOfPersons(), a.getAddress().getStreet(), a.getAddress().getStreetNumber(), a.getAddress().getCity(), a.getAddress().getCountry(), a.getAddress().getLongitude(), a.getAddress().getLatitude(), a.getPricePerHour(), a.getAvailabilityStart(), a.getAvailabilityEnd(), a.getFishingInstructor().getFirstName(), a.getFishingInstructor().getLastName(), a.getFishingInstructor().getEmail(), a.getFishingInstructor().getPhoneNumber(), a.getFishingInstructor().getUserId(), adventureImages); 
		return adventureDTO;
	}

	@Override
	public List<AdventureDTO> findAvailableAdventuresForSelectedDateInterval(Date startDateAndTime, Integer duration) {
		Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Calendar startDateAndTimeCalendar = Calendar.getInstance();
		startDateAndTimeCalendar.setTime(startDateAndTime);
		Long startDesiredReservation = startDateAndTimeCalendar.getTimeInMillis();
		
		Calendar endDateAndTimeCalendar = Calendar.getInstance();
		endDateAndTimeCalendar.setTime(startDateAndTime);
		endDateAndTimeCalendar.add(Calendar.HOUR_OF_DAY, duration);
		Date endDateAndTime = endDateAndTimeCalendar.getTime(); 
		Long endDesiredReservation = endDateAndTimeCalendar.getTimeInMillis();
		
		List<Adventure> adventures = adventureRepository.getAllAdventureWithStatusActive();
		List<Adventure> availableAdventuresForDateInterval = new ArrayList<Adventure>();
		
		for (Adventure c : adventures) 
			if (startDateAndTime.after(c.getAvailabilityStart()) && endDateAndTime.before(c.getAvailabilityEnd())) 
				availableAdventuresForDateInterval.add(c);
			
		
		List<AdventureDTO> availableAdventureDTOs = new ArrayList<AdventureDTO>();
		for (Adventure adventure : availableAdventuresForDateInterval) {
			List<ReservationAdventure> unavailableReservationsForAdventure = new ArrayList<ReservationAdventure>();
			List<ReservationAdventure> allScheduledAndStartedReservationForAdventure = reservationAdventureRepository.findAllScheduledAndStartedReservationForAdventure(adventure.getAdventureId());
			List<ReservationAdventure> allCanceledReservationForAdventureByClientId = reservationAdventureRepository.findAllCanceledReservationForAdventureByClientId(adventure.getAdventureId(), client.getUserId());
			unavailableReservationsForAdventure.addAll(allScheduledAndStartedReservationForAdventure);
			unavailableReservationsForAdventure.addAll(allCanceledReservationForAdventureByClientId);
			Boolean isAvailableAdventure = true;
			for (ReservationAdventure r : unavailableReservationsForAdventure) {
				Calendar dateAndTimeReservationCalendarStart = Calendar.getInstance();
				dateAndTimeReservationCalendarStart.setTime(r.getDateAndTime());
				Long startReservation = dateAndTimeReservationCalendarStart.getTimeInMillis();
				
				Calendar dateAndTimeReservationCalendarEnd = Calendar.getInstance();
				dateAndTimeReservationCalendarEnd.setTime(r.getDateAndTime());
				dateAndTimeReservationCalendarEnd.add(Calendar.HOUR_OF_DAY, duration);
				Long endReservation = dateAndTimeReservationCalendarEnd.getTimeInMillis();
				
				// I - u sredini trajanja zakazanog pregleda
				if (startReservation <= startDesiredReservation && endReservation >= endDesiredReservation) {
					isAvailableAdventure = false;
					break;
				}
				// II - okruzi vec zakazan pregled
				if (startReservation >= startDesiredReservation && endReservation <= endDesiredReservation) {
					isAvailableAdventure = false;
					break;
				}
				// III
				if (startReservation >= startDesiredReservation && startReservation <= endDesiredReservation) {
					isAvailableAdventure = false;
					break;
				} 
				// IV 
				if (startDesiredReservation <= endReservation && endReservation <= endDesiredReservation) {
					isAvailableAdventure = false;
					break;
				}
				
			}
			
			if(isAvailableAdventure) {
				List<Image> images = adventure.getImages();
				List<String> adventureImages = new ArrayList<String>();
				for (Image image : images) {
					adventureImages.add(image.getName());
				}
				AdventureDTO adventureDTO = new AdventureDTO(adventure.getAdventureId(), adventure.getName(), adventure.getDescription(), adventure.getRating(), adventure.getInstructorBiography(), adventure.getMaxNumberOfPersons(), adventure.getAddress().getStreet(), adventure.getAddress().getStreetNumber(), adventure.getAddress().getCity(), adventure.getAddress().getCountry(), adventure.getAddress().getLongitude(), adventure.getAddress().getLatitude(), adventure.getPricePerHour(), adventure.getAvailabilityStart(), adventure.getAvailabilityEnd(), adventure.getFishingInstructor().getFirstName(), adventure.getFishingInstructor().getLastName(), adventure.getFishingInstructor().getEmail(), adventure.getFishingInstructor().getPhoneNumber(), adventure.getFishingInstructor().getUserId() ,adventureImages);
				availableAdventureDTOs.add(adventureDTO);
			}
			
		} 
	
		return availableAdventureDTOs;
	}

}
