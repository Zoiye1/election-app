package nl.hva.stack5.election.dto;

/**
 * DTO for creating or updating a Discussion
 * Used for POST and PUT requests
 */
public class DiscussionRequestDTO {
    private String title;
    private String content;
    private Integer authorId;

    public DiscussionRequestDTO(String title, String content, Integer authorId) {
        this.title = title;
        this.content = content;
        this.authorId = authorId;
    }

    public DiscussionRequestDTO() {
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    /**
     * Check if the DTO is valid
     * @return true if valid, false otherwise
     */
    public boolean isValid() {
        return title != null && !title.trim().isEmpty() && title.length() >= 5 &&
                content != null && !content.trim().isEmpty() && content.length() >= 10 &&
                authorId != null;
    }

    /**
     * Get validation error message
     * @return error message, or null if valid
     */
    public String getValidationError() {
        if (title == null || title.trim().isEmpty()) {
            return "Title is required";
        }
        if (title.length() < 5) {
            return "Title must be at least 5 characters";
        }
        if (content == null || content.trim().isEmpty()) {
            return "Content is required";
        }
        if (content.length() < 10) {
            return "Content must be at least 10 characters";
        }
        if (authorId == null) {
            return "Author ID is required";
        }
        return null;
    }
}
