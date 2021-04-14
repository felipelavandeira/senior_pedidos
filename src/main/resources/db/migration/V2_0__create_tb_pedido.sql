CREATE TABLE IF NOT EXISTS tb_pedido
(
    id_pedido SERIAL PRIMARY KEY,
    status    VARCHAR(10) NOT NULL,
    desconto  NUMERIC(2, 2) DEFAULT 0
);