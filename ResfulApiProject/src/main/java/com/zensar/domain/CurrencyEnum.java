package src.main.java.com.zensar.domain;

public enum CurrencyEnum {
	GBP("£"), USD("$"), EUR("€");
	
	private String value;

    public String getResponse() {
        return value;
    }

    CurrencyEnum(String value){
        this.value = value;
    }
}
