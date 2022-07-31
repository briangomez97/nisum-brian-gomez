package co.com.nisum.builder;

import co.com.nisum.model.dto.PhoneDTO;
import org.apache.commons.lang3.StringUtils;

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
        if(!StringUtils.isEmpty(id)) {
            this.id = id;
        } else {
            this.id = "14124123124312414";
        }
        return this;
    }

    public PhoneDTO build() {
        return PhoneDTO.builder()
                .id(this.id)
                .number(this.number)
                .cityCode(this.cityCode)
                .countryCode(this.countryCode)
                .build();
    }

}
