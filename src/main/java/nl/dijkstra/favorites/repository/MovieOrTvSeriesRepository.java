package nl.dijkstra.favorites.repository;

import nl.dijkstra.favorites.entity.MovieOrTvSeries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieOrTvSeriesRepository extends JpaRepository<MovieOrTvSeries, Long> {
}
