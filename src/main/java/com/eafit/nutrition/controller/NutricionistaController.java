package com.eafit.nutrition.controller;

import com.eafit.nutrition.model.Nutricionista;
import com.eafit.nutrition.model.Paciente;
import com.eafit.nutrition.repository.NutricionistaRepository;
import com.eafit.nutrition.repository.PacienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/nutricionistas")
public class NutricionistaController {

    private final NutricionistaRepository nutricionistaRepository;
    private final PacienteRepository pacienteRepository;

    public NutricionistaController(NutricionistaRepository nutricionistaRepository,
                                    PacienteRepository pacienteRepository) {
        this.nutricionistaRepository = nutricionistaRepository;
        this.pacienteRepository = pacienteRepository;
    }

    @GetMapping
    public ResponseEntity<List<Nutricionista>> getAllNutricionistas() {
        return ResponseEntity.ok(nutricionistaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nutricionista> getNutricionistaById(@PathVariable Long id) {
        Optional<Nutricionista> nutricionista = nutricionistaRepository.findById(id);
        return nutricionista.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/with-pacientes")
    public ResponseEntity<Nutricionista> getNutricionistaWithPacientes(@PathVariable Long id) {
        Optional<Nutricionista> nutricionista = nutricionistaRepository.findByIdWithPacientes(id);
        return nutricionista.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/activos")
    public ResponseEntity<List<Nutricionista>> getNutricionistasActivos() {
        return ResponseEntity.ok(nutricionistaRepository.findByActivoTrue());
    }

    @GetMapping("/especialidad/{especialidad}")
    public ResponseEntity<List<Nutricionista>> getNutricionistasByEspecialidad(
            @PathVariable String especialidad) {
        return ResponseEntity.ok(nutricionistaRepository.findByEspecialidad(especialidad));
    }

    @PostMapping
    public ResponseEntity<Nutricionista> createNutricionista(@RequestBody Nutricionista nutricionista) {
        Nutricionista created = nutricionistaRepository.save(nutricionista);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nutricionista> updateNutricionista(
            @PathVariable Long id,
            @RequestBody Nutricionista nutricionistaActualizado) {

        Optional<Nutricionista> nutricionistaOpt = nutricionistaRepository.findById(id);
        
        if (nutricionistaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Nutricionista nutricionista = nutricionistaOpt.get();
        nutricionista.setNombre(nutricionistaActualizado.getNombre());
        nutricionista.setApellido(nutricionistaActualizado.getApellido());
        nutricionista.setEspecialidad(nutricionistaActualizado.getEspecialidad());
        nutricionista.setEmail(nutricionistaActualizado.getEmail());
        nutricionista.setTelefono(nutricionistaActualizado.getTelefono());
        nutricionista.setActivo(nutricionistaActualizado.isActivo());

        return ResponseEntity.ok(nutricionistaRepository.save(nutricionista));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNutricionista(@PathVariable Long id) {
        nutricionistaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/pacientes")
    public ResponseEntity<List<Paciente>> getPacientesByNutricionista(@PathVariable Long id) {
        List<Paciente> pacientes = pacienteRepository.findByNutricionistaId(id);
        return ResponseEntity.ok(pacientes);
    }
}
