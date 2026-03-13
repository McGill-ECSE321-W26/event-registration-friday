package ca.mcgill.ecse321.eventregistration.dto;

public class ErrorDto {
    private String error;

    // Needed by Jackson to deserialize JSON
    @SuppressWarnings("unused")
    public ErrorDto() {
    }

    public ErrorDto(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
