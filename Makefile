#makefile
APPLICATION_NAME := petshop-admin-api
PORT := 5002

docker-compose-up: docker-compose-down
	docker-compose rm -f -v postgres
	docker-compose up

docker-compose-down:
	docker-compose down -v

docker-compose-dev-up: # docker-compose-down
	docker-compose -f docker-compose-dev.yaml down -v
	docker-compose -f docker-compose-dev.yaml rm -f -v postgres
	docker-compose -f docker-compose-dev.yaml up


