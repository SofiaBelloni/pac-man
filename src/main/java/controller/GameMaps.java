package controller;

public enum GameMaps {
    /**
     * game map 1
     */
    GAME_MAP_1("game_map_1"),
    /**
     * game map 2
     */
    GAME_MAP_2("game_map_2"),
    /**
     * game map 3
     */
    GAME_MAP_3("game_map_3"),
    /**
     * game map 4
     */
    GAME_MAP_4("game_map_4"),
    /**
     * game map 5
     */
    GAME_MAP_5("game_map_5");

    private String name;

    GameMaps(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
