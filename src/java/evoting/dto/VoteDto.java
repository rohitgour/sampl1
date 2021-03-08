
package evoting.dto;

public class VoteDto {
    private String candidateId;
    private String userId;

    public VoteDto(String candidateId, String userId) {
        this.candidateId = candidateId;
        this.userId = userId;
    }

    public VoteDto() {
    }

    
    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
}
