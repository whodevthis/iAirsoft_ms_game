🪖 iAirsoft API

Backend for immersive airsoft/milsim game management with voice-controlled virtual HQ.


About
GateWay API backend for comprehensive airsoft and milsim (military simulation) game management. It handles pre-game organization — matches, teams, players and locations — and during the game acts as a voice-controlled virtual HQ, giving the command team real-time visibility over the game state.
The frontend (mobile app with voice interface) is distributed as an independent APK and consumes this API.

Architecture
The project follows Hexagonal Architecture (Ports & Adapters) combined with Domain-Driven Design (DDD):
src/main/java/
├── Domain/               # Aggregates, entities, value objects, domain services
│   └── aggregates/
├── Application/          # Use cases, input and output ports
└── Infrastructure/       # REST adapters, JPA repositories, Spring configuration

Tech stack
LayerTechnologyLanguageJava 21FrameworkSpring Boot 3SecuritySpring SecurityPersistenceJPA / HibernateDatabaseMySQLDeploymentDocker / Docker ComposeBuildMaven

Features

🎮 Full game lifecycle management
👥 Team, player and location management
🎙️ Voice-controlled virtual HQ during live games
⚙️ Team capacity control, bleeding and healing timers
🔐 Authentication and role-based access control


Requirements

Java 21+
Docker & Docker Compose
Maven 3.8+


Related repositories
ComponentRepositoryAPI (this repo)iAirsoft_APIMobile app (APK)Coming soon

Status
🚧 Active development. Currently implementing the domain layer: Game, Team and Player aggregates.

Author
Miguel Ángel Merchán Recio
whodevthis.com · miguelangelmerchan@whodevthis.com
