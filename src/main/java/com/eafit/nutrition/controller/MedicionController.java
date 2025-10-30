package com.eafit.nutrition.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eafit.nutrition.model.Medicion;
import com.eafit.nutrition.service.MedicionServiceAutowired;
import com.eafit.nutrition.service.MedicionServiceConstructor;
import com.eafit.nutrition.service.MedicionServiceSetter;

@RestController
@RequestMapping("/api/mediciones")
public class MedicionController {

    private final MedicionServiceConstructor constructorService;

    @Autowired
    private MedicionServiceAutowired autowiredService;

    private MedicionServiceSetter setterService;

    public MedicionController(MedicionServiceConstructor constructorService) {
        this.constructorService = constructorService;
    }

    @Autowired
    public void setSetterService(MedicionServiceSetter setterService) {
        this.setterService = setterService;
    }

    @GetMapping("/constructor")
    public ResponseEntity<List<Medicion>> getAllMedicionesConstructor() {
        return ResponseEntity.ok(constructorService.findAll());
    }

    @GetMapping("/constructor/{id}")
    public ResponseEntity<Medicion> getMedicionByIdConstructor(@PathVariable Long id) {
        Optional<Medicion> medicion = constructorService.findById(id);
        return medicion.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/constructor/paciente/{pacienteId}/nutricionista/{nutricionistaId}")
    public ResponseEntity<Medicion> createMedicionConstructor(
            @PathVariable Long pacienteId,
            @PathVariable Long nutricionistaId,
            @RequestBody Medicion medicion) {

        Medicion createdMedicion = constructorService.createMedicion(pacienteId, nutricionistaId, medicion);
        return new ResponseEntity<>(createdMedicion, HttpStatus.CREATED);
    }

    @GetMapping("/autowired")
    public ResponseEntity<List<Medicion>> getAllMedicionesAutowired() {
        return ResponseEntity.ok(autowiredService.findAll());
    }

    @GetMapping("/autowired/{id}")
    public ResponseEntity<Medicion> getMedicionByIdAutowired(@PathVariable Long id) {
        Optional<Medicion> medicion = autowiredService.findById(id);
        return medicion.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/autowired/paciente/{pacienteId}/nutricionista/{nutricionistaId}")
    public ResponseEntity<Medicion> createMedicionAutowired(
            @PathVariable Long pacienteId,
            @PathVariable Long nutricionistaId,
            @RequestBody Medicion medicion) {

        Medicion createdMedicion = autowiredService.createMedicion(pacienteId, nutricionistaId, medicion);
        return new ResponseEntity<>(createdMedicion, HttpStatus.CREATED);
    }

    @GetMapping("/setter")
    public ResponseEntity<List<Medicion>> getAllMedicionesSetter() {
        return ResponseEntity.ok(setterService.findAll());
    }

    @GetMapping("/setter/{id}")
    public ResponseEntity<Medicion> getMedicionByIdSetter(@PathVariable Long id) {
        Optional<Medicion> medicion = setterService.findById(id);
        return medicion.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/setter/paciente/{pacienteId}/nutricionista/{nutricionistaId}")
    public ResponseEntity<Medicion> createMedicionSetter(
            @PathVariable Long pacienteId,
            @PathVariable Long nutricionistaId,
            @RequestBody Medicion medicion) {

        Medicion createdMedicion = setterService.createMedicion(pacienteId, nutricionistaId, medicion);
        return new ResponseEntity<>(createdMedicion, HttpStatus.CREATED);
    }

    @GetMapping("/compare/{id}")
    public ResponseEntity<Map<String, Object>> compareMedicionById(@PathVariable Long id) {
        Optional<Medicion> constructorResult = constructorService.findById(id);
        Optional<Medicion> autowiredResult = autowiredService.findById(id);
        Optional<Medicion> setterResult = setterService.findById(id);

        Map<String, Object> response = new HashMap<>();
        response.put("constructorService", constructorResult.orElse(null));
        response.put("autowiredService", autowiredResult.orElse(null));
        response.put("setterService", setterResult.orElse(null));
        response.put("message", "Los tres métodos devuelven el mismo resultado");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/compare/paciente/{pacienteId}/ultima-medicion")
    public ResponseEntity<Map<String, Object>> compareLastMedicion(@PathVariable Long pacienteId) {
        Optional<Medicion> constructorResult = constructorService.findLastMedicionByPacienteId(pacienteId);
        Optional<Medicion> autowiredResult = autowiredService.findLastMedicionByPacienteId(pacienteId);
        Optional<Medicion> setterResult = setterService.findLastMedicionByPacienteId(pacienteId);

        Map<String, Object> response = new HashMap<>();
        response.put("constructorService", constructorResult.orElse(null));
        response.put("autowiredService", autowiredResult.orElse(null));
        response.put("setterService", setterResult.orElse(null));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<Medicion>> getMedicionesByPaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(constructorService.findByPacienteId(pacienteId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medicion> updateMedicion(
            @PathVariable Long id,
            @RequestBody Medicion medicion) {

        Medicion updatedMedicion = constructorService.updateMedicion(id, medicion);
        return ResponseEntity.ok(updatedMedicion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicion(@PathVariable Long id) {
        constructorService.deleteMedicion(id);
        return ResponseEntity.noContent().build();
    }
}
