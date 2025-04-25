CREATE TABLE venda_produto (
    id UUID PRIMARY KEY,
    id_venda UUID NOT NULL,
    id_produto UUID NOT NULL,
    quantidade INT NOT NULL,
    valor DECIMAl(10, 2) NOT NULL,

    FOREIGN KEY (id_venda) REFERENCES vendas(id),
    FOREIGN KEY (id_produto) REFERENCES  produtos(id)
)