package Team76.InternetSoftwareArchitecture.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.ShipDTO;
import Team76.InternetSoftwareArchitecture.iservice.IShipService;
import Team76.InternetSoftwareArchitecture.model.Client;
import Team76.InternetSoftwareArchitecture.model.Image;
import Team76.InternetSoftwareArchitecture.model.ReservationShip;
import Team76.InternetSoftwareArchitecture.model.Ship;
import Team76.InternetSoftwareArchitecture.repository.IReservationShipRepository;
import Team76.InternetSoftwareArchitecture.repository.IShipRepository;

@Service
public class ShipService implements IShipService  {
	
	private IShipRepository shipRepository;
	private IReservationShipRepository reservationShipRepository;
	
	@Autowired
	public ShipService(IShipRepository shipRepository, IReservationShipRepository reservaitonReservationShipRepository) {
		this.shipRepository = shipRepository;
		this.reservationShipRepository = reservaitonReservationShipRepository;
	}
	
	@Override
	public List<Ship> all() {
		return shipRepository.findAll();
	}
	
	@Override
	public List<ShipDTO> findAllShipWithStatusActive() {
		List<Ship> ships = shipRepository.getAllShipWithStatusActive();
		List<ShipDTO> shipDTOs = new ArrayList<ShipDTO>();
		
		for (Ship ship : ships) {
			List<Image> images = ship.getImages();
			List<String> shipImages = new ArrayList<String>();
			for (Image image : images) {
				shipImages.add(image.getName());
			}
			shipDTOs.add(new ShipDTO(ship.getShipId(), ship.getName(), ship.getDescription(), ship.getAddress().getStreet(), ship.getAddress().getStreetNumber(), ship.getAddress().getCity(), ship.getAddress().getCountry(), ship.getAddress().getLongitude(), ship.getAddress().getLatitude(), ship.getRating(), ship.getPricePerHour(), ship.getLength(), ship.getEngineNumber(), ship.getEnginePower(), ship.getMaxSpeed(), ship.getCapacity(), ship.getShipType(), ship.getShipOwner().getFirstName(), ship.getShipOwner().getLastName(), ship.getShipOwner().getEmail(), ship.getShipOwner().getPhoneNumber(), shipImages, ship.getAvailabilityStart(), ship.getAvailabilityEnd(), ship.getShipRules()));
		}
		return shipDTOs;
	}

	@Override
	public ShipDTO getShipById(Long shipId) {
		Ship s = shipRepository.findById(shipId).get();
		List<Image> images = s.getImages();
		List<String> shipImages = new ArrayList<String>();
		for (Image image : images) {
			shipImages.add(image.getName());
		}
		ShipDTO shipDTO = new ShipDTO(s.getShipId(), s.getName(), s.getDescription(), s.getAddress().getStreet(), s.getAddress().getStreetNumber(), s.getAddress().getCity(), s.getAddress().getCountry(), s.getAddress().getLongitude(), s.getAddress().getLatitude(), s.getRating(), s.getPricePerHour(), s.getLength(), s.getEngineNumber(), s.getEnginePower(), s.getMaxSpeed(), s.getCapacity(), s.getShipType(), s.getShipOwner().getFirstName(), s.getShipOwner().getLastName(), s.getShipOwner().getEmail(), s.getShipOwner().getPhoneNumber(), shipImages, s.getAvailabilityStart(), s.getAvailabilityEnd(), s.getShipRules(), s.getPriceList()); 
		return shipDTO;
	}

