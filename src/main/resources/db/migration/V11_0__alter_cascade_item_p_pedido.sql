ALTER TABLE IF EXISTS tb_pedido_item
    DROP CONSTRAINT item_p_pedido,
    ADD CONSTRAINT item_p_pedido FOREIGN KEY (id_item) REFERENCES tb_item_pedido (id_item) ON DELETE SET NULL;