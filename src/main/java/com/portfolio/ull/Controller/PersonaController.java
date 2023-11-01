package com.portfolio.ull.Controller;

import com.portfolio.ull.Dto.dtoPersona;
import com.portfolio.ull.Entity.Persona;
import com.portfolio.ull.Security.Controller.Mensaje;
import com.portfolio.ull.Service.ImpPersonaService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/personas")
@CrossOrigin(origins= {"http://localhost:4200","https://frontendull-3cead.web.app"})
public class PersonaController {
    @Autowired
    ImpPersonaService personaService;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list(){
        List<Persona> list=personaService.list();
        return new ResponseEntity(list,HttpStatus.OK);
    }
    
    @GetMapping("/traer")
    public List<Persona> getPersona(){
        return personaService.getPersona();
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id")int id){
        if(!personaService.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el id"),HttpStatus.BAD_REQUEST);
        }
        Persona persona= personaService.getOne(id).get();
        return new ResponseEntity(persona,HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoPersona dtopersona){
        if(StringUtils.isBlank(dtopersona.getNombre())){
            return new ResponseEntity(new Mensaje("El nombre es Obligatorio"),HttpStatus.BAD_REQUEST);
        }
        if(personaService.existsByNombre(dtopersona.getNombre())){
            return new ResponseEntity(new Mensaje("Ese nombre ya EXISTE"),HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtopersona.getNombre())){
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"),HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtopersona.getApellido())){
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"),HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtopersona.getDescripcion())){
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"),HttpStatus.BAD_REQUEST);
        }
        
        Persona persona= new Persona(
            dtopersona.getNombre(),dtopersona.getApellido(),dtopersona.getDescripcion(),dtopersona.getImg()
                    );
        personaService.save(persona);
        return new ResponseEntity(new Mensaje("Persona Creada"),HttpStatus.OK);
    }

    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id,@RequestBody dtoPersona dtopersona){
        if(!personaService.existsById(id)){
            return new ResponseEntity(new Mensaje("No Existe el ID"),HttpStatus.NOT_FOUND);
        }
        if(personaService.existsByNombre(dtopersona.getNombre()) && personaService.getByNombre(dtopersona.getNombre()).get().getId() != id){
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"),HttpStatus.BAD_REQUEST);
        }
       
        if(StringUtils.isBlank(dtopersona.getNombre())){
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"),HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtopersona.getApellido())){
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"),HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtopersona.getDescripcion())){
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"),HttpStatus.BAD_REQUEST);
        }
        
        Persona persona= personaService.getOne(id).get();
        
        persona.setNombre(dtopersona.getNombre());
        persona.setApellido(dtopersona.getApellido());
        persona.setDescripcion(dtopersona.getDescripcion());
        persona.setImg(dtopersona.getImg());
        
        personaService.save(persona);
        
        return new ResponseEntity(new Mensaje("Persona Actualizada"),HttpStatus.OK);
    }
    
    
}
