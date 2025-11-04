package org.iesvdm.ventas.dao;


import lombok.extern.slf4j.Slf4j;
import org.iesvdm.ventas.modelo.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Slf4j
//Un Repository es un componente y a su vez un estereotipo de Spring
//que forma parte de la 'capa de persistencia'.
@Repository
public class ClienteDaoImpl implements ClienteDAO{

    @Autowired
    ClienteDAO clienteDAO;

    @Autowired
    private JdbcTemplate JdbcTemplate;

    @Override
    public void create(Cliente cliente) {

        String sql = """
                insert into cliente (nombre, apellido1, apellido2, ciudad, categoria)
                values (                    ?,      ?,      ?,      ?,      ?);
                """;
        String[] ids = {"id"};

        KeyHolder KeyHolder = new GeneratedKeyHolder();

        JdbcTemplate.update(con -> {

            PreparedStatement ps = con.prepareStatement(sql,ids);

            ps.setString(1,cliente.getNombre());
            ps.setString(2,cliente.getApellido1());
            ps.setString(3,cliente.getApellido2());
            ps.setString(4,cliente.getCiudad());
            ps.setInt(5,cliente.getCategoria());

            return ps;

        }, KeyHolder);

        cliente.setId(KeyHolder.getKey().intValue());
    }

    @Override
    public List<Cliente> getAll() {
        List<Cliente> query = JdbcTemplate.query("""
                        select * from cliente c """,
                (rs, rowNum )  -> new Cliente(
                        rs.getInt("id"),
                        rs.getString("apellido1"),
                        rs.getString("apellido2"),
                        rs.getString("ciudad"),
                        rs.getString("categoria")
                ));
        log.info("Devueltos {} clientes", listClientes.size);
        return listClientes;
    }

    @Override
    public Optional<Cliente> find(int id) {
        try {
            Cliente cliente = JdbcTemplate.queryForObject("""
                
                        select * from cliente
                                            where id = ? 
                """,
                (rs, rowNum) -> Cliente.builder()
                        .id(rs.getInt("id"))
                        .nombre(rs.getString("nombre"))
                        .apellido1(rs.getString("apellido1"))
                        .apellido2(rs.getString("apellido2"))
                        .ciudad(rs.getString("ciudad"))
                        .categoria(rs.getInt("categoria"))
                        .build
            (),id
        );

    return Optional.of(cliente);

    }catch (EmptyResultDataAccessException e){
            return Optional.empty();
    }
 }
    @Override
    public void update(Cliente cliente) {

        int rowsUpdated = JdbcTemplate.update("""
            UPDATE cliente 
            SET nombre = ?, apellido1 = ?, apellido2 = ?, ciudad = ?, categoria = ?
            WHERE id = ?
        """, cliente.getNombre()
        , cliente.getApellido1()
        , cliente.getApellido2()
        , cliente.getCiudad()
        , cliente.getCategoria()
        , cliente.getId());

        log.debug("Filas actualizadas {}", rowsUpdated);
    }

    @Override
    public void delete(int id) {

        var rowsDeleted = JdbcTemplate.update("""
            DELETE FROM cliente
            WHERE id = ?
        """, id);

        log.debug("Filas eliminadas {}", rowsDeleted);
    }
}
