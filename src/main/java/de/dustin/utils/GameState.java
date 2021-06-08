package de.dustin.utils;

public enum GameState {

    WAITING,
    LOBBY,
    INGAME,
    ENDING;


    private static GameState Gamestate;

    public static GameState getState() {
        return Gamestate;
    }

    public static void setState(GameState state) {
        Gamestate = state;
    }


}
