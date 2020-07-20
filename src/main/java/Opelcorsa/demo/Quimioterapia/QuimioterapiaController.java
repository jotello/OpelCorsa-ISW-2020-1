package Opelcorsa.demo.Quimioterapia;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/quimioterapia")
public class QuimioterapiaController {
	
	@Autowired
	private QuimioterapiaService quimioterapiaService;
	
	
	@GetMapping("")
    public Iterable<Quimioterapia> getSalas (){
        return quimioterapiaService.listAll();
    }
	
	@GetMapping("/{id}")
	public Optional<Quimioterapia> verSala(long id) {
		return quimioterapiaService.encontrarSala(id);
	}
	
	@PostMapping("")
	public ResponseEntity<Quimioterapia> addSala(@RequestBody Quimioterapia quimioterapia){
		Quimioterapia nuevo = quimioterapiaService.agregarSala(quimioterapia);
		return new ResponseEntity<Quimioterapia>(nuevo,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public void deleteSala(@PathVariable long id){
		 quimioterapiaService.eliminarSala(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Quimioterapia> updateSala(@RequestBody Quimioterapia quimioterapiaDetails, @PathVariable long id ){
		//Quimioterapia quimioterapia = quimioterapiaRepository.findById(id);
		//.orElseThrow((Supplier<? extends X>) () -> new ResourceNotFoundException("Employee not found for this id :: " + id));
		Quimioterapia quimioterapia = quimioterapiaService.actualizarSala(id);
		quimioterapia.setId(quimioterapiaDetails.getId());
		quimioterapia.setPiso(quimioterapiaDetails.getPiso());
		quimioterapia.setNumero(quimioterapiaDetails.getNumero());
		final Quimioterapia updatedQuimioterapia = quimioterapiaService.agregarSala(quimioterapia);
		
		
		return new ResponseEntity<Quimioterapia>(updatedQuimioterapia,HttpStatus.CREATED);
	}
	
	
}