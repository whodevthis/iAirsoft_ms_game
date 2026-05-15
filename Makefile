.PHONY: build test package clean docker-build up up-build down down-volumes
restart ps logs app-logs eureka-logs gateway-logs db-shell prometheus grafana urls

build:
./mvnw clean compile

test:
./mvnw test

package:
./mvnw clean package -DskipTests

clean:
./mvnw clean

docker-build:
docker compose build

up:
docker compose up -d

up-build:
docker compose up -d --build

down:
docker compose down

down-volumes:
docker compose down -v

restart:
docker compose restart

ps:
docker compose ps

logs:
docker compose logs -f

app-logs:
docker compose logs -f msgame

eureka-logs:
docker compose logs -f eureka

gateway-logs:
docker compose logs -f gateway

db-shell:
docker compose exec mysql mysql -u ms_game -pms_game_password msgame

prometheus:
docker compose logs -f prometheus

grafana:
docker compose logs -f grafana

urls:
@echo "Eureka Server -> http://localhost:8761"
@echo "API Gateway -> http://localhost:8080"
@echo "msGame -> http://localhost:8081"
@echo "RabbitMQ -> http://localhost:15672 (myuser / secret)"
@echo "Prometheus -> http://localhost:9090"
@echo "Grafana -> http://localhost:3000 (admin / admin)"