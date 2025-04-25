CREATE TABLE vendas (
    id UUID PRIMARY KEY,
    id_funcionario UUID NOT NULL,
    pagamento VARCHAR(255) NOT NULL,
    data DATE NOT NULL DEFAULT CURRENT_DATE,

    FOREIGN KEY (id_funcionario) REFERENCES funcionarios(id)
);