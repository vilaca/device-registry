# device-registry
REST api to keep track of devices.

## Required dependencies

All required dependecies are managed by gradle or docker (compose).

Only a recent version of docker is required. docker 20.10.8 was used during development.

To run use `docker compose up -d`.

To stop: `docker compose down`.

## Service

### Examples

#### Insert a new device
`
curl --location --request POST 'http://localhost:8080/devices' --header 'Content-Type: application/json' --data-raw '{"name": "name1","brand": "brand1"}'
`
## Persistence

Persistence uses PostgreSQL.

The file `init_db.sql` configures the database on the first time PostgreSQL runs.

This method of configuring the database is optimal for a small project.

_WARNING_: The postgreSQL configuration is not ready for production as `POSTGRES_HOST_AUTH_METHOD: "trust"` is set on `docker-compose.yaml`.
