CREATE TABLE entradas (
    id UUID PRIMARY KEY,
    id_produto UUID NOT NULL,
    quantidade INT NOT NULL,
    data DATE NOT NULL DEFAULT CURRENT_DATE,

    FOREIGN KEY(id_produto) REFERENCES produtos(id)
);