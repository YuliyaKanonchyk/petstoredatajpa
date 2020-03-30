package by.my.petstoredatajpa.pet;

import by.my.petstoredatajpa.pet.model.Category;
import by.my.petstoredatajpa.pet.model.Pet;
import by.my.petstoredatajpa.pet.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, Long> {
    Optional<Pet> deletePetById(Long petID);
    Optional<Pet> deletePetByName(String petName);
    Optional<Pet> findPetByName (String petName);
    Optional<Pet> findPetById(Long petID);
    Optional<List<Pet>> findPetByStatus (Pet.Status status);
    Optional<List<Pet>> findPetByTag (Tag tag);
    Optional<List<Pet>> findPetByCategory (Category category);
//    Optional<Pet> updatePetNameById(Long petID, String petName);
//    Optional<Pet> updatePetStatusById(Pet.Status status);
//    Optional<Pet> updatePetTagById(Tag tag);
//    Optional<Pet> updatePetvById(Category category);

}
