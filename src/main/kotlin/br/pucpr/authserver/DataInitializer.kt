package br.pucpr.authserver

import org.springframework.boot.CommandLineRunner
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component

@Component
class DataInitializer(val jdbcTemplate: JdbcTemplate) : CommandLineRunner {
    override fun run(vararg args: String?) {
        jdbcTemplate.execute("insert into tbl_users (email, name, password, role, id) values ('gui@gmail.com', 'Guilherme', '1234', 'ADM', 1);")
        jdbcTemplate.execute("INSERT INTO TBL_COD_GRU_EST (codigo, descricao) VALUES (1, 'Bebidas');")
        jdbcTemplate.execute("INSERT INTO TBL_COD_GRU_EST (codigo, descricao) VALUES (2, 'Comidas')")
        jdbcTemplate.execute("INSERT INTO TBL_COD_GRU_EST (codigo, descricao) VALUES (3, 'Sobremesas')")
        jdbcTemplate.execute("INSERT INTO  tbl_produto (id, codigo_produto, descricao, preco, cod_gru_est) VALUES (1, '1', 'Coca-cola', 6.5, 1);")
        jdbcTemplate.execute("INSERT INTO  tbl_produto (id, codigo_produto, descricao, preco, cod_gru_est) VALUES (2, '2', 'x-bacon', 10.0, 2);")
        jdbcTemplate.execute("INSERT INTO  tbl_produto (id, codigo_produto, descricao, preco, cod_gru_est) VALUES (3, '3', 'picolé laranja', 3.0, 3);")
        jdbcTemplate.execute("INSERT INTO  tbl_produto (id, codigo_produto, descricao, preco, cod_gru_est) VALUES (4, '20', 'x-egg', 12.5, 2);")
        jdbcTemplate.execute("INSERT INTO tbl_produto (id, codigo_produto, descricao, preco, cod_gru_est) VALUES (5, '4', 'Feijoada', 30.0, 2);")
        jdbcTemplate.execute("INSERT INTO tbl_produto (id, codigo_produto, descricao, preco, cod_gru_est) VALUES (6, '5', 'Pão de Queijo', 5.0, 2);")
        jdbcTemplate.execute("INSERT INTO tbl_produto (id, codigo_produto, descricao, preco, cod_gru_est) VALUES (7, '6', 'Coxinha', 7.0, 2);")
        jdbcTemplate.execute("INSERT INTO tbl_produto (id, codigo_produto, descricao, preco, cod_gru_est) VALUES (8, '7', 'Brigadeiro', 3.0, 3);")
        jdbcTemplate.execute("INSERT INTO tbl_produto (id, codigo_produto, descricao, preco, cod_gru_est) VALUES (9, '8', 'Açaí', 10.0, 3);")
        jdbcTemplate.execute("INSERT INTO tbl_produto (id, codigo_produto, descricao, preco, cod_gru_est) VALUES (10, '9', 'Pastel', 8.0, 2);")
        jdbcTemplate.execute("INSERT INTO tbl_produto (id, codigo_produto, descricao, preco, cod_gru_est) VALUES (11, '10', 'Churrasco', 50.0, 2);")
        jdbcTemplate.execute("INSERT INTO tbl_produto (id, codigo_produto, descricao, preco, cod_gru_est) VALUES (12, '11', 'Moqueca', 40.0, 2);")
        jdbcTemplate.execute("INSERT INTO tbl_produto (id, codigo_produto, descricao, preco, cod_gru_est) VALUES (13, '12', 'Picanha', 60.0, 2);")
        jdbcTemplate.execute("INSERT INTO tbl_produto (id, codigo_produto, descricao, preco, cod_gru_est) VALUES (14, '13', 'Tapioca', 5.0, 2);")
        jdbcTemplate.execute("INSERT INTO tbl_produto (id, codigo_produto, descricao, preco, cod_gru_est) VALUES (15, '14', 'Empada', 6.0, 2);")
        jdbcTemplate.execute("INSERT INTO tbl_produto (id, codigo_produto, descricao, preco, cod_gru_est) VALUES (16, '15', 'Cini Abacaxi', 4.0, 1);")
        jdbcTemplate.execute("INSERT INTO tbl_produto (id, codigo_produto, descricao, preco, cod_gru_est) VALUES (17, '16', 'Caipirinha', 12.0, 1);")
        jdbcTemplate.execute("INSERT INTO tbl_produto (id, codigo_produto, descricao, preco, cod_gru_est) VALUES (18, '17', 'Café', 3.0, 1);")
        jdbcTemplate.execute("INSERT INTO tbl_produto (id, codigo_produto, descricao, preco, cod_gru_est) VALUES (19, '18', 'Suco de Laranja', 5.0, 1);")
        jdbcTemplate.execute("INSERT INTO tbl_produto (id, codigo_produto, descricao, preco, cod_gru_est) VALUES (20, '19', 'Água de Coco', 6.0, 1);")
        jdbcTemplate.execute("INSERT INTO  tbl_produto (id, codigo_produto, descricao, preco, cod_gru_est) VALUES (21, '21', 'Guaraná', 5.0, 1);")



    }
}