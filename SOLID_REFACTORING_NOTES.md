# Catatan Refactoring SOLID - HiPaw PetCare

Refactoring ini memusatkan penerapan SOLID pada fitur pengelolaan akun pengguna.

## Prinsip yang diterapkan
- **SRP (Single Responsibility Principle):** `JdbcUserRepository` menangani akses SQL; `UserValidator` menangani validasi; `UserServiceImpl` menangani aturan bisnis; `DatabaseConnection` menangani pembuatan koneksi.
- **DIP (Dependency Inversion Principle):** `UserServiceImpl` bergantung pada abstraksi `UserRepository`, bukan implementasi JDBC. `UserController` menerima abstraksi `UserService` melalui constructor.
- **OCP (Open/Closed Principle):** implementasi akses data baru dapat ditambahkan dengan mengimplementasikan `UserRepository` tanpa mengubah aturan bisnis di `UserServiceImpl`.

## Alur fitur tambah pengguna
`ManageUserFrame` -> `UserController` -> `UserService` -> `UserRepository` -> `JdbcUserRepository` -> `DatabaseConnection` -> MySQL.

## Catatan penting
- Nama database, tabel, kolom, dan konfigurasi koneksi dipertahankan mengikuti proyek asal (`db_klinik_hewan`, tabel `user`).
- Password masih disimpan mengikuti mekanisme lama (plaintext) agar tidak mengubah kompatibilitas login yang saat ini juga membandingkan password secara langsung. Ini merupakan kelemahan keamanan yang perlu ditangani pada pekerjaan terpisah dengan migrasi hash BCrypt pada proses registrasi, perubahan password, dan login secara bersamaan.
- Refactoring ini difokuskan pada fitur pengelolaan pengguna. Service dan form fitur lain belum seluruhnya dipindahkan ke pola repository.
- Untuk pengujian runtime, siapkan MySQL dengan database `db_klinik_hewan` dan jalankan proyek dari NetBeans. Konfigurasi default koneksi tetap `root` tanpa password seperti proyek awal.
