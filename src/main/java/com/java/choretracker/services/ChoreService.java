package com.java.choretracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.choretracker.models.Chore;
import com.java.choretracker.repositories.ChoreRepository;

@Service
public class ChoreService {
	@Autowired
    private ChoreRepository choreRepository;
	
	// return all chores
    public List<Chore> allChores() {
        return (List<Chore>) choreRepository.findAll();
    }
    
    
    // create chore
    public Chore createChore(Chore chore) {
        return choreRepository.save(chore);
    }
    
    
    // retrieve chore
    public Chore findChore(Long id) {
        Optional<Chore> optionalChore = choreRepository.findById(id);
        if(optionalChore.isPresent()) {
            return optionalChore.get();
        } else {
            return null;
        }
    }
    
    
    //update chore 
    public Chore updateChore(Chore chore) {
        return choreRepository.save(chore);
    }
    
    
    
    // delete chore
    public void deleteChore(Long id) {
        Optional<Chore> optionalChore = choreRepository.findById(id);
        if(optionalChore.isPresent()) {
        	choreRepository.deleteById(id);
        } 
    }
    

}
