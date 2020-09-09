package be.vdab.springmvc.controllers;

import be.vdab.springmvc.models.Animal;
import be.vdab.springmvc.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/animal")
public class AnimalController {
    @Qualifier("animalService")
    @Autowired
    AnimalService animalService;

    @RequestMapping(value = "/getAllAnimals", method = RequestMethod.GET)
    public ModelAndView getAllAnimals(ModelMap model) {
        model.addAttribute("animals",animalService.getAllAnimals());
        return new ModelAndView("animalListDisplay", model);
    }

    @GetMapping("/add")
    public ModelAndView showAddView(ModelMap modelMap) {
        return new ModelAndView("addAnimal");
    }

    @GetMapping("/{id}/edit")
    public ModelAndView showEditPage(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.addAttribute("animal", animalService.findById(id));
        return new ModelAndView("editAnimal", modelMap);
    }

    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable("id") int id, ModelMap modelMap) {
        animalService.deleteAnimalById(id);
        modelMap.addAttribute("succes", true);
        return new ModelAndView("redirect:/animal/getAllAnimals");
    }

    @PostMapping("/{id}/edit")
    public ModelAndView save(@PathVariable("id") String id, @ModelAttribute Animal animal) {
        animalService.update(animal);
        return new ModelAndView("redirect:/animal/getAllAnimals");
    }

    @PostMapping("/add")
    public ModelAndView addAnimal(@ModelAttribute Animal animal, ModelMap modelMap) {
        animalService.addAnimal(animal);
        return new ModelAndView("redirect:/animal/getAllAnimals");
    }
}
