Diet Analysis Service (mock)

This folder contains a minimal Spring Boot microservice that implements the
POST /api/analysis/photo endpoint. It currently returns a mock analysis JSON
matching the DTOs used by the input-service.

Run locally:

mvn -f analysis/pom.xml spring-boot:run

The service listens on port 8082 by default (see analysis/src/main/resources/application.properties).

Replace the mock logic in AnalysisController.analyzePhoto(...) with real model/AI calls when ready.
