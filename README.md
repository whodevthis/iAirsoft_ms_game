# msGame — iAirsoft

Game management microservice for the iAirsoft platform. Handles the full lifecycle of airsoft/milsim games: matches, teams, players, locations, objectives and roles.
🏗️ Architecture

Hexagonal Architecture (Ports & Adapters) · Domain-Driven Design (DDD) · CQRS

src/main/java/org/project/msgame/
│
├── 🟣 domain/
│   ├── aggregates/       # Game, Team, Player, Objective, Role, Location, Cammo, Geofence
│   ├── service/          # TeamDomainService, GameDomainService, ObjectiveDomainService
│   ├── states/           # GameStatus, ObjectiveState, RoleType
│   └── valueObjects/     # Marker, Respawn, PlayerRole, TeamRole, PlayerObjective, Address, GeoJson
│
├── 🔵 application/
│   ├── ports/
│   │   ├── in/           # Use case interfaces — command + query per aggregate
│   │   └── out/          # Repository port interfaces — one per aggregate
│   ├── services/         # CommandService + QueryService per aggregate
│   ├── dtos/             # Input/output DTOs per aggregate
│   ├── mappers/          # MapStruct mappers  (domain ↔ DTO)
│   └── utils/            # GenericUtils (search, applyIfChanged)
│
└── 🟤 infrastructure/
    ├── controller/       # REST controllers — one per aggregate
    ├── messagin/
    │   ├── config/       # RabbitMQConfig
    │   ├── consumer/     # PlayerCreatedConsumer
    │   └── dto/          # UserEventDTO
    └── persistence/
        ├── adapter/      # Repository port implementations
        ├── assemblers/   # GameAssembler
        ├── converters/   # JPA AttributeConverters (JSON columns)
        ├── entities/     # JPA entities
        ├── mapper/       # MapStruct mappers  (entity ↔ domain)
        ├── mirrorClasses/# JSON mirror classes for converters
        └── repository/   # Spring Data JPA repositories

📨 Messaging — msAuth → msGame
When a user registers in msAuth, a UserEvent is published to RabbitMQ.
msGame consumes it and automatically creates the player profile — no manual call needed.
msAuth
  └─▶  audit.exchange
            └─▶  [routing key: audit.createUser]
                        └─▶  player.creation.queue
                                    └─▶  PlayerCreatedConsumer
                                                └─▶  PlayerEntity  ✅

⚙️ Tech stack
LanguageJava 21
FrameworkSpring Boot 4.0.6
PersistenceJPA / Hibernate 7 + MySQL 9MessagingRabbitMQ 
Spring AMQP 4MappingMapStruct 1.5.5
SecuritySpring Security + OAuth2API
DocsSpringDoc OpenAPI / Swagger 
UIMetricsMicrometer + PrometheusInfrastructureDocker + Docker ComposeBuildMaven

*Part of the [iAirsoft](https://whodevthis.com) project · Miguel Ángel Merchán Recio*
