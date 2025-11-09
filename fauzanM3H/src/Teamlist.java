public class Teamlist {
  TeamNode head;
  TeamNode tail;

  public Teamlist() {
    this.head = null;
    this.tail = null;
  }

  public void addTeam(String namatim, int gp, int w, int l, float ppg, float apg, float rpg) {
    TeamNode newTeam = new TeamNode(namatim, gp, w, l, ppg, apg, rpg);
    if (this.head == null) {
      this.head = newTeam;
      this.tail = newTeam;
    } else {
      this.tail.next = newTeam;
      this.tail = newTeam;
    }
  }

  public void displayTeams() {

    System.out.println("------------------------------------------------------------------------------------");
    System.out.printf("%-25s | %-5s | %-5s | %-5s | %-8s | %-8s | %-8s\n",
            "TEAM NAME", "GP", "W", "L", "PPG", "APG", "RPG");
    System.out.println("------------------------------------------------------------------------------------");

    TeamNode current = this.head;

    while (current != null) {
        double ppg = (double) current.ppg / current.gp;
        double apg = (double) current.apg / current.gp;
        double rpg = (double) current.rpg / current.gp;

        System.out.printf("%-25s | %-5d | %-5d | %-5d | %-8.2f | %-8.2f | %-8.2f\n",
                current.namatim,
                current.gp,
                current.w,
                current.l,
                ppg,
                apg,
                rpg
        );
        
        current = current.next;
    }
    System.out.println("------------------------------------------------------------------------------------");
}
  public void displayall(){
    TeamNode currentteam= this.head;

    while (currentteam != null){
      currentteam.diplayplayers();
      currentteam = currentteam.next;
    }
  }

public TeamNode getTail(TeamNode node){
  if (node == null || node.next == null) {
    return node;
  }
  TeamNode current = node;
  while (current.next != null) {
    current = current.next;
  }
  return current;
}

public TeamNode quickSortRecursive(TeamNode startNode){
  if (startNode == null || startNode.next == null){
    return startNode;
  }

  TeamNode pivot = startNode;
  // Siapkan 4 pointer untuk 2 list baru
  TeamNode greaterHead = null, greaterTail = null;
  TeamNode lessHead = null, lessTail = null;

  TeamNode current = startNode.next;
  while (current != null) {
    TeamNode next =current.next;
    current.next = null;
    if (current.w >= pivot.w) {
      if (greaterHead == null) {
        greaterHead = current;
        greaterTail = current;
      } else {
        greaterTail.next = current;
        greaterTail = current;
      }
    } else {
      if (lessHead == null) {
        lessHead = current;
        lessTail = current;
      } else {
        lessTail.next = current;
        lessTail = current;
      }
    }
    current = next;
  }
  TeamNode sortedGreater = quickSortRecursive(greaterHead);
  TeamNode sortedLess = quickSortRecursive(lessHead);
  pivot.next = null;
  pivot.next = sortedLess;
  if (sortedGreater == null) {
    // Jika tidak ada, pivot adalah head baru dari list gabungan ini
    return pivot;
} 
TeamNode newTail = getTail(sortedGreater);
newTail.next = pivot;
return sortedGreater;
}

//untuk win
public void sortTeamsByWins() {
  System.out.println("Diurutkan berdasarkan W (Wins)");
  this.head = quickSortRecursive(this.head);
  this.tail = getTail(this.head);
}

//mergesort
public TeamNode getMiddle(TeamNode headNode){
  if(headNode == null){
    return headNode;
  }

  TeamNode slow =headNode;
  TeamNode fast = headNode.next;

  while(fast !=null && fast.next != null){
    slow = slow.next;
    fast = fast.next.next;
  }
  return slow;
}

public TeamNode merge(TeamNode left, TeamNode right){
  if(left == null){
    return right;
  }
  if(right == null){
    return left;
  }

  TeamNode resultHead;

  double ppgleft = (double) left.ppg / left.gp;
  double ppgright = (double) right.ppg / right.gp;

  if (ppgleft >= ppgright){
    resultHead =left;
    resultHead.next = merge(left.next, right);
  }else{
    resultHead = right;
    resultHead.next = merge(left, right.next);
  }
  return resultHead;
}

public TeamNode mergeSortRecursive(TeamNode headNode){
  if(headNode == null || headNode.next == null){
    return headNode;
  }
    TeamNode middle = getMiddle(headNode);
    TeamNode rightHalf = middle.next;
    middle.next = null;
    TeamNode sortedLeft = mergeSortRecursive(headNode);
    TeamNode sortedRight = mergeSortRecursive(rightHalf);
    return merge(sortedLeft, sortedRight);
}

public void sortTeamsByPPG(){
  System.out.println("Diurutkan berdasarkan PPG (Points Per Game)");
  this.head = mergeSortRecursive(this.head);
  this.tail = getTail(this.head);
}

public PlayerList getAllPlayers(){
  // Buat list kosong baru untuk menampung semua pemain
  PlayerList combinedList = new PlayerList();

  // Iterasi setiap tim di liga (Outer loop)
  TeamNode currentTeam = this.head;
  while (currentTeam != null){

    PlayerNode currentPlayer = currentTeam.players.head;
    while (currentPlayer != null){
      PlayerNode playerCopy = new PlayerNode(
        currentPlayer.namaplayer,
        currentPlayer.jersey,
        currentPlayer.ppg,
        currentPlayer.apg,
        currentPlayer.rpg,
        currentPlayer.points
        );
        // Tambahkan 'playerCopy' ke 'combinedList'
        if(combinedList.head == null){
           combinedList.head = playerCopy;
           combinedList.tail = playerCopy;
        }else {
          combinedList.tail.next = playerCopy;
          combinedList.tail = playerCopy;
        }
        currentPlayer = currentPlayer.next;
    }
    currentTeam = currentTeam.next;
  }
  return combinedList;
}

public void runFullAnalysis(double targetPPG) {
  System.out.println("==============================================");
  System.out.println("             ANALISIS KOMPLEKSITAS");
  System.out.println("==============================================");
  System.out.println("PERBANDINGAN SORTING:");

  // --- Merge Sort ---
  PlayerList listMerge = this.getAllPlayers(); // Ambil data baru
  listMerge.resetCounters();
  long startTime = System.nanoTime();
  listMerge.sortPlayersByPPGmerge(); // Panggil Merge Sort
  long endTime = System.nanoTime();

  System.out.println("Merge Sort:");
  System.out.println(" - Waktu eksekusi: " + ((endTime - startTime) / 1000000.0) + " msec");
  System.out.println(" - Recursive Call: " + listMerge.getSortRecursiveCalls());
  System.out.println(" - Perbandingan: " + listMerge.getSortComparisons());
  System.out.println(" - Pertukaran: Tidak dihitung (merge tidak swap)");

  // --- Quick Sort ---
  PlayerList listQuick = this.getAllPlayers(); // Ambil data baru
  listQuick.resetCounters();
  startTime = System.nanoTime();
  listQuick.sortPlayersByPPGquick(); // Panggil Quick Sort (Value Swap)
  endTime = System.nanoTime();

  System.out.println("Quick Sort:");
  System.out.println(" - Waktu eksekusi: " + ((endTime - startTime) / 1000000.0) + " msec");
  System.out.println(" - Recursive Call: " + listQuick.getSortRecursiveCalls());
  System.out.println(" - Perbandingan: " + listQuick.getSortComparisons());
  System.out.println(" - Pertukaran: " + listQuick.getSortSwaps());

  // --- PERBANDINGAN SEARCHING ---
  System.out.println("\nPERBANDINGAN SEARCHING:");
  
  // --- Linear Search (data acak) ---
  PlayerList listUnsorted = this.getAllPlayers(); // Ambil data baru (acak)
  startTime = System.nanoTime();
  listUnsorted.linearSearchByPPG(targetPPG); // Panggil Linear Search
  endTime = System.nanoTime();
  // Waktu dan hasil sudah dicetak di dalam methodnya
  System.out.println(" - Perbandingan: " + listUnsorted.getSearchComparisons());


  // --- Binary Search (termasuk sorting) ---
  PlayerList listForBinary = this.getAllPlayers(); // Ambil data baru (acak)
  listForBinary.resetCounters(); // Reset counter sort & search
  
  System.out.println("\nBinary Search (termasuk waktu sorting data acak):");
  startTime = System.nanoTime();
  
  //  Sorting (Quick Sort)
  listForBinary.sortPlayersByPPGquick();
  
  //  Searching
  listForBinary.binarysearchByPPG(targetPPG);
  
  endTime = System.nanoTime();
  
  // Waktu dan hasil search sudah dicetak di dalam methodnya
  System.out.println(" - Waktu eksekusi total: " + ((endTime - startTime) / 1000000.0) + " msec");
  System.out.println(" - Recursive Call saat Sorting: " + listForBinary.getSortRecursiveCalls());
  System.out.println(" - Perbandingan saat Sorting: " + listForBinary.getSortComparisons());
  System.out.println(" - Pertukaran saat Sorting: " + listForBinary.getSortSwaps());
  System.out.println(" - Perbandingan saat Searching: " + listForBinary.getSearchComparisons());
  System.out.println("Catatan: Binary search membutuhkan data terurut,");
  System.out.println("sehingga waktu dan operasi sorting termasuk dalam analisis.");
  System.out.println("=========================================================");
}

}


