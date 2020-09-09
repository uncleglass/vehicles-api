package pl.uncleglass.api.service;

import org.springframework.stereotype.Service;
import pl.uncleglass.api.client.maps.MapsClient;
import pl.uncleglass.api.client.prices.PriceClient;
import pl.uncleglass.api.domain.Location;
import pl.uncleglass.api.domain.car.Car;
import pl.uncleglass.api.domain.car.CarRepository;

import java.util.List;

/**
 * Implements the car service create, read, update or delete
 * information about vehicles, as well as gather related
 * location and price data when desired.
 */
@Service
public class CarService {
    private final CarRepository carRepository;
    private final PriceClient priceClient;
    private final MapsClient mapsClient;

    public CarService(CarRepository carRepository,
                      PriceClient priceClient,
                      MapsClient mapsClient) {
        this.carRepository = carRepository;
        this.priceClient = priceClient;
        this.mapsClient = mapsClient;
    }

    /**
     * Gathers a list of all vehicles
     * @return a list of all vehicles in the CarRepository
     */
    public List<Car> list() {
        return carRepository.findAll();
    }

    /**
     * Gets car information by ID (or throws exception if non-existent)
     * @param id the ID number of the car to gather information on
     * @return the requested car's information, including location and price
     */
    public Car findById(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(CarNotFoundException::new);

        String price = priceClient.getPrice(id);
        car.setPrice(price);

        Location location = car.getLocation();
        Location address = mapsClient.getAddress(location);
        car.setLocation(address);

        return car;
    }

    /**
     * Either creates or updates a vehicle, based on prior existence of car
     * @param car A car object, which can be either new or existing
     * @return the new/updated car is stored in the repository
     */
    public Car save(Car car) {
        if (car.getId() != null) {
            return carRepository.findById(car.getId())
                    .map(carToBeUpdated -> {
                        carToBeUpdated.setDetails(car.getDetails());
                        carToBeUpdated.setLocation(car.getLocation());
                        return carRepository.save(carToBeUpdated);
                    }).orElseThrow(CarNotFoundException::new);
        }

        return carRepository.save(car);
    }

    /**
     * Deletes a given car by ID
     * @param id the ID number of the car to delete
     */
    public void delete(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(CarNotFoundException::new);
        carRepository.delete(car);
    }
}
