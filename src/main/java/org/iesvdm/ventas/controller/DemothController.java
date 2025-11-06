package org.iesvdm.ventas.controller;

import org.iesvdm.ventas.modelo.Cliente;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
