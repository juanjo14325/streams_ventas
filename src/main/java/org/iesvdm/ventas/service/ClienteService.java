package org.iesvdm.ventas.service;

import lombok.AllArgsConstructor;
import org.iesvdm.ventas.dao.ClienteDAO;
import org.iesvdm.ventas.modelo.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private ClienteDAO clienteDAO;

    public ClienteService(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public List<Cliente>all(){
        return clienteDAO.getAll();
    }

}
