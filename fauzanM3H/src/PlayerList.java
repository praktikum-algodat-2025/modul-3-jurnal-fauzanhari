public class PlayerList {
    PlayerNode head;
    PlayerNode tail;
    // --- VARIABEL  UNTUK ANALISIS ---
    public long sortRecursiveCalls;
    public long sortComparisons;
    public long sortSwaps;
    public long searchComparisons;

    public PlayerList() {
        this.head = null;
        this.tail = null;
        resetCounters();
    }
    public void resetCounters() {
        this.sortRecursiveCalls = 0;
        this.sortComparisons = 0;
        this.sortSwaps = 0;
        this.searchComparisons = 0;
    }
    // Method baru untuk mengambil counter
    public long getSortRecursiveCalls() { return this.sortRecursiveCalls; }
    public long getSortComparisons() { return this.sortComparisons; }
    public long getSortSwaps() { return this.sortSwaps; }
    public long getSearchComparisons() { return this.searchComparisons; }

    public void addPlayer(String namaplayer, int jersey, float ppg, float apg, float rpg, int points) {
        PlayerNode newPlayer = new PlayerNode(namaplayer, jersey, ppg, apg, rpg, points);
        if (this.head == null) {
            this.head = newPlayer;
            this.tail = newPlayer;
        } else {
            this.tail.next = newPlayer;
            this.tail = newPlayer;
        }
    }

    public void displayPlayers() {
    
        // 1. Cetak Header Tabel (sesuai format gambar)
        System.out.printf("%-25s %-10s %-8s %-8s %-8s\n",
                "PLAYER NAME", "JERSEY #", "PPG", "APG", "RPG");
        System.out.println("--------------------------------------------------------------------");
      
        // 2. Siapkan loop, mulai dari pemain pertama
        PlayerNode currentPlayer = this.head;
        
      
        // 3. Loop selama masih ada pemain
        while (currentPlayer != null) {
            
            // 4. HITUNG STATISTIK RATA-RATA PER GAME
            double ppg = (double) currentPlayer.ppg / currentPlayer.points;
            double apg = (double) currentPlayer.apg / currentPlayer.points;
            double rpg = (double) currentPlayer.rpg / currentPlayer.points;
      
            // 5. Cetak data pemain dengan format
            System.out.printf("%-25s %-10d %-8.1f %-8.1f %-8.1f\n",
                    currentPlayer.namaplayer,
                    currentPlayer.jersey,
                    ppg,
                    apg,
                    rpg
            );
            
            currentPlayer = currentPlayer.next;
        }
        System.out.println("--------------------------------------------------------------------");
      }



      public PlayerNode getMiddle(PlayerNode headNode){
        if(headNode ==null){
            return headNode;
        }
        PlayerNode slow = headNode;
        PlayerNode fast = headNode.next;
        while(fast!=null && fast.next!=null){
            slow= slow.next;
            fast= fast.next.next;
        }
        return slow;
      }

      public PlayerNode getTail(PlayerNode node){
        if(node==null||node.next==null){
            return node;
        }
        PlayerNode current = node;
        while(current.next!=null){
            current= current.next;
        }
        return current;
      }


      public PlayerNode merge(PlayerNode left, PlayerNode right) {
        // Jika salah satu list kosong, kembalikan list yang lain
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
    
        PlayerNode resultHead;
    
        // Hitung PPG untuk perbandingan
        double ppgLeft = (double) left.ppg / left.points;
        double ppgRight = (double) right.ppg / right.points;
        // 2. HITUNG COMPARISON
        this.sortComparisons++;
        // 2. HITUNG COMPARISON
        this.sortComparisons++;
        // Tentukan head baru berdasarkan perbandingan PPG (DESCENDING)
        if (ppgLeft > ppgRight) {
            resultHead = left;
            // Panggil rekursif untuk sisa list 'left'
            resultHead.next = merge(left.next, right);
        } else {
            resultHead = right;
            // Panggil rekursif untuk sisa list 'right'
            resultHead.next = merge(left, right.next);
        }
    
        return resultHead;
    }

    public PlayerNode mergeSortRecursive(PlayerNode headNode) {
        // 1. HITUNG RECURSIVE CALL
        this.sortRecursiveCalls++;
        // --- Base Case ---
        if (headNode == null || headNode.next == null) {
            return headNode;
        }
    
        // --- 1. Pecah (Split) ---
        PlayerNode middle = getMiddle(headNode);
        PlayerNode rightHalf = middle.next;
        middle.next = null; // Putuskan list
    
        // --- 2. Urutkan (Recurse) ---
        PlayerNode sortedLeft = mergeSortRecursive(headNode);
        PlayerNode sortedRight = mergeSortRecursive(rightHalf);
    
        // --- 3. Gabungkan (Merge) ---
        return merge(sortedLeft, sortedRight);
    }

    public void sortPlayersByPPGmerge() {
        // Panggil method recursive, mulai dari 'head'
        this.head = mergeSortRecursive(this.head);
        
        // Cari dan perbarui 'tail' utama
        this.tail = getTail(this.head);
    }

private void swapData(PlayerNode a, PlayerNode b) {
    // 1. HITUNG SWAP
    this.sortSwaps++;
    
    // (Sesuai 'PlayerNode' Anda)
    String tempNama = a.namaplayer;
    int tempJersey = a.jersey;
    float tempPpg = a.ppg;
    float tempApg = a.apg;
    float tempRpg = a.rpg;
    int tempPoints = a.points;

    a.namaplayer = b.namaplayer;
    a.jersey = b.jersey;
    a.ppg = b.ppg;
    a.apg = b.apg;
    a.rpg = b.rpg;
    a.points = b.points;

    b.namaplayer = tempNama;
    b.jersey = tempJersey;
    b.ppg = tempPpg;
    b.apg = tempApg;
    b.rpg = tempRpg;
    b.points = tempPoints;
}

private PlayerNode partition(PlayerNode start, PlayerNode end) {
    PlayerNode pivot = start;
    PlayerNode i = start;
    PlayerNode j = start.next;

    // Hitung PPG Pivot (Sesuai 'PlayerNode' Anda: ppg/points)
    double divPivot = (pivot.points == 0) ? 1.0 : (double) pivot.points;
    double ppgPivot = (double) pivot.ppg / divPivot;

    while (j != end) {
        // Hitung PPG 'j'
        double divJ = (j.points == 0) ? 1.0 : (double) j.points;
        double ppgJ = (double) j.ppg / divJ;

        // 2. HITUNG COMPARISON
        this.sortComparisons++;
        if (ppgJ > ppgPivot) { // Descending
            i = i.next;
            swapData(i, j);
        }
        j = j.next;
    }
    swapData(start, i); // Swap terakhir untuk pivot
    return i;
}

private void quickSortRecursive_ValueSwap(PlayerNode start, PlayerNode end) {
    // 3. HITUNG RECURSIVE CALL
    this.sortRecursiveCalls++;
    
    if (start == end || start.next == end) {
        return;
    }
    PlayerNode pivotNode = partition(start, end);
    quickSortRecursive_ValueSwap(start, pivotNode);
    quickSortRecursive_ValueSwap(pivotNode.next, end);
}

public void sortPlayersByPPGquick() {
    quickSortRecursive_ValueSwap(this.head, null);
}


public void linearSearchByPPG(double targetPPG){
    System.out.println("Mencari pemain dengan PPG: " + targetPPG + " menggunakan Linear Search");
    long startTime= System.nanoTime();

    // Reset counter sebelum search
    this.searchComparisons = 0;

    PlayerNode current = this.head;
    boolean found = false;
    while (current != null){
        this.searchComparisons++;
        double divisor = (current.points == 0)? 1.0 : (double) current.points;
        double actualPPG = (double) current.ppg / divisor;

        if(Math.abs(actualPPG - targetPPG) < 0.05){
            long elapsedTime = System.nanoTime() - startTime;
            System.out.println("Ditemukan Pemain: "+ current.namaplayer);
            System.out.println("Elapsed Time: "+ (elapsedTime /1000000.0)+ "msec");
            found = true;
            break;
        }
        current = current.next;
    }
    if(!found){
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Pemain dengan PPG: "+ targetPPG + " tidak ditemukan.");
        System.out.println("Elapsed Time: "+ (elapsedTime /1000000.0)+ "msec");
    }
}

public PlayerNode getMiddlebin(PlayerNode start, PlayerNode end) {
    if (start == null) {
        return null;
    }
    
    PlayerNode slow = start;
    PlayerNode fast = start.next;

    while (fast != end && fast.next != end) {
        slow = slow.next;
        fast = fast.next.next;
    }
    
    return slow;
}

public PlayerNode BinarySearchRecursive(PlayerNode start, PlayerNode end, double targetPPG){
    if(start == null || start == end){
        return null;
    }
    PlayerNode middle = getMiddlebin(start, end);
    if(middle == null){
        return null;
    }
    double divisor = (middle.points == 0)? 1.0 : middle.points;
    double middlePPG = (double) middle.ppg / divisor;

    this.searchComparisons++;
    if(Math.abs(middlePPG - targetPPG) < 0.05){
        return middle;
    }
    this.searchComparisons++;
    //list diurutkan descending
    if(middlePPG > targetPPG){
        // 'middlePPG' terlalu besar, target ada di 'kanan' 
        return BinarySearchRecursive(middle.next, end, targetPPG);
    }else{
        // 'middlePPG' terlalu kecil, target ada di 'kiri'
        return BinarySearchRecursive(start, middle, targetPPG);
    }
}

public void binarysearchByPPG(double targetPPG){
    System.out.println("Mencari Pemain dengan PPG "+ targetPPG + " Menggunakan Binary Search");
    long startTime = System.nanoTime();
    // Reset counter sebelum search
    this.searchComparisons = 0;
    PlayerNode foundPlayer = BinarySearchRecursive(this.head, null, targetPPG);
    long elapsedTime = System.nanoTime() - startTime;
    if (foundPlayer != null){
        System.out.println("Ditemukan Pemain : " + foundPlayer.namaplayer);
    }else {
        System.out.println("Pemain dengan PPG " + targetPPG + " tidak ditemukan.");
    }
    System.out.println("Elapsed Time is " + (elapsedTime / 1000000.0) + " msec");
    }


    public void findMVPCandidates(double minPPG) {
        // 1. Cetak Header
        System.out.println("Mencari Kandidat MVP berdasarkan PPG >= " + minPPG);
        System.out.printf("%-25s %-10s %-8s %-8s %-8s\n",
                "PLAYER NAME", "JERSEY #", "PPG", "APG", "RPG");
        System.out.println("--------------------------------------------------------------------");
    
        // 2. Mulai Linear Search dari head
        PlayerNode current = this.head;
    
        // 3. Loop selama masih ada pemain
        while (current != null) {
        double divisor = (current.points == 0) ? 1.0 : (double) current.points;
        double currentPPG = (double) current.ppg / divisor;

        // 5. Periksa apakah pemain ini memenuhi syarat
        if (currentPPG >= (minPPG - 0.05)) {
            
            // Hitung sisa statistik untuk dicetak
            double currentAPG = (double) current.apg / divisor;
            double currentRPG = (double) current.rpg / divisor;

            // Cetak pemain
            System.out.printf("%-25s %-10d %-8.1f %-8.1f %-8.1f\n",
                    current.namaplayer, // Sesuai 'PlayerNode' Anda
                    current.jersey,
                    currentPPG,
                    currentAPG,
                    currentRPG
            );
        } else {
            break; 
        }
        
        current = current.next;
    }
    System.out.println("--------------------------------------------------------------------");
}
}
