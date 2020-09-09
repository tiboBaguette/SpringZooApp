package be.vdab.springmvc.doa;

import be.vdab.springmvc.models.Animal;

import java.util.List;

public interface AnimalDAO
{
    public List<Animal> getAllAnimals();
    public void addAnimal(Animal animal);

    Animal findById(int id);

    void update(Animal animal);

    void deleteAnimal(int id);
}
