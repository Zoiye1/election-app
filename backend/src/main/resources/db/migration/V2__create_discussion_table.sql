CREATE TABLE discussion
(
                            id SERIAL PRIMARY KEY,
                            title VARCHAR(255) NOT NULL,
                            content TEXT NOT NULL,
                            user_id INTEGER NOT NULL REFERENCES "user"(id),
                            created_at TIMESTAMP NOT NULL,
                            views INTEGER NOT NULL DEFAULT 0,
                            replies INTEGER NOT NULL DEFAULT 0
);