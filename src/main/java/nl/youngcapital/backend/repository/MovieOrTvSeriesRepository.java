package nl.youngcapital.backend.repository;

import nl.youngcapital.backend.entity.MovieOrTvSeries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieOrTvSeriesRepository extends JpaRepository<MovieOrTvSeries, Long> {
}
