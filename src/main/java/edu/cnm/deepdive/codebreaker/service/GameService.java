package edu.cnm.deepdive.codebreaker.service;

import edu.cnm.deepdive.codebreaker.model.dao.GameRepository;
import edu.cnm.deepdive.codebreaker.model.entity.Game;
import edu.cnm.deepdive.codebreaker.model.entity.User;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class GameService implements AbstractGameService {

  private final GameRepository repository;

  public GameService(GameRepository repository) {
    this.repository = repository;
  }

  @Override
  public Game startGame(Game game, User user) {
    //TODO Validate properties of game
    // todo compute pool length
    //TODO generate secret text
    // todo Write pool length & secret

    return null;
  }

  @Override
  public Game get(UUID externalKey, User user) {
    return repository
        .findByExternalKey(externalKey)
        .map((game) -> ( game.getUser().getId()).equals(user.getId()) ? game : null)
        .orElseThrow();
  }

}
