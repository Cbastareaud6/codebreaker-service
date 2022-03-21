package edu.cnm.deepdive.codebreaker.service;

import edu.cnm.deepdive.codebreaker.model.view.GamePerformance;
import edu.cnm.deepdive.codebreaker.model.view.UserPerformance;
import org.springframework.stereotype.Service;


@Service
public interface AbstractPerformanceService {

  Iterable<GamePerformance> getGameRankingsByDuration(int poolSize, int codeLength, int count);

  Iterable<GamePerformance> getGameRankingsByGuessCount(int poolSize, int codeLength, int count);

  Iterable<UserPerformance> getUserRankingByDuration(int poolSize, int codeLength, int minGamesCompleted, int count);

  Iterable<UserPerformance> getUserRankingByGuessCount(int poolSize, int codeLength, int minGamesCompleted, int count);

}
