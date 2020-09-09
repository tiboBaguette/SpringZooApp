package be.vdab.springmvc.services;

import be.vdab.springmvc.models.Animal;

import java.util.List;

public interface AnimalService {
    public List<Animal> getAllAnimals();
    public void addAnimal(Animal animal);

    Animal findById(int id);

    void update(Animal animal);

    void deleteAnimalById(int id);
}
