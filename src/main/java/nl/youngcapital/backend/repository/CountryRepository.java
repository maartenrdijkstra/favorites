package nl.youngcapital.backend.repository;

import nl.youngcapital.backend.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