	@Override
	public List<ShipDTO> findAvailableShipsForSelectedDateIntervalAndNumberOfGuests(Date startDateAndTime, Integer duration, Integer numberOfGuests) {
		Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Calendar startDateAndTimeCalendar = Calendar.getInstance();
		startDateAndTimeCalendar.setTime(startDateAndTime);
		Long startDesiredReservation = startDateAndTimeCalendar.getTimeInMillis();
		
		Calendar endDateAndTimeCalendar = Calendar.getInstance();
		endDateAndTimeCalendar.setTime(startDateAndTime);
		endDateAndTimeCalendar.add(Calendar.HOUR_OF_DAY, duration);
		Date endDateAndTime = endDateAndTimeCalendar.getTime(); 
		Long endDesiredReservation = endDateAndTimeCalendar.getTimeInMillis();
		
		List<Ship> ships = shipRepository.getAllShipWithStatusActive();
		List<Ship> availableShipsForDateInterval = new ArrayList<Ship>();
		
		for (Ship c : ships) 
			if (startDateAndTime.after(c.getAvailabilityStart()) && endDateAndTime.before(c.getAvailabilityEnd())) 
				availableShipsForDateInterval.add(c);
			
		
		List<ShipDTO> availableShipDTOs = new ArrayList<ShipDTO>();
		for (Ship ship : availableShipsForDateInterval) {
			List<ReservationShip> unavailableReservationsForShip = new ArrayList<ReservationShip>();
			List<ReservationShip> allScheduledAndStartedReservationForShip = reservationShipRepository.findAllScheduledAndStartedReservationForShip(ship.getShipId());
			List<ReservationShip> allCanceledReservationForShipByClientId = reservationShipRepository.findAllCanceledReservationForShipByClientId(ship.getShipId(), client.getUserId());
			unavailableReservationsForShip.addAll(allScheduledAndStartedReservationForShip);
			unavailableReservationsForShip.addAll(allCanceledReservationForShipByClientId);
			Boolean isAvailableShip = true;
			for (ReservationShip r : unavailableReservationsForShip) {
				Calendar dateAndTimeReservationCalendarStart = Calendar.getInstance();
				dateAndTimeReservationCalendarStart.setTime(r.getDateAndTime());
				Long startReservation = dateAndTimeReservationCalendarStart.getTimeInMillis();
				
				Calendar dateAndTimeReservationCalendarEnd = Calendar.getInstance();
				dateAndTimeReservationCalendarEnd.setTime(r.getDateAndTime());
				dateAndTimeReservationCalendarEnd.add(Calendar.HOUR_OF_DAY, duration);
				Long endReservation = dateAndTimeReservationCalendarEnd.getTimeInMillis();
				
				// I - u sredini trajanja zakazanog pregleda
				if (startReservation <= startDesiredReservation && endReservation >= endDesiredReservation) {
					isAvailableShip = false;
					break;
				}
				// II - okruzi vec zakazan pregled
				if (startReservation >= startDesiredReservation && endReservation <= endDesiredReservation) {
					isAvailableShip = false;
					break;
				}
				// III
				if (startReservation >= startDesiredReservation && startReservation <= endDesiredReservation) {
					isAvailableShip = false;
					break;
				} 
				// IV 
				if (startDesiredReservation <= endReservation && endReservation <= endDesiredReservation) {
					isAvailableShip = false;
					break;
				}
				
			}
			
			int maxNumberOfPersons = ship.getCapacity();
			
			if(isAvailableShip && numberOfGuests <= maxNumberOfPersons) {
				List<Image> images = ship.getImages();
				List<String> shipImages = new ArrayList<String>();
				for (Image image : images) {
					shipImages.add(image.getName());
				}
				ShipDTO shipDTO = new ShipDTO(ship.getShipId(), ship.getName(), ship.getDescription(), ship.getAddress().getStreet(), ship.getAddress().getStreetNumber(), ship.getAddress().getCity(), ship.getAddress().getCountry(), ship.getAddress().getLongitude(), ship.getAddress().getLatitude(), ship.getRating(), ship.getPricePerHour(), ship.getLength(), ship.getEngineNumber(), ship.getEnginePower(), ship.getMaxSpeed(), ship.getCapacity(), ship.getShipType(), ship.getShipOwner().getFirstName(), ship.getShipOwner().getLastName(), ship.getShipOwner().getEmail(), ship.getShipOwner().getPhoneNumber(), shipImages, ship.getAvailabilityStart(), ship.getAvailabilityEnd(), ship.getShipRules(), ship.getPriceList());
				availableShipDTOs.add(shipDTO);
			}
			
		} 
	
		return availableShipDTOs;
	}
	
}
