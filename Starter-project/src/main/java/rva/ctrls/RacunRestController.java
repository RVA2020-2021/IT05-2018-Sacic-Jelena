package rva.ctrls;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rva.repository.RacunRepository;
import rva.jpa.Racun;

@CrossOrigin
@RestController
@Api(tags = {"Racun CRUD operacije"})
public class RacunRestController {

	@Autowired
	private RacunRepository racunRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("racun")
	@ApiOperation(value = "Vra�a kolekciju svih ra�una iz baze podataka.")
	public Collection<Racun> getRacuni(){
		return racunRepository.findAll();
	}
	
	@GetMapping("racun/{id}")
	@ApiOperation(value = "Vra�a ra�un u odnosu na prosle�enu vrednost path varijable id.")
	public Racun getRacun(@PathVariable("id") Integer id){
		return racunRepository.getOne(id);
	}
	
	@GetMapping("racunNacinPlacanja/{nacinPlacanja}")
	@ApiOperation(value = "Vra�a kolekciju ra�una koji imaju na�in pla�anja koji sadr�i vrednost prosle�enu u okviru path varijable nacinPlacanja.")
	public Collection<Racun> getRacunByNacinPlacanja(@PathVariable("nacinPlacanja") String nacinPlacanja){
		return racunRepository.findByNacinPlacanjaContainingIgnoreCase(nacinPlacanja);
	}
	
	//insert
	@PostMapping("racun")
	@ApiOperation(value = "Dodaje novi ra�un u bazu podataka.")
	public ResponseEntity<Racun> insertRacun(@RequestBody Racun racun){
		if(!racunRepository.existsById(racun.getId())) {
			racunRepository.save(racun);
			return new ResponseEntity<Racun>(HttpStatus.OK);
		}
		return new ResponseEntity<Racun>(HttpStatus.CONFLICT);
	}
	
	//update
	@PutMapping("racun")
	@ApiOperation(value = "Update-uje postoje�i ra�un u bazi podataka.")
	public ResponseEntity<Racun> updateRacun(@RequestBody Racun racun){
		if(!racunRepository.existsById(racun.getId())) {
			return new ResponseEntity<Racun>(HttpStatus.CONFLICT);
		}
		racunRepository.save(racun);
		return new ResponseEntity<Racun>(HttpStatus.OK);
	}
	
	//delete
	//@Transactional
	@DeleteMapping("racun/{id}")
	@ApiOperation(value = "Bri�e ra�un �ija id vrednost odgovara vrednosti prosle�ene path varijable id.")
	public ResponseEntity<Racun> deleteRacun(@PathVariable("id") Integer id){
		if(!racunRepository.existsById(id)) 
			return new ResponseEntity<Racun>(HttpStatus.CONFLICT);
		jdbcTemplate.execute("delete from stavka_racuna where racun = " +id);
		racunRepository.deleteById(id);
		if(id == -100)
			jdbcTemplate.execute(" INSERT INTO \"racun\" (\"id\", \"datum\", \"nacin_placanja\") "
					+ "VALUES (-100, to_date('01.01.2021.', 'dd.mm.yyyy.'), 'Test nacin placanja')");
		return new ResponseEntity<Racun>(HttpStatus.OK);
	}
}
