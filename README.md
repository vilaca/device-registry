# device-registry

REST API to keep track of devices.

## Required dependencies

All required dependecies are managed by gradle or docker (compose).

Only a recent version of docker is required. docker 20.10.8 was used during development.

To run use `docker compose up -d --build`. To stop: `docker compose down`.

## Service

The service is a Spring boot reactive application using Webflux and a PostgreSQL reactive driver.

The server is listening on port _8080_ and the base path is _/devices_.

| Method | Path                      | Description                     |
|:-------|:--------------------------|:--------------------------------|
| GET    | /devices                  | List all devices.               |
| POST   | /devices                  | Insert device                   |
| GET    | /devices?brand=brand_name | Search devices by [brand]       |
| GET    | /devices/{id}             | Get device by [id].             |
| PUT    | /devices/{id}             | Update device with [id]         |
| PATCH  | /devices/{id}             | Partial update device with [id] |
| DELETE | /devices/{id}             | Delete device with [id]         |

[> See the complete API definition in Swagger UI](http://localhost:8080/swagger-ui.html)

### Sample requests

#### Insert a new _device_

`
curl --location --request POST 'http://localhost:8080/devices' --header 'Content-Type: application/json' --data-raw '{"name": "name1","brand": "brand1"}'
`

#### Retrieve a _device_ with the ID 1

`
curl -i --location --request GET 'http://localhost:8080/devices/1' --header 'Content-Type: application/json'
`

#### Update a _device_ with the ID 1

`
curl -i --location --request PUT 'http://localhost:8080/devices/1' --header 'Content-Type: application/json' --data-raw '{"name": "name1", "brand": "brand1"}'
`

#### Update the name of a _device_ with the ID 1

`
curl -i --location --request PATCH 'http://localhost:8080/devices/1' --header 'Content-Type: application/json' --data-raw '{"name": "name2"}'
`

#### Update the brand of a _device_ with the ID 1

`
curl -i --location --request PATCH 'http://localhost:8080/devices/1' --header 'Content-Type: application/json' --data-raw '{"brand": "brand2"}'
`

#### Delete a _device_ with the ID 1

`
curl -i --location --request DELETE 'http://localhost:8080/devices/1' --header 'Content-Type: application/json'
`

#### List all _devices_

`
curl -i --location --request GET 'http://localhost:8080/devices' --header 'Content-Type: application/json'
`

#### Search _device_ by _brand_

`
curl -i --location --request GET 'http://localhost:8080/devices?brand=brand_name' --header 'Content-Type: application/json'
`

## Persistence

Persistence uses PostgreSQL.

The file `init_db.sql` configures the database on the first time PostgreSQL runs.

This method of configuring the database is optimal for a small project.

_WARNING_: The postgreSQL configuration is not ready for production as `POSTGRES_HOST_AUTH_METHOD: "trust"` is set
on `docker-compose.yaml`.
