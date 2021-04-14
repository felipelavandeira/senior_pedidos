CREATE TABLE IF NOT EXISTS tb_pedido_item
(
    id_pedido_item SERIAL PRIMARY KEY,
    id_pedido INTEGER,
    id_item INTEGER,
    CONSTRAINT pedido_p_item FOREIGN KEY(id_pedido) REFERENCES tb_pedido(id_pedido),
    CONSTRAINT item_p_pedido FOREIGN KEY (id_item) REFERENCES tb_item_pedido(id_item)
);