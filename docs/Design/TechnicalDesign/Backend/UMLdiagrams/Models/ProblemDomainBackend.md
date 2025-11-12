# Backend Domain Model

![Mermaid Chart - ProblemDIagram Backend.-2025-10-17-143849.png](Mermaid%20Chart%20-%20ProblemDIagram%20Backend.-2025-10-17-143849.png)


## Core Election Entities

**Election** - Root entity representing a specific election (e.g., TK2023)
- Contains parties and constituencies
- Central coordination point for all election data

**Party** - Political parties participating in the election
- Has candidates and tracks total votes
- Appears across multiple constituencies

**Candidate** - Individual politicians under a party
- Personal vote totals that contribute to party totals

**Constituency & ConstituencyResult** - Geographic voting regions and their results
- Shows vote breakdown per party/candidate per region

## User Features

**User & Discussion** - Community system for election discussions
- Users can create discussions about elections
- Discussions can be linked to specific elections
- Built-in reply system for engagement

## Key Design

- Election is the main entry point
- Vote data flows from XML files → Domain entities → Database
- Clean separation between election data and user discussions