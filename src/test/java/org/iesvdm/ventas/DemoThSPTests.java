package org.iesvdm.ventas;

import org.iesvdm.ventas.dao.ClienteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DemoThSPTests {

    @Autowired
    ClienteDAO clienteDAO;

}
