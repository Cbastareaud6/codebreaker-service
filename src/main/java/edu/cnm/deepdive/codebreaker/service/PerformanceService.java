package edu.cnm.deepdive.codebreaker.service;

import edu.cnm.deepdive.codebreaker.model.dao.GamePerformanceRepository;
import edu.cnm.deepdive.codebreaker.model.dao.UserPerformanceRepository;
import edu.cnm.deepdive.codebreaker.model.view.GamePerformance;
import edu.cnm.deepdive.codebreaker.model.view.UserPerformance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PerformanceService implements AbstractPerformanceService {

  private final GamePerformanceRepository gamePerformanceRepository;
  private final UserPerformanceRepository userPerformanceRepository;

  @Autowired
  public PerformanceService(GamePerformanceRepository repository,
      UserPerformanceRepository userPerformanceRepository) {
    this.gamePerformanceRepository = repository;
    this.userPerformanceRepository = userPerformanceRepository;
  }

  @Override
  public Iterable<GamePerformance> getGameRankingsByDuration(int poolSize, int codeLength, int count) {
    Pageable pageable = PageRequest.of(0, count,
        Sort.by("duration").ascending().and(Sort.by("guessCount").ascending())) ;

    return gamePerformanceRepository.findAllByPoolSizeAndLength(poolSize, codeLength, pageable);
  }

  @Override
  public Iterable<GamePerformance> getGameRankingsByGuessCount(int poolSize, int codeLength, int count) {
    Pageable pageable = PageRequest.of(0, count,
        Sort.by("guessCount").ascending().and(Sort.by("duration").ascending())) ;

    return gamePerformanceRepository.findAllByPoolSizeAndLength(poolSize, codeLength, pageable);
  }

  @Override
  public Iterable<UserPerformance> getUserRankingByDuration(int poolSize, int codeLength,
      int minGamesCompleted, int count) {    Pageable pageable = PageRequest.of(0, count,
      Sort.by("averageDuration").ascending().and(Sort.by("averageGuessCount").ascending())) ;


    return userPerformanceRepository.findAllByPoolSizeAndLengthAndGamesCompletedGreaterThanEqual(poolSize, codeLength, minGamesCompleted, pageable);
  }

  @Override
  public Iterable<UserPerformance> getUserRankingByGuessCount(int poolSize, int codeLength,
      int minGamesCompleted, int count) {
    Pageable pageable = PageRequest.of(0, count,
        Sort.by("averageGuessCount").ascending().and(Sort.by("averageDuration").ascending())) ;

    return userPerformanceRepository.findAllByPoolSizeAndLengthAndGamesCompletedGreaterThanEqual(poolSize, codeLength, minGamesCompleted,pageable);
  }
}
