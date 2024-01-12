#makefile
APPLICATION_NAME := petshop-admin-api
PORT := 5002
PROFILE := docker

docker-compose-up: docker-compose-down
	docker-compose rm -f -v postgres
	docker-compose up

docker-compose-down:
	docker-compose down -v

docker-compose-docker-up: # docker-compose-down
	docker-compose -f docker-compose-docker.yaml down -v
	docker-compose -f docker-compose-docker.yaml rm -f -v postgres
	docker-compose -f docker-compose-docker.yaml up

build-docker:
	mvn -version
	mvn clean
	mvn package
	docker build --build-arg SPRING_PROFILES_ACTIVE=$(PROFILE) -f Dockerfile -t $(APPLICATION_NAME):latest .
	docker build --build-arg SPRING_PROFILES_ACTIVE=$(PROFILE) -f Dockerfile -t $(APPLICATION_NAME):$(shell echo $(shell mvn help:evaluate -Dexpression=project.version -q -DforceStdout) | perl -pe 's/-SNAPSHOT//') .

run-build-docker:	build-docker
	docker run -e SPRING_PROFILE=$(PROFILE) -p $(PORT):$(PORT) -t $(APPLICATION_NAME):latest

run-docker:
	docker run -e SPRING_PROFILE=$(PROFILE) -p $(PORT):$(PORT) -t $(APPLICATION_NAME):latest
