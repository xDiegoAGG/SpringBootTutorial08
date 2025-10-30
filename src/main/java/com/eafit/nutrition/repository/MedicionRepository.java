package com.eafit.nutrition.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eafit.nutrition.model.Medicion;

@Repository
public interface MedicionRepository extends JpaRepository<Medicion, Long> {

    List<Medicion> findByPacienteIdOrderByFechaDesc(Long pacienteId);

    Optional<Medicion> findFirstByPacienteIdOrderByFechaDesc(Long pacienteId);

    @Query("SELECT m FROM Medicion m WHERE m.paciente.id = :pacienteId " +
           "AND m.fecha BETWEEN :fechaInicio AND :fechaFin " +
           "ORDER BY m.fecha DESC")
    List<Medicion> findByPacienteIdAndFechaBetween(
            @Param("pacienteId") Long pacienteId,
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin);

    List<Medicion> findByNutricionistaIdOrderByFechaDesc(Long nutricionistaId);

    @Query(value = "SELECT * FROM medicion WHERE paciente_id = :pacienteId " +
                   "ORDER BY fecha DESC LIMIT 1", nativeQuery = true)
    Optional<Medicion> findLastMedicionByPacienteId(@Param("pacienteId") Long pacienteId);

    Long countByPacienteId(Long pacienteId);

    @Query("SELECT m FROM Medicion m WHERE m.paciente.id = :pacienteId " +
           "ORDER BY m.fecha DESC")
    List<Medicion> findByPacienteIdForIMCAnalysis(@Param("pacienteId") Long pacienteId);
}
