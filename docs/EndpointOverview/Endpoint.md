# API endpoint overview

## System Architecture Overview

This project allows users to view Dutch election results from various years. 
Users can also discuss these with other users.

### Architectuur Diagram

```
[Frontend (TS,VUE.JS)] <-- HTTP/HTTPS --> [Spring Boot Rest API] <-- --> [Database]
                                            
                                         
```

## Technical Components

### Frontend Infrastructuur
- **Technology**: Vanilla TypeScript + Vue.js + tailwind Css
- **Location**: `/frontend/src/`
- **Components** (`/components/`): Reusable UI elements in Vue.js
- **Services** (`/services/`): API communication to the backend
- **Assets** (`/assets/`):  Static files like images and stylesheets
- **Views** (`/views/`): Different pages of the application
- **Router** (`/router/`): Manages navigation between views
- **Stores** (`/stores/`): Vuex store for managing application state
- **Interfaces** (`/interfaces/`): TypeScript interfaces for type definitions
- **Composables** (`/composables/`): Reusable logic using Vue 3 Composition API

### Backend Infrastructuur

- **Technology**: Java + Spring Boot + Rest API
- **Location**: `/backend/src/main/java`
- **Config** (`/config/`): Configuration classes for Spring Boot
- **Controllers** (`/controllers/`): Handle incoming HTTP requests and route them to services
- **Services** (`/services/`): Business logic and interaction with repositories
- **Repositories** (`/repositories/`): Data access layer, interacting with the database
- **Models** (`/models/`): Java classes representing database entities
- **DTOs** (`/dtos/`): Data Transfer Objects for transferring data between
- **Utils** (`/utils/`): Utility classes and helper functions