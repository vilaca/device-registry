GRANT ALL PRIVILEGES ON DATABASE postgres TO postgres;

CREATE TABLE device (
    id      SERIAL PRIMARY KEY,
    name    TEXT NOT NULL,
    brand   TEXT NOT NULL,
    created TIMESTAMP NOT NULL
)