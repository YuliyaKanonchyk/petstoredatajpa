package by.my.petstoredatajpa.pet;

import by.my.petstoredatajpa.pet.model.Category;
import by.my.petstoredatajpa.pet.model.Pet;
import by.my.petstoredatajpa.pet.model.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/pet")
public class PetController {

    private final PetRepository petRepository;

    public PetController(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @PostMapping
    public ResponseEntity<Pet> addNewPet(@Valid @RequestBody Pet pet) {
        Pet newPet = petRepository.save(pet);
        return new ResponseEntity<>(newPet, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{petID}")
    public ResponseEntity<Pet> deletePetById(@PathVariable @Min(0) Long petID) {
        Optional<Pet> deletePet = petRepository.deletePetById(petID);
        if (deletePet.isPresent()) {
            return new ResponseEntity<>(deletePet.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = "/{petName}")
    public ResponseEntity<Pet> deletePetById(@PathVariable String petName) {
        Optional<Pet> deletePet = petRepository.deletePetByName(petName);
        if (deletePet.isPresent()) {
            return new ResponseEntity<>(deletePet.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/petName")
    public ResponseEntity<Pet> findPetByName(@RequestParam String petName) {
        Optional<Pet> petByName = petRepository.findPetByName(petName);
        if (petByName.isPresent()) {
            return new ResponseEntity<>(petByName.get(), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/petID")
    public ResponseEntity<Pet> findPetByName(@RequestParam @Min(0) Long petID) {
        Optional<Pet> petById = petRepository.findPetById(petID);
        if (petById.isPresent()) {
            return new ResponseEntity<>(petById.get(), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/status/{status}")
    public ResponseEntity<List<Pet>> findPetByStatus(@PathVariable Pet.Status status) {
        Optional<List<Pet>> petByStatus = petRepository.findPetByStatus(status);
        // FIXME: 30.03.2020 CHECK!
        if (petByStatus.isPresent()) {
            return new ResponseEntity<>(new ArrayList<>(petByStatus.get()), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/tag/{tag}")
    public ResponseEntity<List<Pet>> findPetByTag(@PathVariable Tag tag){
        Optional<List<Pet>> petByTag = petRepository.findPetByTag(tag);
        // FIXME: 30.03.2020 CHECK!
        if (petByTag.isPresent()) {
            return new ResponseEntity<>(new ArrayList<>(petByTag.get()), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/category/{category}")
    public ResponseEntity<List<Pet>> findPetByCategory(@PathVariable Category category){
         Optional<List<Pet>> petByCategory = petRepository.findPetByCategory(category);
        // FIXME: 30.03.2020 CHECK!
        if (petByCategory.isPresent()) {
            return new ResponseEntity<>(new ArrayList<>(petByCategory.get()), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(path = "/upd/petID")
    public ResponseEntity<String> updatePetNameById(@RequestParam @Min(0) Long petID, String petName){
        Optional<Pet> petByIdFound = petRepository.findPetById(petID);
        if (petByIdFound.isPresent()) {
            petByIdFound.get().setPetName(petName);
            return new ResponseEntity<>("Pet "+petByIdFound.get()+" was updated", HttpStatus.OK);
        }
        return new ResponseEntity<>("Pet's Name wasn't updated", HttpStatus.BAD_REQUEST);
    }
}
