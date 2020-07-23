package app.platform.userservice.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="permissions")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "permissions", targetEntity = Role.class)
    private List<Role> roles;
}
