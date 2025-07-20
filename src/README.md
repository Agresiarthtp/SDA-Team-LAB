# Sistem Manajemen Perpustakaan

## Deskripsi Proyek
Proyek ini merupakan implementasi sistem manajemen perpustakaan yang menggunakan tiga struktur data utama: Queue, Stack, dan Linked List dalam satu kasus terpadu.

## Struktur Data yang Diimplementasikan

### 1. Queue (Antrian Peminjaman)
- Digunakan untuk mengelola daftar tunggu anggota yang ingin meminjam buku
- Mengimplementasikan First-In-First-Out (FIFO) - anggota yang masuk lebih dulu dilayani lebih dulu
- Operasi utama: enqueue (tambah anggota), dequeue (layani anggota), display (tampilkan antrean)

### 2. Stack (Histori Transaksi)
- Digunakan untuk mencatat dan melacak transaksi peminjaman dan pengembalian buku
- Mendukung fitur undo/redo untuk membatalkan atau mengembalikan transaksi
- Operasi utama: push (tambah transaksi), pop (ambil transaksi terakhir), peek (lihat transaksi terakhir)

### 3. Linked List (Inventaris Buku)
- Menyimpan daftar buku dalam inventaris perpustakaan
- Mendukung operasi dinamis: tambah, hapus, dan update informasi buku
- Memungkinkan pengelolaan jumlah stok untuk setiap buku

## Fitur Sistem

### Manajemen Buku (Linked List)
- Menambahkan buku baru ke inventaris
- Menghapus buku dari inventaris
- Memperbarui jumlah stok buku
- Menampilkan daftar semua buku yang tersedia

### Manajemen Antrian (Queue)
- Menambahkan anggota ke daftar tunggu peminjaman
- Melayani anggota yang sedang mengantri
- Melihat daftar anggota yang sedang mengantri

### Manajemen Transaksi (Stack)
- Mencatat transaksi peminjaman dan pengembalian buku
- Membatalkan (undo) transaksi terakhir
- Mengembalikan (redo) transaksi yang dibatalkan
- Menampilkan histori transaksi

### Integrasi Keseluruhan
- Ketika anggota dilayani dari antrian, transaksi dicatat dalam stack
- Ketika buku dipinjam atau dikembalikan, stok di inventaris diperbarui
- Operasi undo/redo mempengaruhi stok buku dalam inventaris

## Analisis Kinerja
- Perbandingan kinerja ketiga struktur data
- Evaluasi kelebihan dan kekurangan masing-masing struktur data
- Analisis kesesuaian struktur data dengan perannya dalam sistem

## Pembelajaran
- Pemahaman tentang penggunaan praktis Queue, Stack, dan Linked List
- Analisis kompleksitas waktu dan ruang untuk operasi-operasi kunci
- Penerapan struktur data dalam konteks aplikasi dunia nyata
