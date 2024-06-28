package org.penzgtu.Application.controller;

import org.penzgtu.Application.data.DataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/data-generator")
public class DataGeneratorController {

    private final DataGenerator dataGenerator;

    @Autowired
    public DataGeneratorController(DataGenerator dataGenerator) {
        this.dataGenerator = dataGenerator;
    }

    @GetMapping("/generate")
    public String generateData() {
        dataGenerator.init();
        return "Data generation started";
    }
}