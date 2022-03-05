# PERLU DILINDUNGI
> 13519114 Renaldi Arlin\
> 13519121 Michael Philip Gunardi\
> 13519144 Jonathan Christhoper Jahja

## Deskripsi Aplikasi
Perlu Dilindungi adalah aplikasi yang dikembangkan untuk memenuhi tugas mata kuliah IF3210 Pengembangan Aplikasi pada Platform Khusus	yang menggambarkan sedikit banyak seperti aplikasi pemerintah Peduli Lindungi

Pembuatan aplikasi ditujukan agar mahasiswa memahami prinsip dasar dalam platfrom Android, mahasiswa mampu bekerja dalam tim pada konteks pengembangan aplikasi Android, mahasiswa mampu mempresentasikan perangkat lunak yang sudah dibangun

Aplikasi memiliki kemampuan sebagai berikut :
1. Menampikan Berita COVID-19
2. Menampilkan Daftar Faskes untuk Vaksinasi
3. Menampilkan Detail Informasi Faskes
4. Menampilkan Daftar Bookmark Faskes
5. Melakukan "Check-In"

Kemampuan aplikasi ini dapat dilihat cara kerjanya secara detail pada segment dibawah ini.

## Cara Kerja
### 1. Menampilkan Berita COVID-19
Aplikasi akan mengambil data berita COVID-19   terlebih dahulu menggunakan API https://perludilindungi.herokuapp.com/
Aplikasi akan menampilkan list berita yang berisi Judul, Gambar, dan Tanggal berita.
Aplikasi akan menampilkan sebuah WebView ketika salah satu berita dari list sebelumnya diklik oleh Pengguna
WebView Aplikasi akan menampilkan berita dari data URL yang didapat dari API sebelumnya

### 2. Menampilkan Daftar Faskes untuk Vaksinasi
Aplikasi akan mengambil data daftar faskes yang tersedia dari https://perludilindungi.herokuapp.com/
Aplikasi akan menampilan list berita faskes dalam bentuk card yang dapat di akses pada halaman Lokasi Vaksin
Aplikasi juga dapat menampilkan daftar faskes berdasarkan provinsi dan kota tertentu yang kemudian akan dipilih 5 faskes terdekat dengna lokasi user sekarang
Setiap card yang ditampilkan oleh aplikasi apabila di klik akan menuju ke halaman detail yang berisi informasi lebih lanjut mengenai faskes tersebut

### 3. Menampilkan Detail Informasi Faskes
Pengguna dapat masuk ke halaman detail suatu faskes dengan mengklik card yang terdapat pada halaman Lokasi Vaksin
Pada halaman ini tertera informasi mengenai nama faskes, kode faskes, alamat faskes, nomor telepon faskes, jenis faskes dan status faskes
Pada halaman ini pengguna dapat menambahkan faskes kepada bookmarknya

### 4. Menampilkan Daftar Bookmark Faskes
Aplikasi menggunakan Library Room untuk menyimpan data-data faskes secara lokal pada gawai User. Untuk bookmark suatu faskes dapat dilakukan dengan membuka halaman detail dari faskes tersebut kemudian menekan tombol `Bookmark`. Daftar faskes yang dibookmark dapat dilihat di halaman Bookmark pada Navigation Bar. Daftar Bookmark diimplementasikan dengan cara yang sama dengan daftar Faskes di fitur sebelumnya.

### 5. Melakukan "Check-In"
Aplikasi dapat melakukan scan QRCode dengan menggunakan library ZXing-android-embedded. Library ZXing menyediakan komponen DecoratedBarcodeView yang akan memanfaatkan kamera untuk melakukan scanning terhadap sebuah Barcode ataupun QRCode. Ketika aplikasi berhasil scan suatu QRCode, maka data QRCode akan di POST Request pada API https://perludilindungi.herokuapp.com/ untuk mendapatkan status keberhasilan "Check-In". Pada halaman ini juga tersedia temperatur suhu yang memanfaatkan SensorManager yang sudah disediakan oleh Android.

## Library yang digunakan dan justifikasi penggunaannya.
- Material UI untuk penggunaan komponen Button dan CardView dengan cornerRadius
- Retrofit untuk pemanggilan REST API
- Glide Image untuk mengeload image dari external URL
- Room untuk penyimpanan dan ORM database SQLite
- GMS Play Service Location untuk mendapatkan GPS Location
- ZXing-android-embedded untuk scan barcode dan QR code

## Screenshot
Splashscreen
![ss](/screenshots/1-splashscreen.png "Splashscreen")
Berita COVID-19
![ss](/screenshots/2-news.png "Splashscreen")
Webview Berita
![ss](/screenshots/3-news-web-view.png "Splashscreen")
Daftar Lokasi Vaksin
![ss](/screenshots/4-lokasi-vaksin.png "Splashscreen")
Detail Lokasi Vaksin
![ss](/screenshots/5-lokasi-vaksin-detail.png "Splashscreen")
![ss](/screenshots/6-bookmark-faskes.png "Splashscreen")
Scan QRCode Default
![ss](/screenshots/7-scan-default.jpg "Splashscreen")
Scan QRCode Green
![ss](/screenshots/7-scan-berhasil-green.jpg "Splashscreen")
Scan QRCode Yellow
![ss](/screenshots/7-scan-berhasil-yellow.jpg "Splashscreen")
Scan QRCode Red
![ss](/screenshots/7-scan-gagal-red.jpg "Splashscreen")
Scan QRCode Black
![ss](/screenshots/7-scan-gagal-black.jpg "Splashscreen")

## Pembagian kerja anggota kelompok.
- 13519114 Renaldi Arlin
  - Setup project dan splash screen
  - Halaman daftar berita
- 13519121 Michael Philip Gunardi
  - Halaman daftar faskes
  - Halaman detail faskes
- 13519144 Jonathan Christhoper Jahja
  - Halaman scan
  - Halaman daftar bookmark