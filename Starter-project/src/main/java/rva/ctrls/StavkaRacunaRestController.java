package rva.ctrls;

import java.math.BigDecimal;
import java.util.Collection;

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
import rva.jpa.Racun;
import rva.jpa.StavkaRacuna;
import rva.repository.RacunRepository;
import rva.repository.StavkaRacunaRepository;

@CrossOrigin
@RestController
@Api(tags = {"StavkaRacuna CRUD operacije"})
public class StavkaRacunaRestController {
	
	@Autowired
	private StavkaRacunaRepository stavkaRacunaRepository;
	
	@Autowired
	private RacunRepository racunRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("stavkaRacuna")
	@ApiOperation(value = "Vra�a kolekciju svih stavki ra�una iz baze podataka.")
	public Collection<StavkaRacuna> getStavkeRacuna(){
		return stavkaRacunaRepository.findAll();
	}
	
	@GetMapping("stavkaRacuna/{id}")
	@ApiOperation(value = "Vra�a stavku ra�una u odnosu na prosle�enu vrednost path varijable id.")
	public StavkaRacuna getStavkaRacuna(@PathVariable("id") Integer id){
		return stavkaRacunaRepository.getOne(id);
	}
	
	@GetMapping("stavkeZaRacunID/{id}")
	@ApiOperation(value = "Vra�a kolekciju stavki ra�una koje pripadaju ra�unu pod id-jem koji ima vrednost prosle�enu u okviru path varijable id.")
	public Collection<StavkaRacuna> getStavkeRacunaPoRacunuID(@PathVariable("id") Integer id){
		Racun r = racunRepository.getOne(id);
		return stavkaRacunaRepository.findByRacun(r);
	}
	
	@GetMapping("stavkeRacunaCena/{cena}")
	@ApiOperation(value = "Vra�a kolekciju stavki ra�una koje imaju cenu manju od prosle�ene vrednosti u okviru path varijable cena.")
	public Collection<StavkaRacuna> getStavkeRacunaCena(@PathVariable("cena") BigDecimal cena){
		return stavkaRacunaRepository.findByCenaLessThanOrderById(cena);
	}
	
	//insert
	@PostMapping("stavkaRacuna")
	@ApiOperation(value = "Dodaje novu stavku ra�una u bazu podataka.")
	public ResponseEntity<StavkaRacuna> insertStavkaRacuna(@RequestBody StavkaRacuna stavkaRacuna){
		if(!stavkaRacunaRepository.existsById(stavkaRacuna.getId())) {
			stavkaRacuna.setRedniBroj(stavkaRacunaRepository.nextRbr(stavkaRacuna.getRacun().getId()));
			stavkaRacunaRepository.save(stavkaRacuna);
			return new ResponseEntity<StavkaRacuna>(HttpStatus.OK);
		}
		return new ResponseEntity<StavkaRacuna>(HttpStatus.CONFLICT);
	}
	
	//update
	@PutMapping("stavkaRacuna")
	@ApiOperation(value = "Update-uje postoje�u stavku ra�una u bazi podataka.")
	public ResponseEntity<StavkaRacuna> updateStavkaRacuna(@RequestBody StavkaRacuna stavkaRacuna){
		if(!stavkaRacunaRepository.existsById(stavkaRacuna.getId()))
			return new ResponseEntity<StavkaRacuna>(HttpStatus.CONFLICT);
		stavkaRacunaRepository.save(stavkaRacuna);
		return new ResponseEntity<StavkaRacuna>(HttpStatus.OK);
	}
	
	//delete
	@DeleteMapping("stavkaRacuna/{id}")
	@ApiOperation(value = "Bri�e stavku ra�una �ija id vrednost odgovara vrednosti prosle�ene path varijable id.")
	public ResponseEntity<StavkaRacuna> deleteStavkaRacuna(@PathVariable("id") Integer id){
		if(!stavkaRacunaRepository.existsById(id))
			return new ResponseEntity<StavkaRacuna>(HttpStatus.CONFLICT);
		stavkaRacunaRepository.deleteById(id);
		if(id == -100)
			jdbcTemplate.execute(" INSERT INTO \"stavka_racuna\" (\"id\", \"redni_broj\", \"kolicina\", \"jedinica_mere\", \"cena\", \"racun\", \"proizvod\") "
					+ "VALUES (-100, 100, 1, 'kom', 100, 1, 1)");
		return new ResponseEntity<StavkaRacuna>(HttpStatus.OK);
	}
}
