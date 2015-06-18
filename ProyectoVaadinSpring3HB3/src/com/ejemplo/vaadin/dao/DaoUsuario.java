package com.ejemplo.vaadin.dao;

import com.ejemplo.vaadin.entidades.Usuario;
import java.util.List;

/**
 * (DAO, Objeto de Acceso a Datos) DAO para la entidad Usuario
 *
 * @author
 */
public interface DaoUsuario extends DaoGenerico<Usuario> {

    /**
     * Metodo para obtener los usuarios activos (no eliminados) en la Base de
     * datos
     *
     * @return un listado con los objetos Usuario recuperados
     */
    public List<Usuario> getActivos();

    /**
     * Obtiene los usuarios el usuario activo al que corresponde el correo
     * suministrado
     *
     * @param correo
     * @return El usuario al que corresponde el correo suministrado, o null si
     * no se encuentra
     */
    public Usuario getActivoByCorreo(String correo);
}
