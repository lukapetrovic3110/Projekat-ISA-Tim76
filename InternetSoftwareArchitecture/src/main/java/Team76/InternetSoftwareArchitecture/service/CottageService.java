package Team76.InternetSoftwareArchitecture.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.AddCottageDTO;
import Team76.InternetSoftwareArchitecture.dto.CottageDTO;
import Team76.InternetSoftwareArchitecture.iservice.ICottageService;
import Team76.InternetSoftwareArchitecture.model.Client;
import Team76.InternetSoftwareArchitecture.model.Cottage;
import Team76.InternetSoftwareArchitecture.model.Image;
import Team76.InternetSoftwareArchitecture.model.PriceList;
import Team76.InternetSoftwareArchitecture.model.PriceTag;
import Team76.InternetSoftwareArchitecture.model.ReservationCottage;
import Team76.InternetSoftwareArchitecture.model.Rule;
import Team76.InternetSoftwareArchitecture.repository.IAddressRepository;
import Team76.InternetSoftwareArchitecture.repository.ICottageRepository;
import Team76.InternetSoftwareArchitecture.repository.IReservationCottageRepository;

@Service
public class CottageService implements ICottageService {

	private ICottageRepository cottageRepository;
	private IAddressRepository addressRepository;
	private IReservationCottageRepository reservationCottageRepository;

	@Autowired
	public CottageService(ICottageRepository cottageRepository, IAddressRepository addressRepository, IReservationCottageRepository reservationCottageRepository) {
		super();
		this.cottageRepository = cottageRepository;
		this.addressRepository = addressRepository;
		this.reservationCottageRepository = reservationCottageRepository;
	}

	@Override
	public Cottage saveCottage(AddCottageDTO addCottageDTO) {
		Cottage cottage = new Cottage();
		cottage.setName(addCottageDTO.getName());
		cottage.setDescription(addCottageDTO.getDescription());
		cottage.setAddress(addressRepository.save(addCottageDTO.getAddress()));
		cottage.setNumberOfRooms(addCottageDTO.getNumberOfRooms());
		cottage.setNumberOfBedsPerRoom(addCottageDTO.getNumberOfBedsPerRoom());
		cottage.setAvailabilityStart(addCottageDTO.getAvailabilityStart());
		cottage.setAvailabilityEnd(addCottageDTO.getAvailabilityEnd());

		Set<PriceTag> priceTags = new HashSet<PriceTag>();
		for (String priceTag : addCottageDTO.getPriceList()) {
			String[] priceTagInfo = priceTag.split(";");
			priceTags.add(new PriceTag(Double.parseDouble(priceTagInfo[1]), priceTagInfo[0]));
		}
		cottage.setPriceList(new PriceList(priceTags));

		Set<Rule> cottageRules = new HashSet<Rule>();
		for (String cottageRule : addCottageDTO.getCottageRules()) {
			cottageRules.add(new Rule(cottageRule));
		}
		cottage.setCottageRules(cottageRules);

		Cottage cottagedb = cottageRepository.save(cottage);
		cottageRepository.saveCottageOwnerForCottage(cottagedb.getCottageId(), addCottageDTO.getCottageOwnerId());
		
		return findById(cottagedb.getCottageId());
	}

	@Override
	public Cottage findById(Long id) {
		return cottageRepository.findByCottageId(id);
	}

	@Override
	public void saveImageForCottage(Long cottageId, Long imageId) {
		cottageRepository.saveImageForCottage(cottageId, imageId);
	}

