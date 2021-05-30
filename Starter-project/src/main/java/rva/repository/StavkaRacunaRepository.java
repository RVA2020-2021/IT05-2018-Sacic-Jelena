package rva.repository;

import java.math.BigDecimal;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rva.jpa.StavkaRacuna;
import rva.jpa.Racun;

public interface StavkaRacunaRepository extends JpaRepository<StavkaRacuna, Integer>{
	Collection<StavkaRacuna> findByRacun(Racun r);
	Collection<StavkaRacuna> findByCenaLessThanOrderById(BigDecimal cena);
	
	@Query(value = "select coalesce(max(redni_broj)+1,1) from stavka_racuna where racun = ?1", nativeQuery = true)
	Integer nextRbr(Integer racunId);
}
