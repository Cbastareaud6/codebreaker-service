package edu.cnm.deepdive.codebreaker.controller;

import edu.cnm.deepdive.codebreaker.service.AbstractPerformanceService;
import edu.cnm.deepdive.codebreaker.view.Performance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rankings")
public class RankingController {

  private final AbstractPerformanceService performanceService;

  @Autowired
  public RankingController(AbstractPerformanceService performanceService) {
    this.performanceService = performanceService;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Performance> get(
      @RequestParam(name = "pool-size", required = false, defaultValue = "3") int poolSize,
      @RequestParam(name = "code-length", required = false, defaultValue = "3" )int codeLength,
      @RequestParam(required = false, defaultValue = "10" )int count,
      @RequestParam(required = false, defaultValue = "guesses") String order) {

  }
}