	@Override
	public List<CottageDTO> getAllCottagesForCottageOwner(Long cottageOwnerId) {
		List<Cottage> cottagesForCottageOwner = cottageRepository.getAllCottagesForCottageOwner(cottageOwnerId);
		ArrayList<CottageDTO> cottageDTOs = new ArrayList<CottageDTO>();
		
		for (Cottage cottage : cottagesForCottageOwner) {
			List<Image> images = cottage.getImages();
			List<String> cottageImages = new ArrayList<String>();
			for (Image image : images) {
				cottageImages.add(image.getName());
			}
			
			CottageDTO cottageDTO = new CottageDTO(cottage.getCottageId(), cottage.getName(), cottage.getDescription(),
					cottage.getAddress().getStreet(), cottage.getAddress().getStreetNumber(),
					cottage.getAddress().getCity(), cottage.getAddress().getCountry(),
					cottage.getAddress().getLongitude(), cottage.getAddress().getLatitude(), cottage.getRating(), cottage.getPricePerDay(), 
					cottage.getAvailabilityStart(), cottage.getAvailabilityEnd(), cottage.getNumberOfRooms(),
					cottage.getNumberOfBedsPerRoom(), cottage.getCottageOwner().getFirstName(),
					cottage.getCottageOwner().getLastName(), cottage.getCottageOwner().getEmail(),
					cottage.getCottageOwner().getPhoneNumber(), cottageImages);

			cottageDTOs.add(cottageDTO);
		}
		
		return cottageDTOs;

	}
	
	public List<Cottage> all() {
		return cottageRepository.findAll();
	}

	@Override
	public List<CottageDTO> findAll() {
		List<Cottage> cottages = all();
		List<CottageDTO> cottageDTOs = new ArrayList<CottageDTO>();
		
		for (Cottage cottage : cottages) {
			List<Image> images = cottage.getImages();
			List<String> cottageImages = new ArrayList<String>();
			for (Image image : images) {
				cottageImages.add(image.getName());
			}
			
			CottageDTO cottageDTO = new CottageDTO(cottage.getCottageId(), cottage.getName(), cottage.getDescription(),
					cottage.getAddress().getStreet(), cottage.getAddress().getStreetNumber(),
					cottage.getAddress().getCity(), cottage.getAddress().getCountry(),
					cottage.getAddress().getLongitude(), cottage.getAddress().getLatitude(), cottage.getRating(), cottage.getPricePerDay(),
					cottage.getAvailabilityStart(), cottage.getAvailabilityEnd(), cottage.getNumberOfRooms(),
					cottage.getNumberOfBedsPerRoom(), cottage.getCottageOwner().getFirstName(),
					cottage.getCottageOwner().getLastName(), cottage.getCottageOwner().getEmail(),
					cottage.getCottageOwner().getPhoneNumber(), cottageImages);

			cottageDTOs.add(cottageDTO);
		}

		return cottageDTOs;
	}
	
