package Opelcorsa.demo.Sillon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quimioterapia/sillones")
public class SillonControlller {
    
    @Autowired
    private SillonService sillonService;

    @PostMapping("")
    public ResponseEntity<Sillon> addSillon (@RequestBody Sillon sillon){
        Sillon nuevo = sillonService.agregarSillon(sillon);
        return new ResponseEntity<Sillon>(nuevo, HttpStatus.CREATED);
    }

    @GetMapping("")
    public Iterable<Sillon> getSillones (){
        return sillonService.listAll();
    }
    
}