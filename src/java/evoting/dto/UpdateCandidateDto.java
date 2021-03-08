
package evoting.dto;

import java.io.InputStream;

public class UpdateCandidateDto {
    private String party;
    private String city;
    private String userId;
    private InputStream symbol;
    private boolean flag;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public UpdateCandidateDto() {
    }

    public UpdateCandidateDto(String party, String city, String userId, InputStream symbol, boolean flag) {
        this.party = party;
        this.city = city;
        this.userId = userId;
        this.symbol = symbol;
        this.flag = flag;
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

    @Override
    public String toString() {
        return "UpdateCandidateDto{" + "party=" + party + ", city=" + city + ", userId=" + userId + ", symbol=" + symbol + ", flag=" + flag + '}';
    }
    
  
}