package co.com.nisum.builder;

import co.com.nisum.model.dto.RegularExpressionDTO;
import co.com.nisum.util.enums.RegexEnum;

import java.time.LocalDateTime;

public class RegularExpressionDTODataBuilder {

    private String id;
    private String name;
    private String regularExpression;
    private LocalDateTime lastUpdate;

    public RegularExpressionDTODataBuilder() {
        this.name = RegexEnum.PASSWORD_REGEX.getValue();
        this.regularExpression = "Hola!";
        this.lastUpdate = LocalDateTime.now();
    }

    public RegularExpressionDTODataBuilder withIdField(String id) {
        this.id = id;
        return this;
    }

    public RegularExpressionDTODataBuilder withIdField() {
        this.id = "14124123124312414";
        return this;
    }

    public RegularExpressionDTO build() {
        return new RegularExpressionDTO(id, name, regularExpression, lastUpdate);
    }

}
