package pl.uncleglass.pricingservice.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository  extends JpaRepository<Price, Long> {
}
