package rva.ctrls;

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
import rva.jpa.Proizvod;
import rva.repository.ProizvodRepository;

@CrossOrigin
@RestController
@Api(tags = {"Proizvod CRUD operacije"})
public class ProizvodRestController {
	@Autowired
	private ProizvodRepository proizvodRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("proizvod")
	@ApiOperation(value = "Vraæa kolekciju svih proizvoda iz baze podataka.")
	public Collection<Proizvod> getProizvodi(){
		return proizvodRepository.findAll();
	}
	
	@GetMapping("proizvod/{id}")
	@ApiOperation(value = "Vraæa proizvod u odnosu na prosleðenu vrednost path varijable id.")
	public Proizvod getProizvod(@PathVariable("id") Integer id){
		return proizvodRepository.getOne(id);
	}
	
	@GetMapping("proizvodNaziv/{naziv}")
	@ApiOperation(value = "Vraæa kolekciju proizvoda koji imaju naziv koji sadrži vrednost prosleðenu u okviru path varijable naziv.")
	public Collection<Proizvod> getProizvodByNaziv(@PathVariable("naziv") String naziv){
		return proizvodRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	//insert
	@PostMapping("proizvod")
	@ApiOperation(value = "Dodaje novi proizvod u bazu podataka.")
	public ResponseEntity<Proizvod> insertProizvod(@RequestBody Proizvod proizvod){
		if(!proizvodRepository.existsById(proizvod.getId())) {
			proizvodRepository.save(proizvod);
			return new ResponseEntity<Proizvod>(HttpStatus.OK);
		}
		return new ResponseEntity<Proizvod>(HttpStatus.CONFLICT);
	}
	
	//update
	@PutMapping("proizvod")
	@ApiOperation(value = "Update-uje postojeæi proizvod u bazi podataka.")
	public ResponseEntity<Proizvod> updateProizvod(@RequestBody Proizvod proizvod){
		if(!proizvodRepository.existsById(proizvod.getId())) {
			return new ResponseEntity<Proizvod>(HttpStatus.CONFLICT);
		}
		proizvodRepository.save(proizvod);
		return new ResponseEntity<Proizvod>(HttpStatus.OK);
	}
	
	//delete
	@DeleteMapping("proizvod/{id}")
	@ApiOperation(value = "Briše proizvod èija id vrednost odgovara vrednosti prosleðene path varijable id.")
	public ResponseEntity<Proizvod> deleteProizvod(@PathVariable("id") Integer id){
		if(!proizvodRepository.existsById(id)) 
			return new ResponseEntity<Proizvod>(HttpStatus.CONFLICT);
		proizvodRepository.deleteById(id);
		if(id == -100)
			jdbcTemplate.execute(" INSERT INTO \"proizvod\" (\"id\", \"naziv\", \"proizvodjac\") "
					+ "VALUES (-100, 'Test naziv', 1)");
		return new ResponseEntity<Proizvod>(HttpStatus.OK);
	}
}
