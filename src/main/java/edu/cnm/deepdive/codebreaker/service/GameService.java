package edu.cnm.deepdive.codebreaker.service;

import edu.cnm.deepdive.codebreaker.model.dao.GameRepository;
import edu.cnm.deepdive.codebreaker.model.entity.Game;
import edu.cnm.deepdive.codebreaker.model.entity.User;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService implements AbstractGameService {

  private final GameRepository repository;
  private final Random rng;

  @Autowired
  public GameService(GameRepository repository, Random rng) {
    this.repository = repository;
    this.rng = rng;
  }

  @Override
  public Game startGame(Game game, User user) {
    int[] codePoints = codePoints(game.getPool());
    validate(codePoints);
    game.setText(generate(codePoints, game.getLength()));
    //TODO generate secret text
    game.setPoolSize(codePoints.length);
    // todo Write pool length & secret
    // todo cave game using repo and return result
   game.setUser(user);

    return repository.save(game);
  }

  @Override
  public Optional<Game> get(UUID externalKey, User user) {
    return repository
        .findByExternalKey(externalKey)
        .map((game) -> ( game.getUser().getId()).equals(user.getId()) ? game : null);

  }

  private  int[] codePoints(String input){
    return input
        .codePoints()
        .distinct()
        .toArray();
  }
  private void validate(int[] codePoints){
    for (int codePoint : codePoints) {
      if (!Character.isDefined(codePoint) || !Character.isValidCodePoint(codePoint)
        || Character.isISOControl(codePoint) || Character.isWhitespace(codePoint)){
        throw  new IllegalArgumentException();
      }

    }
  }

  private String generate(int[] codePoints, int length){
 /*   int[] selection = new int [ length];
    for ( int i = 0; i<length; i ++) {
      selection[i] =  codePoints[rng.nextInt(codePoints.length)];
    }
    return new String(selection, 0, selection.length)*/
    int[] selection = IntStream
        .generate(() -> rng.nextInt(codePoints.length))
        .limit(length)
        .map((position) -> codePoints[position])
        .toArray();
    return new String(selection, 0, selection.length);

  }
}
