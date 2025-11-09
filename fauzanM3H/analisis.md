NAMA : FAUZAN HARI RAMDANI
NIM : F1D02410047
KELOMPOK : 10

                     [Analisis Kompleksitas Algoritma Sorting & Searching] :

Dokumen ini berisi analisis perbandingan efektivitas dan efisiensi algoritma sorting (Merge Sort & Quick Sort) dan searching (Linear Search & Binary Search) yang diimplementasikan untuk mengelola dan menganalisis data statistik pemain NBA.

Analisis ini didasarkan pada output yang dihasilkan oleh program, yang mengukur metrik berikut:
Waktu Eksekusi (msec): Waktu nyata yang diperlukan.
Panggilan Rekursif: Jumlah total pemanggilan fungsi rekursif (untuk sorting).
Perbandingan: Jumlah perbandingan antar elemen (operasi kunci).
Pertukaran: Jumlah pertukaran data antar node (khusus Quick Sort versi value swap)

- Analisis Perbandingan Sorting +
  Algoritma yang dibandingkan adalah Merge Sort (versi node swap) dan Quick Sort (versi value swap) pada linked list pemain gabungan (sekitar 300 node data).

Hasil Pengujian(100 data)
PERBANDINGAN SORTING:
Merge Sort:

- Waktu eksekusi: 0.092 msec
- Recursive Call: 199
- Perbandingan: 1074
- Pertukaran: Tidak dihitung (merge tidak swap)
  Quick Sort:
- Waktu eksekusi: 0.198 msec
- Recursive Call: 129
- Perbandingan: 679
- Pertukaran: 329

Berdasarkan hasil pengujian pada 100 data, Merge Sort terbukti secara signifikan lebih cepat (0.092 msec) dibandingkan Quick Sort (0.198 msec).
Alasan:
Merge Sort: Meskipun melakukan lebih banyak panggilan rekursif (199) dan perbandingan (1074), implementasi node swap (memanipulasi pointer next) sangat efisien. Operasi ini tidak menyalin data besar, hanya alamat memori, yang sangat cepat. Overhead-nya dalam mencari middle tampaknya lebih ringan daripada overhead Quick Sort pada linked list.

Quick Sort: Implementasi value swap yang kita gunakan ternyata lebih lambat. Meskipun jumlah perbandingan (679) dan panggilan rekursi (129) lebih sedikit, operasi "Pertukaran" (329 kali) memakan biaya. Setiap "swap" mengharuskan penyalinan seluruh data di dalam node (nama, jersey, 4 statistik). Aktivitas penyalinan data value di memori ini, jika dilakukan ratusan kali, ternyata lebih lambat daripada operasi pointer di Merge Sort.

- PERBANDINGAN SEARCHING +
  Mencari pemain dengan PPG: 23.3 menggunakan Linear Search
  Ditemukan Pemain: Jayson Tatum
  Elapsed Time: 0.0025msec

* Perbandingan: 1

Binary Search (termasuk waktu sorting data acak):
Mencari Pemain dengan PPG 23.3 Menggunakan Binary Search
Ditemukan Pemain : Jayson Tatum
Elapsed Time is 0.0096 msec

- Waktu eksekusi total: 0.360301 msec
- Recursive Call saat Sorting: 129
- Perbandingan saat Sorting: 679
- Pertukaran saat Sorting: 329
- Perbandingan saat Searching: 13

Linear Search pada data acak menunjukkan waktu yang sangat cepat yaitu 0.0029 msec dengan hanya 3 perbandingan. Algoritma ini bekerja dengan memeriksa setiap elemen secara berurutan hingga menemukan target. Keunggulan utama Linear Search adalah kesederhanaan implementasi dan tidak memerlukan data terurut. Namun, kompleksitas waktu O(n) membuatnya kurang efisien untuk dataset yang sangat besar.

Binary Search mencatat waktu total 0.4636 msec yang termasuk waktu sorting. Proses searching itu sendiri sangat efisien dengan hanya 11 perbandingan, namun overhead waktu datang dari proses pengurutan data terlebih dahulu menggunakan Quick Sort (129 recursive call, 679 perbandingan, dan 329 pertukaran). Binary Search dengan kompleksitas O(log n) sangat superior dalam proses pencarian murni, tetapi ketergantungannya pada data terurut menjadi trade-off yang signifikan.

Linear Search lebih efisien untuk pencarian satu kali pada data acak, sementara Binary Search lebih unggul untuk multiple queries (banyak pencarian) pada dataset yang sama setelah dilakukan sorting awal.

| Jumlah Data | Linear Search | Binary Search (Total) | Merge Sort  | Quick Sort  |
| :---------- | :-----------: | :-------------------: | :---------: | :---------: |
| **~100**    |  0.0029 msec  |      0.4636 msec      | 0.092 msec  | 0.198 msec  |
| **~200**    |  0.0016 msec  |      0.3153 msec      | 0.1303 msec | 0.2041 msec |
| **~300**    |  0.0043 msec  |      0.3504 msec      | 0.1182 msec | 0.1006 msec |
