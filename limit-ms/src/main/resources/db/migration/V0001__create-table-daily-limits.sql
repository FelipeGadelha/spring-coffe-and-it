CREATE TABLE daily_limits (
    id SERIAL NOT NULL,
    agency VARCHAR(10) NOT NULL,
    account VARCHAR(10) NOT NULL,
    date DATE NOT NULL,
    value NUMERIC(10,2) NOT NULL,

    PRIMARY KEY (id)
);