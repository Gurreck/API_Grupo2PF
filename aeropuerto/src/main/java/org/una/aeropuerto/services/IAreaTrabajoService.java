/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.entities.AreaTrabajo;

/**
 *
 * @author acer
 */
public interface IAreaTrabajoService {
    public Optional<List<AreaTrabajo>> findAll();

    public Optional<AreaTrabajo> findById(Long id);

    public Optional<List<AreaTrabajo>> findByNombreResponsable(String responsable);

    public Optional<AreaTrabajo> findByNombreArea(String area);

    public AreaTrabajo create(AreaTrabajo AreaTrabajo);

    public Optional<AreaTrabajo> update(AreaTrabajo AreaTrabajo, Long id);

}
