package com.eafit.nutrition.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eafit.nutrition.model.Medicion;
import com.eafit.nutrition.model.Nutricionista;
import com.eafit.nutrition.model.Paciente;
import com.eafit.nutrition.repository.MedicionRepository;
import com.eafit.nutrition.repository.NutricionistaRepository;
import com.eafit.nutrition.repository.PacienteRepository;

@Service
public class MedicionServiceAutowired {

    @Autowired
    private MedicionRepository medicionRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private NutricionistaRepository nutricionistaRepository;

    @Transactional(readOnly = true)
    public List<Medicion> findAll() {
        return medicionRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Medicion> findById(Long id) {
        return medicionRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Medicion> findByPacienteId(Long pacienteId) {
        return medicionRepository.findByPacienteIdOrderByFechaDesc(pacienteId);
    }

    @Transactional(readOnly = true)
    public Optional<Medicion> findLastMedicionByPacienteId(Long pacienteId) {
        return medicionRepository.findFirstByPacienteIdOrderByFechaDesc(pacienteId);
    }

    @Transactional
    public Medicion createMedicion(Long pacienteId, Long nutricionistaId, Medicion medicion) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con id: " + pacienteId));

        Nutricionista nutricionista = nutricionistaRepository.findById(nutricionistaId)
                .orElseThrow(() -> new RuntimeException("Nutricionista no encontrado con id: " + nutricionistaId));

        medicion.setPaciente(paciente);
        medicion.setNutricionista(nutricionista);

        if (medicion.getFecha() == null) {
            medicion.setFecha(LocalDate.now());
        }

        return medicionRepository.save(medicion);
    }

    @Transactional
    public Medicion updateMedicion(Long id, Medicion medicionActualizada) {
        Medicion medicion = medicionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medición no encontrada con id: " + id));

        medicion.setPeso(medicionActualizada.getPeso());
        medicion.setAltura(medicionActualizada.getAltura());
        medicion.setCircunferenciaCintura(medicionActualizada.getCircunferenciaCintura());
        medicion.setCircunferenciaCadera(medicionActualizada.getCircunferenciaCadera());
        medicion.setPorcentajeGrasaCorporal(medicionActualizada.getPorcentajeGrasaCorporal());

        return medicionRepository.save(medicion);
    }

    @Transactional
    public void deleteMedicion(Long id) {
        medicionRepository.deleteById(id);
    }
}
