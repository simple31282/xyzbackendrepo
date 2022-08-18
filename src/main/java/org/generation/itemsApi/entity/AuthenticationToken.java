package org.generation.itemsApi.entity;


import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="tokens")
public class AuthenticationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String Token;
    @Column(name="created_date")
    private Date createdDate;

    public AuthenticationToken() {
    }

    @OneToOne(targetEntity = User.class,fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name="user_id")
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AuthenticationToken(User user) {
        this.user = user;
        this.createdDate = new Date();
        this.Token = UUID.randomUUID().toString();
    }
}
