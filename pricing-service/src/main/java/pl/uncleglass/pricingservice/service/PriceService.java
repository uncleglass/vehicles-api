package pl.uncleglass.pricingservice.service;

import org.springframework.stereotype.Service;
import pl.uncleglass.pricingservice.domain.Price;
import pl.uncleglass.pricingservice.domain.PriceRepository;

import java.util.Optional;

@Service
public class PriceService {
    private PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Price getPriceByCarId(Long id) throws PriceException {
        Optional<Price> price = priceRepository.findById(id);
        return price.orElseThrow(PriceException::new);
    }
}
