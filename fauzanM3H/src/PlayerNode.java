public class PlayerNode {
  String namaplayer;
  int jersey;
  float ppg;
  float apg;
  float rpg;
  int points;

  PlayerNode next;
  public PlayerNode(String namaplayer, int jersey, float ppg, float apg, float rpg, int points) {
    this.namaplayer = namaplayer;
    this.jersey = jersey;
    this.ppg = ppg;
    this.apg = apg;
    this.rpg = rpg;
    this.points=points;
    this.next = null;
  }

}
