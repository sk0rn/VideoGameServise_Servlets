package service.games.interfaces;

import pojo.game.Game;

import java.util.List;

public interface GameService {

    List<Game> getAllByGenre(Integer genreId);

    List<Game> getAllByDeveloper(Integer devId);

    List<Game> getAllGames();
}