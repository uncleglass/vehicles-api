package pl.uncleglass.pricingservice.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository  extends CrudRepository<Price, Long> {

}
