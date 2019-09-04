package com.easy.tailoring.controller;

import com.easy.tailoring.exceptions.ResourceNotFoundException;
import com.easy.tailoring.model.Measurement;
import com.easy.tailoring.repository.MeasurementRepository;
import com.easy.tailoring.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
public class MeasurementController {

    @Autowired
    private MeasurementRepository measurementRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customer/{customerId}/measurements")
    public List<Measurement> getMeasurementsByCustomerId(@PathVariable Long customerId) {
        return measurementRepository.findByCustomerId(customerId);
    }

    @PostMapping("/customer/{customerId}/measurement")
    public Measurement addMeasurement(@PathVariable Long customerId,
                                 @Valid @RequestBody Measurement measurement) {
        return customerRepository.findById(customerId)
                .map(question -> {
                    measurement.setCustomer(question);
                    return measurementRepository.save(measurement);
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + customerId));
    }

    @PutMapping("/customer/{customerId}/measurement/{measurementId}")
    public Measurement updateMeasurement(@PathVariable Long customerId,
                                    @PathVariable Long measurementId,
                                    @Valid @RequestBody Measurement measurementRequest) {
        if(!customerRepository.existsById(customerId)) {
            throw new ResourceNotFoundException("Question not found with id " + customerId);
        }

        return measurementRepository.findById(measurementId)
                .map(measurement -> {
                    return measurementRepository.save(measurement);
                }).orElseThrow(() -> new ResourceNotFoundException("Measurement not found with id " + measurementId));
    }

    @DeleteMapping("/customer/{customerId}/measurement/{measurementId}")
    public ResponseEntity<?> deleteMeasurement(@PathVariable Long customerId,
                                          @PathVariable Long measurementId) {
        if(!customerRepository.existsById(customerId)) {
            throw new ResourceNotFoundException("Question not found with id " + customerId);
        }

        return measurementRepository.findById(measurementId)
                .map(measurement -> {
                    measurementRepository.delete(measurement);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Measurement not found with id " + measurementId));

    }
}