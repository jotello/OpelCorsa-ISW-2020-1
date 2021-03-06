package Opelcorsa.demo.Sillon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Opelcorsa.demo.Respuesta.Respuesta;

@CrossOrigin
@RestController
@RequestMapping("/quimioterapia/sillones")
public class SillonControlller {

    @Autowired
    private SillonService sillonService;

    @PostMapping("")
    public ResponseEntity<Sillon> addSillon(@RequestBody Sillon sillon) {
        Sillon nuevo = sillonService.agregarSillon(sillon);
        return new ResponseEntity<Sillon>(nuevo, HttpStatus.CREATED);
    }

    @GetMapping("")
    public Iterable<Sillon> getSillones() {
        return sillonService.listAll();
    }

    @DeleteMapping(path = "/{id}")
    public Respuesta deleteSillon(@PathVariable long id) {
        sillonService.eliminarSillon(id);
        
        Respuesta respuesta = new Respuesta(true, "Sillon eliminado satisfactoriamente.");
        return respuesta;
    }

    @GetMapping(path = "/{id}")
    public Sillon getSillon(@PathVariable long id) {
        return sillonService.obtenerSillon(id).get();
    }

    @PutMapping(path = "/{id}")
    public Sillon updateSillon(@PathVariable long id, @RequestBody Sillon sillon){
    	Sillon sillonFinal = sillonService.obtenerSillon(id).get();
    	sillonFinal.setEstado(sillon.getEstado());
    	sillonService.agregarSillon(sillonFinal);
        return sillonService.obtenerSillon(id).get();
    }
}