package com.atmosware.soundwave.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "albums")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private int numberOfSong;
    private LocalDate releaseDate;
    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;
    @OneToMany(mappedBy = "album")
    private List<Song> songs;

}
