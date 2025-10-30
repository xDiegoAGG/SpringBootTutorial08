package com.eafit.nutrition.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eafit.nutrition.model.Nutricionista;

@Repository
public interface NutricionistaRepository extends JpaRepository<Nutricionista, Long> {

    Optional<Nutricionista> findByEmail(String email);

    Optional<Nutricionista> findByNumeroLicencia(String numeroLicencia);

    List<Nutricionista> findByActivoTrue();

    List<Nutricionista> findByEspecialidad(String especialidad);

    @Query("SELECT n FROM Nutricionista n LEFT JOIN FETCH n.pacientes WHERE n.id = :id")
    Optional<Nutricionista> findByIdWithPacientes(@Param("id") Long id);

    @EntityGraph(attributePaths = {"pacientes"})
    @Query("SELECT n FROM Nutricionista n WHERE n.id = :id")
    Optional<Nutricionista> findByIdWithPacientesGraph(@Param("id") Long id);

    @Query("SELECT DISTINCT n FROM Nutricionista n " +
           "LEFT JOIN FETCH n.pacientes " +
           "LEFT JOIN FETCH n.notas " +
           "WHERE n.id = :id")
    Optional<Nutricionista> findByIdWithPacientesAndNotas(@Param("id") Long id);
}
