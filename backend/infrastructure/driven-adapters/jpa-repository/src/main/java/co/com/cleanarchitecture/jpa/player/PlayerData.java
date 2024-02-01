package co.com.cleanarchitecture.jpa.player;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "players")
public class PlayerData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 155, nullable = false)
    private String name;

    @Column(length = 45, nullable = false)
    private String position;

    @Column(length = 12, nullable = false, unique = true)
    private String cellphone;

    @Column(length = 120)
    private String photo;

}
