# Backend – Running with Docker

This backend is a **Spring Boot application** that runs inside a **Docker container** and uses an **H2 file-based database**.  
The database is mounted as a volume so data is persisted between container restarts.

---

## Requirements

Make sure you have installed:

- Docker Desktop
- Git

---

## Build the backend image

From the **backend directory**, run:

````bash
docker build -t backend .

````

## running the container 

From the **backend directory**, run: 
```bash
docker run --name election-backend -p 8080:8080 -v <ABSOLUTE_PATH_TO_DATA_FOLDER>:/data  backend

```

**NOTE!!** : 

in  <ABSOLUTE_PATH_TO_DATA_FOLDER> put your path that leads to the data folder in your project

**Example**: 

```bash
docker run --name election-backend -p 8080:8080 -v "C:\Documents\Hva projecten\SM3\fuuyeenaatee72\data:/data" backend
```

**The data folder should have an electiondb.mv.db file**