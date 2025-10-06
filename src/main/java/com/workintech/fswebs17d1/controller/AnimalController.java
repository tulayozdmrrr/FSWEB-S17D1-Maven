package com.workintech.fswebs17d1.controller;

import com.workintech.fswebs17d1.entity.Animal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/workintech/animal")
public class AnimalController {

    @Value("${course.name}")
    private String courseName;

    @Value("${project.developer.fullname}")
    private String developerName;

    // Map ile veri saklıyoruz
    private Map<Integer, Animal> animals = new HashMap<>();

    // GET /workintech/animal -> tüm animal listesi
    @GetMapping
    public List<Animal> getAllAnimals() {
        return new ArrayList<>(animals.values());
    }

    // GET /workintech/animal/{id} -> tek animal
    @GetMapping("/{id}")
    public Animal getAnimalById(@PathVariable Integer id) {
        return animals.get(id);
    }

    // POST /workintech/animal -> map’e ekle
    @PostMapping
    public String addAnimal(@RequestBody Animal animal) {
        animals.put(animal.getId(), animal);
        return "Animal eklendi: " + animal.getName();
    }


    // PUT /workintech/animal/{id} -> map’i güncelle
    @PutMapping("/{id}")
    public String updateAnimal(@PathVariable Integer id, @RequestBody Animal updatedAnimal) {
        if (animals.containsKey(id)) {
            animals.put(id, updatedAnimal);
            return "Animal güncellendi: " + updatedAnimal.getName();
        } else {
            return "Animal bulunamadı!";
        }
    }

    // DELETE /workintech/animal/{id} -> map’ten sil
    @DeleteMapping("/{id}")
    public String deleteAnimal(@PathVariable Integer id) {
        if (animals.containsKey(id)) {
            animals.remove(id);
            return "Animal silindi: " + id;
        } else {
            return "Animal bulunamadı!";
        }
    }

    // Opsiyonel: course ve developer bilgisi
    @GetMapping("/info")
    public String getInfo() {
        return "Course: " + courseName + ", Developer: " + developerName;
    }
}
