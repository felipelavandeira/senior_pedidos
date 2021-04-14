DO
$$
    DECLARE
        n       INTEGER := 10;
        counter INTEGER := 0;
    BEGIN
        LOOP
            EXIT WHEN counter = n;
            counter := counter + 1;
            INSERT INTO tb_item_pedido (id_item, nome, preco, st_ativo, tp_item)
            VALUES (NEXTVAL('tb_item_pedido_id_item_seq'::REGCLASS),
                    CONCAT('Produto Teste', TO_CHAR(counter, '00')),
                    random() * 10000,
                    TRUE,
                    'P');
            INSERT INTO tb_item_pedido (id_item, nome, preco, st_ativo, tp_item)
            VALUES (NEXTVAL('tb_item_pedido_id_item_seq'::REGCLASS),
                    CONCAT('Serviço Teste', TO_CHAR(counter, '00')),
                    random() * 10000,
                    TRUE,
                    'S');
        END LOOP;
    END
$$;