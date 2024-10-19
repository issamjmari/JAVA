all: up

up: 
	@mkdir -p target
	@javac -d target src/fr/42/printer/app/MainApp.java src/fr/42/printer/logic/ImageConverter.java

down:
	@docker-compose -f ./srcs/docker-compose.yml down

status:
	@docker ps