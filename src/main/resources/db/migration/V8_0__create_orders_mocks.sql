DO
$$
    DECLARE
        n       INTEGER := 10;
        counter INTEGER := 0;
    BEGIN
        LOOP
            EXIT WHEN counter = n;
            counter := counter + 1;
            INSERT INTO tb_pedido (id_pedido, status, desconto, total)
            VALUES (NEXTVAL('tb_pedido_id_pedido_seq'::REGCLASS),
                    'OPENED',
                    RANDOM() * 1000,
                    RANDOM() * 10000);
        END LOOP;
    END
$$;