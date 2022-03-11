package edu.cnm.deepdive.codebreaker.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Immutable
@Subselect("SELECT\n"
    + "    game_id,\n"
    + "    DATEDIFF('SECOND', MIN(created), MAX(created)) AS duration,\n"
    + "       COUNT (*)                                 AS guess_count,\n"
    + "       MAX(exact_matches) AS matches\n"
    + "FROM guess\n"
    + "GROUP BY\n"
    + "         game_id")

public class GamePerformance {

  @Id
  @Column(name = "game_id")
  private UUID id;

  private int duration;

  @Column(name = "guess_count")
  private int guessCount;

  private int matches;

}
