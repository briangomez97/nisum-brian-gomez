package co.com.nisum.builder;

import co.com.nisum.model.dto.PhoneDTO;
import co.com.nisum.model.dto.UserDTO;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserDTODataBuilder {

    private String id;
    private String name;
    private String email;
    private String password;
    private List<PhoneDTO> phones;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private String token;
    private Boolean isActive;

    public UserDTODataBuilder() {
        this.name = "Paolo Guerrero";
        this.email = "paolo@mail.com";
        this.password = "Paolo2022*";
        this.phones = new ArrayList<>();
        phones.add(new PhoneDTODataBuilder().withIdField("1").build());
        phones.add(new PhoneDTODataBuilder().withIdField("2").build());
        phones.add(new PhoneDTODataBuilder().withIdField("3").build());
    }

    public UserDTODataBuilder withIdField() {
        this.id = "14124123124312414";
        return this;
    }

    public UserDTODataBuilder withIdField(String id) {
        this.id = id;
        return this;
    }

    public UserDTODataBuilder withEmailField(String email) {
        this.email = email;
        return this;
    }

    public UserDTODataBuilder withCreatedField() {
        this.created = LocalDateTime.now();
        return this;
    }

    public UserDTODataBuilder withModifiedField() {
        this.modified = LocalDateTime.now();
        return this;
    }

    public UserDTODataBuilder withLastLoginField() {
        this.lastLogin = LocalDateTime.now();
        return this;
    }

    public UserDTODataBuilder withTokenField() {
        this.token = "heretoken";
        return this;
    }

    public UserDTODataBuilder withIsActiveField() {
        this.isActive = Boolean.TRUE;
        return this;
    }

    public UserDTO build() {
        return UserDTO.builder()
                .id(this.id)
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .phones(this.phones)
                .created(this.created)
                .modified(this.modified)
                .lastLogin(this.lastLogin)
                .token(this.token)
                .isActive(this.isActive)
                .build();
    }

}
