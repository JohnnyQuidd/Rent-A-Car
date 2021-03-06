package com.group56.adminservice.controller;

import com.group56.adminservice.DTO.CarModelDTO;
import com.group56.adminservice.service.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin-service/model")
public class CarModelController {
    private CarModelService carModelService;

    @Autowired
    public CarModelController(CarModelService carModelService){
        this.carModelService = carModelService;
    }

    @GetMapping
    public ResponseEntity<?> getActiveModels() {
        return carModelService.getActiveCarModels();
    }

    @PostMapping
    public ResponseEntity<?> addNewModel(@RequestBody CarModelDTO carModelDTO){
        return carModelService.addNewCarModel(carModelDTO);
    }

    @PutMapping
    public ResponseEntity<?> updateModel(@RequestBody CarModelDTO carModelDTO) {
        return carModelService.updateCarModel(carModelDTO);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> removeModel(@RequestBody CarModelDTO carModelDTO) {
        return carModelService.removeCarModel(carModelDTO);
    }
}
