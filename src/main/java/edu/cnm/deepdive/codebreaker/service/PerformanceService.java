package edu.cnm.deepdive.codebreaker.service;

import edu.cnm.deepdive.codebreaker.model.dao.GamePerformanceRepository;
import edu.cnm.deepdive.codebreaker.model.view.GamePerformance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PerformanceService implements AbstractPerformanceService {

  private final GamePerformanceRepository repository;

  @Autowired
  public PerformanceService(GamePerformanceRepository repository) {
    this.repository = repository;
  }

  @Override
  public Iterable<GamePerformance> getGameRankingsByDuration(int poolSize, int codeLength, int count) {
    Pageable pageable = PageRequest.of(0, count,
        Sort.by("duration").ascending().and(Sort.by("guess_count").ascending())) ;

    return repository.findAllByPoolSizeAndLength(poolSize, codeLength, pageable);
  }

  @Override
  public Iterable<GamePerformance> getGameRankingsByGuessCount(int poolSize, int codeLength, int count) {
    Pageable pageable = PageRequest.of(0, count,
        Sort.by("guess_count").ascending().and(Sort.by("duration").ascending())) ;

    return repository.findAllByPoolSizeAndLength(poolSize, codeLength, pageable);
  }
}
