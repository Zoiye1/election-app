## Implementation Model

This diagram represents the actual implementation of the domain model in our Java/Spring Boot application. It shows all entity classes with their fields, methods, and JPA relationships.

### Election Data

The `Election` entity serves as the central aggregate, linking all election-related data for a specific election year. Results are stored in dedicated entities:

- `CandidateResult` – national votes per candidate
- `PartyResult` – total votes per party
- `ConstituencyPartyVotes` – party votes per constituency
- `MunicipalityPartyVotes` – party votes per municipality

`Candidate` and `Party` entities can exist independently and are linked to elections through their respective result entities, allowing historical data to persist across multiple elections.

### Discussion Platform

Users can create discussions and replies. The `Tag` entity connects discussions to a specific election, enabling users to filter content by election year. Replies support threading through the `parentReply` self-reference.

![EntitySolutionDiagram.png](EntitySolutionDiagram.png)
---

**Contributors**  
This model was designed and implemented by:
- Batuhan Yumak
- Zoiye Van Leeuwen
- Shivanio Cooman
- Matisse Ben Addi