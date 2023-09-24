CREATE TABLE business (
    id SERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    cnpj VARCHAR(20) NOT NULL UNIQUE,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE taxes (
    id SERIAL PRIMARY KEY,
    taxes_value VARCHAR(255) NOT NULL,
    value DOUBLE PRECISION NOT NULL,
    business_id INT REFERENCES business(id)
);

