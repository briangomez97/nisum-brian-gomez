package co.com.nisum.builder;

import co.com.nisum.model.dto.PhoneDTO;

public class PhoneDTODataBuilder {

    private String id;
    private String number;
    private String cityCode;
    private String countryCode;

    public PhoneDTODataBuilder() {
        this.number = "3133333333";
        this.cityCode = "1";
        this.countryCode = "57";
    }

    public PhoneDTODataBuilder withIdField(String id) {
        this.id = id;
        return this;
    }

    public PhoneDTODataBuilder withIdField() {
        this.id = "14124123124312414";
        return this;
    }

    public PhoneDTO build() {
        return new PhoneDTO(id, number, cityCode,countryCode);
    }

}
