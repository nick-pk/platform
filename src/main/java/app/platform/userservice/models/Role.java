package app.platform.userservice.models;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "roles_permissions",
        joinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "permission_id",referencedColumnName = "id"))
    private List<Permission> permissions;

    @ManyToMany(mappedBy = "roles", targetEntity = User.class)
    private List<User> users;
}
