package org.iesvdm.ventas.dao;
import lombok.AllArgsConstructor;
import org.iesvdm.ventas.modelo.Cliente;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("sqlImpl")
public interface ClienteDAO {

        //Mapear operaciones CRUD a BBDD

        void create(Cliente cliente);

        public List<Cliente> getAll();

        public Optional<Cliente> find(int id);

        public void update(Cliente cliente);

        public void delete(int id);
    }

