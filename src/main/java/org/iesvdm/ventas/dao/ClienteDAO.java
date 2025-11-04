package org.iesvdm.ventas.dao;

import org.iesvdm.ventas.modelo.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteDAO {

        //Mapear operaciones CRUD a BBDD

        void create(Cliente cliente);

        public List<Cliente> getAll();

        public Optional<Cliente> find(int id);

        public void update(Cliente cliente);

        public void delete(int id);
    }

