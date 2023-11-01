package com.portfolio.ull.Service;

import com.portfolio.ull.Entity.Persona;
import com.portfolio.ull.Repository.IPersonaRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImpPersonaService{
    @Autowired IPersonaRepository ipersonaRepository;
    
     public List<Persona> list(){
        return ipersonaRepository.findAll();
    }
    
    public List<Persona> getPersona(){
        List<Persona> persona= ipersonaRepository.findAll();
        return persona;
    }
     
    public Optional<Persona> getOne (int id){
        return ipersonaRepository.findById(id);
    }
    
    public Optional<Persona> getByNombre(String nombre){
        return ipersonaRepository.findByNombre(nombre);
    }
    
    public void save(Persona persona){
        ipersonaRepository.save(persona);
    }
    
    public void delete(int id){
        ipersonaRepository.deleteById(id);
        
    }
    
    public boolean existsById(int id){
        return ipersonaRepository.existsById(id);
        
    }
    
    public boolean existsByNombre(String nombre){
        return ipersonaRepository.existsByNombre(nombre);
    }
}