	public List<CottageDTO> findAvailableCottagesForSelectedDateInterval(Date startDateAndTime, Integer duration) {
		Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Calendar startDateAndTimeCalendar = Calendar.getInstance();
		startDateAndTimeCalendar.setTime(startDateAndTime);
		Long startDesiredReservation = startDateAndTimeCalendar.getTimeInMillis();
		
		Calendar endDateAndTimeCalendar = Calendar.getInstance();
		endDateAndTimeCalendar.setTime(startDateAndTime);
		endDateAndTimeCalendar.add(Calendar.DAY_OF_MONTH, duration);
		Date endDateAndTime = endDateAndTimeCalendar.getTime(); 
		Long endDesiredReservation = endDateAndTimeCalendar.getTimeInMillis();
		
		List<Cottage> cottages = all();
		List<Cottage> availableCottagesForDateInterval = new ArrayList<Cottage>();
		
		for (Cottage c : cottages) 
			if (startDateAndTime.after(c.getAvailabilityStart()) && endDateAndTime.before(c.getAvailabilityEnd())) 
				availableCottagesForDateInterval.add(c);
			
		
		List<CottageDTO> availableCottageDTOs = new ArrayList<CottageDTO>();
		for (Cottage cottage : availableCottagesForDateInterval) {
			List<ReservationCottage> unavailableReservationsForCottage = new ArrayList<ReservationCottage>();
			List<ReservationCottage> allScheduledAndStartedReservationForCottage = reservationCottageRepository.findAllScheduledAndStartedReservationForCottage(cottage.getCottageId());
			List<ReservationCottage> allCanceledReservationForCottageByClientId = reservationCottageRepository.findAllCanceledReservationForCottageByClientId(cottage.getCottageId(), client.getUserId());
			unavailableReservationsForCottage.addAll(allScheduledAndStartedReservationForCottage);
			unavailableReservationsForCottage.addAll(allCanceledReservationForCottageByClientId);
			Boolean isAvailableCottage = true;
			for (ReservationCottage r : unavailableReservationsForCottage) {
				Calendar dateAndTimeReservationCalendarStart = Calendar.getInstance();
				dateAndTimeReservationCalendarStart.setTime(r.getDateAndTime());
				Long startReservation = dateAndTimeReservationCalendarStart.getTimeInMillis();
				
				Calendar dateAndTimeReservationCalendarEnd = Calendar.getInstance();
				dateAndTimeReservationCalendarEnd.setTime(r.getDateAndTime());
				dateAndTimeReservationCalendarEnd.add(Calendar.DAY_OF_MONTH, duration);
				Long endReservation = dateAndTimeReservationCalendarEnd.getTimeInMillis();
				
				// I - u sredini trajanja zakazanog pregleda
				if (startReservation <= startDesiredReservation && endReservation >= endDesiredReservation) {
					isAvailableCottage = false;
					break;
				}
				// II - okruzi vec zakazan pregled
				if (startReservation >= startDesiredReservation && endReservation <= endDesiredReservation) {
					isAvailableCottage = false;
					break;
				}
				// III
				if (startReservation >= startDesiredReservation && startReservation <= endDesiredReservation) {
					isAvailableCottage = false;
					break;
				} 
				// IV 
				if (startDesiredReservation <= endReservation && endReservation <= endDesiredReservation) {
					isAvailableCottage = false;
					break;
				}
				
			}
			
			if(isAvailableCottage) {
				List<Image> images = cottage.getImages();
				List<String> cottageImages = new ArrayList<String>();
				for (Image image : images) {
					cottageImages.add(image.getName());
				}
				CottageDTO cottageDTO = new CottageDTO(cottage.getCottageId(), cottage.getName(), cottage.getDescription(),
						cottage.getAddress().getStreet(), cottage.getAddress().getStreetNumber(),
						cottage.getAddress().getCity(), cottage.getAddress().getCountry(),
						cottage.getAddress().getLongitude(), cottage.getAddress().getLatitude(), cottage.getRating(), cottage.getPricePerDay(),
						cottage.getAvailabilityStart(), cottage.getAvailabilityEnd(), cottage.getNumberOfRooms(),
						cottage.getNumberOfBedsPerRoom(), cottage.getCottageOwner().getFirstName(),
						cottage.getCottageOwner().getLastName(), cottage.getCottageOwner().getEmail(),
						cottage.getCottageOwner().getPhoneNumber(), cottageImages);
				availableCottageDTOs.add(cottageDTO);
			}
			
		} 
	
		return availableCottageDTOs;
	}

	@Override
	public CottageDTO getCottageById(Long id) {
		Cottage c = cottageRepository.findByCottageId(id);
		List<Image> images = c.getImages();
		List<String> cottageImages = new ArrayList<String>();
		for (Image image : images) {
			cottageImages.add(image.getName());
		}
		CottageDTO cottageDTO = new CottageDTO(c.getCottageId(), c.getName(), c.getDescription(),
				c.getAddress().getStreet(), c.getAddress().getStreetNumber(),
				c.getAddress().getCity(), c.getAddress().getCountry(),
				c.getAddress().getLongitude(), c.getAddress().getLatitude(), c.getRating(), c.getPricePerDay(),
				c.getAvailabilityStart(), c.getAvailabilityEnd(), c.getNumberOfRooms(),
				c.getNumberOfBedsPerRoom(), c.getCottageOwner().getFirstName(),
				c.getCottageOwner().getLastName(), c.getCottageOwner().getEmail(),
				c.getCottageOwner().getPhoneNumber(), cottageImages);

		return cottageDTO;
	}
  
}
