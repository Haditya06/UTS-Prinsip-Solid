# Klinik Hewan

Aplikasi manajemen klinik hewan berbasis Java dan MySQL yang dibuat untuk kebutuhan Ujian Tengah Semester (UTS) dengan pendekatan Prinsip SOLID. Aplikasi ini mencakup pengelolaan data pengguna, hewan, layanan, dan reservasi klinik.

## Fitur

- Autentikasi pengguna (login/register)
- Manajemen data pengguna
- Manajemen data hewan
- Manajemen layanan klinik
- Manajemen reservasi / jadwal kunjungan
- Dashboard pengguna
- Koneksi database MySQL menggunakan JDBC
- Arsitektur berbasis service, repository, controller, dan model

## Teknologi yang Digunakan

- Java 24
- Maven
- MySQL
- JDBC
- JBCrypt
- Swing / NetBeans GUI Form

## Struktur Proyek

```text
UTS-Prinsip-Solid/
├── src/
│   └── main/
│       └── java/
│           ├── com/mycompany/klinikhewan/
│           │   └── KlinikHewan.java
│           ├── config/
│           │   └── DatabaseConnection.java
│           ├── controller/
│           ├── modul/
│           ├── repository/
│           ├── service/
│           ├── validation/
│           └── view/
├── hazzelnut.sql
├── pom.xml
├── README.md
└── .gitignore
```

## Persyaratan Sistem

Sebelum menjalankan aplikasi, pastikan perangkat Anda sudah memiliki:

- Java JDK 24 atau versi yang kompatibel
- Apache Maven
- MySQL Server 8.x
- IDE seperti NetBeans, IntelliJ IDEA, atau VS Code dengan Java support

## Persiapan Database

1. Buat database MySQL baru.
2. Import file SQL berikut:

```bash
mysql -u root -p < hazzelnut.sql
```

3. Pastikan konfigurasi koneksi database sudah sesuai di file berikut:

```java
src/main/java/config/DatabaseConnection.java
```

Contoh konfigurasi:

```java
private static final String URL = "jdbc:mysql://localhost:3306/hazzelnut";
private static final String USER = "root";
private static final String PASSWORD = "";
```

> Sesuaikan nama database, username, dan password MySQL Anda. Jika nama database Anda berbeda, ubah `URL` sesuai kebutuhan.

## Cara Menjalankan Aplikasi

### 1. Clone repository

```bash
git clone https://github.com/Haditya06/UTS-Prinsip-Solid.git
cd UTS-Prinsip-Solid
```

### 2. Build project

```bash
mvn clean package
```

### 3. Jalankan aplikasi

Gunakan IDE Anda untuk menjalankan class utama:

```java
com.mycompany.klinikhewan.KlinikHewan
```

Atau bisa juga dijalankan melalui Maven jika konfigurasi sudah benar:

```bash
mvn exec:java
```

## Catatan Penting

- Aplikasi ini menggunakan desktop GUI Swing, jadi pengalaman penggunaan akan lebih optimal jika dijalankan dari IDE.
- Pastikan MySQL sudah aktif sebelum membuka aplikasi.
- Jika terjadi masalah koneksi, cek konfigurasi database pada `DatabaseConnection.java`.

## Kontribusi

Proyek ini dibuat untuk kebutuhan pembelajaran dan tugas UTS. Jika Anda ingin mengembangkan lebih lanjut, silakan fork repository ini dan buat pull request.

## Lisensi

Proyek ini dibuat untuk keperluan akademik. Silakan sesuaikan lisensi jika akan dipakai untuk kebutuhan komersial atau publikasi.

## Penulis

- Haditya06

## Status Proyek

Status: Masih dalam tahap pengembangan / pembelajaran.
