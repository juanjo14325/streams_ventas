package org.iesvdm.ventas.controller;

import lombok.Getter;
import org.iesvdm.ventas.modelo.Cliente;
import org.iesvdm.ventas.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.util.List;
@Controller
public class ClienteController {

    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    //endpoint
    @GetMapping("/clientes")
    public String listarClientes(Model model){

        List<Cliente> clienteList = clienteService.all();

        model.addAttribute("listaClientes", clienteList);

        return "clientes";
    }

}
