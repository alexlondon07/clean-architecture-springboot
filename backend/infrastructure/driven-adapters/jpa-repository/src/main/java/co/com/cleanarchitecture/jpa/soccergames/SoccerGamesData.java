package co.com.cleanarchitecture.jpa.soccergames;

import co.com.cleanarchitecture.jpa.player.PlayerData;
import co.com.cleanarchitecture.jpa.role.RoleData;
import co.com.cleanarchitecture.model.player.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "soccer_games")
public class SoccerGamesData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 15, nullable = false)
    private Date date;

    @NotBlank
    @Column(length = 15)
    private String time;

    @NotBlank
    @Column(length = 30, nullable = false)
    private String field;

    @Column(name = "field_number", length = 3, nullable = false)
    private String fieldNumber;

    @NotNull
    @Column(nullable = false)
    private Double price;

    @Column(name = "player_number")
    private int playerNumber;

    @Column(length = 155, nullable = true)
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "soccer_games_players",
            joinColumns = @JoinColumn(name = "soccer_games_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id"))
    private Set<PlayerData> players = new HashSet<>();

    @Column(length = 20)
    private String status;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true, name = "updated_at",
            updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updatedAt;

    private boolean enable = true;

    @PrePersist
    public void prePersist() {
        createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }
}