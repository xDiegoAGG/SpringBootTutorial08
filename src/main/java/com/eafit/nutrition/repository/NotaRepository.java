package com.eafit.nutrition.repository;

import com.eafit.nutrition.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {

    List<Nota> findByPacienteIdOrderByFechaCreacionDesc(Long pacienteId);

    List<Nota> findByNutricionistaId(Long nutricionistaId);

    List<Nota> findByTipoNota(String tipoNota);

    List<Nota> findByPacienteIdAndTipoNota(Long pacienteId, String tipoNota);

    @Query("SELECT n FROM Nota n WHERE n.fechaCreacion BETWEEN :fechaInicio AND :fechaFin " +
           "ORDER BY n.fechaCreacion DESC")
    List<Nota> findByFechaCreacionBetween(
            @Param("fechaInicio") LocalDateTime fechaInicio,
            @Param("fechaFin") LocalDateTime fechaFin);

    Long countByPacienteId(Long pacienteId);

    @Query("SELECT n FROM Nota n WHERE LOWER(n.contenido) LIKE LOWER(CONCAT('%', :texto, '%')) " +
           "OR LOWER(n.titulo) LIKE LOWER(CONCAT('%', :texto, '%'))")
    List<Nota> buscarPorTexto(@Param("texto") String texto);
}
