
package evoting.dto;

import java.io.InputStream;

public class AddCandidateDto {
    private String candidateId;
    private String party;
    private String city;
    private String userId;
    private InputStream symbol;

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public InputStream getSymbol() {
        return symbol;
    }

    public void setSymbol(InputStream symbol) {
        this.symbol = symbol;
    }

    public AddCandidateDto(String candidateId, String party, String city, String userId, InputStream symbol) {
        this.candidateId = candidateId;
        this.party = party;
        this.city = city;
        this.userId = userId;
        this.symbol = symbol;
    }

    public AddCandidateDto() {
    }

    @Override
    public String toString() {
        return "AddCandidateDto{" + "candidateId=" + candidateId + ", party=" + party + ", city=" + city + ", userId=" + userId + ", symbol=" + symbol + '}';
    }
    
}
