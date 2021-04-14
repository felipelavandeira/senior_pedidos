CREATE TABLE IF NOT EXISTS tb_item_pedido
(
    id_item  SERIAL PRIMARY KEY,
    nome     VARCHAR(100) NOT NULL,
    preco    NUMERIC(3, 2) DEFAULT 0,
    st_ativo BOOLEAN,
    tp_item  CHAR(1)
);