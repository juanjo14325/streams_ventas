package org.iesvdm.ventas.controller;

import jakarta.servlet.http.HttpSession;
import org.iesvdm.ventas.dao.ClienteDAO;
import org.iesvdm.ventas.modelo.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public class DemothController {

    //SIN SERVICE, solo actuar sobre plantillas htm

    //ENDPOINTS

    @GetMapping("/demoth1")
    public String demoThText(Model model){

        model.addAttribute("parrafo2","en un lugar de la mancha");

        return "plantilla1";

    }
    @GetMapping("/demoth2")
    public String demothBlock(Model model){
        Cliente cliente = Cliente.builder()
                                           .nombre("Miguel de cervantes")
                                           .build();

        Cliente cliente2 = Cliente.builder()
                                            .nombre("Lope de vega")
                                            .build();

        List<Cliente> list = List.of(cliente,cliente2);

        model.addAttribute("escritores",list);
        return "plantilla2";
    }

    @GetMapping("/demoth3")
    public String demothHttpSession(Model model, HttpSession session){

        String mensajeASesion = "Lo grabé en demoth 3";

        //atributos en model solo visibles en esta plantilla
        String mensajeAModel = "Esto sólo lo ve la plantilla demoth3";

        model.addAttribute("msgModel", mensajeAModel);

        //atributos en sesión son visibles en todos los endpoint
        session.setAttribute("msgSession",mensajeASesion);

        return "demoth3";

    }
    @GetMapping("/demoth3_2")
    public String demothHttpSession2(Model model, HttpSession session){

        return "demoth3_2";
    }

    @GetMapping("/demoth5")
    public String demoth5(Model model){
        Cliente cliente = Cliente.builder().id(101).nombre("cervantes").build();

        model.addAttribute("cliente",cliente);

        return "plantilla5";
    }

    @GetMapping("/demoth/crear")
    public String demothCrear(Model model){

        Cliente cliente = new Cliente();
        model.addAttribute("cliente",cliente);

        return "demoth-crear";


    }

    //@Autowired
    private ClienteDAO clienteDAO;

    @PostMapping("/demoth/crear")
    public String demothCrearSubmit(@ModelAttribute Cliente cliente){

        clienteDAO.create(cliente);

        return "redirect:/demoth/listar";
    }

    @GetMapping("demoth/listar")
    public String demothListarCliente(Model model){

        List<Cliente> clientes =clienteDAO.getAll();
        model.addAttribute("clientes",clientes);

        return "demoth-listar";

    }
}
