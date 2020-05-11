package controller;

public enum GameMaps {
    GAME_MAP_1("game_map_1"),
    GAME_MAP_2("game_map_2"),
    GAME_MAP_3("game_map_3"),
    GAME_MAP_4("game_map_4"),
    GAME_MAP_5("game_map_5");

    private String name;

    GameMaps(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
