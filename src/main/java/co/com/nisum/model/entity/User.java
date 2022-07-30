package co.com.nisum.model.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Setter
    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy="user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Phone> phones;

    @CreationTimestamp
    @Column(name = "created")
    private LocalDateTime created;

    @Setter
    @Column(name = "modified")
    private LocalDateTime modified;

    @Setter
    @CreationTimestamp
    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @Setter
    @Column(name = "token")
    private String token;

    @Column(name = "is_active")
    private Boolean isActive;
}
