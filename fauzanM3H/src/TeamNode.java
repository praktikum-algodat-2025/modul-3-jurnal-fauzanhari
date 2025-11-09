  public class TeamNode {
  String namatim;
    int gp;
    int w;
    int l;
    float ppg;
    float apg;
    float rpg;
   
    TeamNode next;
    PlayerList players;

    public TeamNode(String namatim, int gp, int w, int l, float ppg, float apg, float rpg) {
        this.namatim = namatim;
        this.gp = gp;
        this.w = w;
        this.l = l;
        this.ppg = ppg;
        this.apg = apg;
        this.rpg = rpg;
        this.next = null;
        this.players = new PlayerList();
    }

    public void diplayplayers(){
      System.out.println(this.namatim);
      // Cetak Header Tabel (sesuai format gambar)
      System.out.printf("%-25s %-10s %-8s %-8s %-8s\n",
      "PLAYER NAME", "JERSEY #", "PPG", "APG", "RPG");
      System.out.println("------------------------------------------------------------------------------");

      PlayerNode currentPlayer = this.players.head;

      while (currentPlayer != null) {
        double ppg = (double) currentPlayer.ppg / currentPlayer.points;
        double apg = (double) currentPlayer.apg / currentPlayer.points;
        double rpg = (double) currentPlayer.rpg / currentPlayer.points;
        System.out.printf("%-25s %-10d %-8.1f %-8.1f %-8.1f\n",
                currentPlayer.namaplayer,
                currentPlayer.jersey,
                ppg,
                apg,
                rpg
        );
        
        currentPlayer = currentPlayer.next;
      }
      System.out.println();
      }
}
