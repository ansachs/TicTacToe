package Players;

public interface Player {
    public int getNextMove(String[] board);
    public String getToken();
    public String getPlayerType();
    public String getName();
}
