package com.portfolio.ull.Controller;

import com.portfolio.ull.Dto.dtoHys;
import com.portfolio.ull.Entity.hys;
import com.portfolio.ull.Security.Controller.Mensaje;
import com.portfolio.ull.Service.Shys;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
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

@RestController
@CrossOrigin(origins= {"http://localhost:4200","https://frontendull-3cead.web.app"})
@RequestMapping("/skill")
public class CHys {
    @Autowired
    Shys shys;
    
    @GetMapping("/lista")
    public ResponseEntity<List<hys>> list(){
        List<hys> list = shys.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
     @GetMapping("/detail/{id}")
    public ResponseEntity<hys> getById(@PathVariable("id")int id){
        if(!shys.existsById(id)){
            return new ResponseEntity(new Mensaje("No Existe"),HttpStatus.NOT_FOUND);
        }
        hys hYs = shys.getOne(id).get();
        return new ResponseEntity(hYs,HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!shys.existsById(id)){
            return new ResponseEntity(new Mensaje("No Existe"),HttpStatus.NOT_FOUND);
        }
        shys.delete(id);
        return new ResponseEntity(new Mensaje("HyS Eliminada"),HttpStatus.OK);
    }
    
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoHys dtohys){
        if(StringUtils.isBlank(dtohys.getNombre()))
            return new ResponseEntity(new Mensaje("El Nombre Obligatorio"),HttpStatus.BAD_REQUEST);
        if(shys.existsByNombre(dtohys.getNombre()))
            return new ResponseEntity(new Mensaje("Esa HyS ya existe"), HttpStatus.BAD_REQUEST);
        
        hys hYs = new hys(dtohys.getNombre(),dtohys.getPorcentaje());
        shys.save(hYs);
        
        return new ResponseEntity (new Mensaje("HyS Cargada"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoHys dtohys){
        if(!shys.existsById(id))
            return new ResponseEntity(new Mensaje("el id no existe"), HttpStatus.BAD_REQUEST);
        
        if(shys.existsByNombre(dtohys.getNombre()) && shys.getByNombre(dtohys.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Esa HyS ya existe"),HttpStatus.BAD_REQUEST);
           
        if(StringUtils.isBlank(dtohys.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        
        
        hys hYs = shys.getOne(id).get();
        hYs.setNombre(dtohys.getNombre());
        hYs.setPorcentaje(dtohys.getPorcentaje());
        
        shys.save(hYs);
        return new ResponseEntity(new Mensaje("HyS modificada correctamente"),HttpStatus.OK);
    
    }
}
