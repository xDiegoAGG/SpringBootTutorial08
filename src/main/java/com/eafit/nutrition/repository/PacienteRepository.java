package com.eafit.nutrition.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eafit.nutrition.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Optional<Paciente> findByEmail(String email);

    List<Paciente> findByActivoTrue();

    List<Paciente> findByNutricionistaId(Long nutricionistaId);

    List<Paciente> findByNutricionistaIdAndActivoTrue(Long nutricionistaId);

    interface PacienteResumen {
        Long getId();
        String getNombre();
        String getApellido();
        String getEmail();
    }

    @Query("SELECT p.id as id, p.nombre as nombre, p.apellido as apellido, p.email as email " +
           "FROM Paciente p WHERE p.nutricionista.id = :nutricionistaId")
    List<PacienteResumen> findPacienteResumenByNutricionistaId(@Param("nutricionistaId") Long nutricionistaId);

    @Query("SELECT p FROM Paciente p LEFT JOIN FETCH p.notas WHERE p.id = :id")
    Optional<Paciente> findByIdWithNotas(@Param("id") Long id);

    @Query("SELECT DISTINCT p FROM Paciente p " +
           "LEFT JOIN FETCH p.notas " +
           "LEFT JOIN FETCH p.mediciones " +
           "WHERE p.id = :id")
    Optional<Paciente> findByIdWithAll(@Param("id") Long id);
}
