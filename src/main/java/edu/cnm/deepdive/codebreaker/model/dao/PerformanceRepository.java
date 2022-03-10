package edu.cnm.deepdive.codebreaker.model.dao;

import edu.cnm.deepdive.codebreaker.model.entity.Game;
import edu.cnm.deepdive.codebreaker.view.Performance;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface PerformanceRepository extends Repository<Game, UUID> {

  @Query("SELECT ga.created, "
      + "COUNT(gu) AS guessCount,"
      + "MAX(gu.created)- MIN(gu.created) AS duration "
      + " FROM Game AS ga "
      + "JOIN ga.guesses AS gu "
      + " WHERE ga.poolSize = :poolSize AND ga.length = :codeLength " )
  Iterable<Performance> getRankings(int poolSize,int codeLength);
}
