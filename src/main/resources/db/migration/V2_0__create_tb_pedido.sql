CREATE SEQUENCE IF NOT EXISTS tb_pedido_id_pedido_seq
    INCREMENT 1
    MINVALUE 1
    START 1;


CREATE TABLE IF NOT EXISTS tb_pedido
(
    id_pedido SERIAL PRIMARY KEY,
    status    VARCHAR(10) NOT NULL,
    desconto  NUMERIC(2, 2) DEFAULT 0
);