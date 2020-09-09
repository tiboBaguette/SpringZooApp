package be.vdab.springmvc.services;

import be.vdab.springmvc.doa.AnimalDAO;
import be.vdab.springmvc.models.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier(value = "animalService")
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    AnimalDAO dao;

    public List<Animal> getAllAnimals(){
        return dao.getAllAnimals();
    }

    @Override
    public void addAnimal(Animal animal) {
        dao.addAnimal(animal);
    }

    @Override
    public Animal findById(int id) {
        return dao.findById(id);
    }

    @Override
    public void update(Animal animal) {
        dao.update(animal);
    }

    @Override
    public void deleteAnimalById(int id) {
        dao.deleteAnimal(id);
    }

}
