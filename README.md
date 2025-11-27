# 🗳️ StemSlim - Dutch Election Results Platform

StemSlim is a web application for visualizing Dutch election results. The application processes official election data from EML-XML files and presents them in a clear, user-friendly interface. Users can view voting results at national, constituency, and municipality level, compare party and candidate performance, and engage in discussions about the outcomes. The platform supports multiple election years, allowing users to explore historical data and track political trends over time.

## 📑 Table of Contents

- [API Endpoint Overview](#api-endpoint-overview)
    - [Candidates](#candidates)
    - [Parties](#parties)
    - [Elections](#elections)
    - [Constituencies](#constituencies)
    - [Municipalities](#municipalities)
    - [Discussions](#discussions)
    - [Users](#users)

## API Endpoint Overview

Full API documentation is available via Swagger UI:

🔗 **[Swagger UI - API Documentation](http://localhost:8080/api/swagger-ui/index.html#/)**

### Candidates

Endpoints for retrieving candidate information and the top 20 most voted candidates per election.

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/candidates` | Get all candidates |
| `GET` | `/candidates/{id}` | Get candidate by ID |
| `GET` | `/candidates/top?electionId={id}` | Get top 20 candidates per election |

### Parties

Endpoints for retrieving the top 20 parties based on national vote count.

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/parties/top?electionId={id}` | Get top 20 parties nationally |

### Elections

Endpoints for retrieving and importing election data. Data is imported from EML-XML files.

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/elections/{electionId}` | Get election results |
| `POST` | `/elections/{electionId}?folderName={folder}` | Import election data from XML |
| `GET` | `/elections/{electionId}/total-votes` | Get total vote count |

### Constituencies

Endpoints for retrieving election results per constituency, including vote percentages and totals.

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/elections/{electionId}/{constituencyName}` | Get party votes per constituency |
| `GET` | `/elections/{electionId}/{constituencyName}/votes-percentage` | Get vote percentage per constituency |
| `GET` | `/elections/{electionId}/{constituencyName}/totalVotes` | Get total votes per constituency |

### Municipalities

Endpoints for retrieving election results per municipality.

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/elections/{electionId}/municipalities/{municipalityName}` | Get party votes per municipality |

### Discussions

Endpoints for the discussion platform. Users can create, view, edit, and delete discussions. A JWT token is required to create a discussion.

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/discussion` | Get all discussions |
| `POST` | `/discussion` | Create new discussion (JWT required) |
| `PUT` | `/discussion/{discussionId}` | Update discussion |
| `DELETE` | `/discussion/{discussionId}` | Delete discussion |

### Users

Endpoints for user management and authentication. Passwords are hashed using Argon2. A JWT token is returned upon successful login.

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/user/{userId}` | Get user by ID |
| `POST` | `/user` | Register new user |
| `POST` | `/user/verify` | Login user (returns JWT token) |