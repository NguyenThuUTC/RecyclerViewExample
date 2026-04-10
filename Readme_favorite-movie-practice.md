# Thực hành: Favorite Movies

## Mô tả
Dựa trên project movie hiện tại, hãy mở rộng ứng dụng để xây dựng chức năng **Favorite Movies**.

Sau khi hoàn thành, ứng dụng cần có:

- Ở màn hình Movie List:
    - mỗi item có icon favorite
    - click vào icon sẽ lưu movie vào database với trạng thái yêu thích
- có 1 icon để mở màn hình Favorite Movies
- ở màn hình Favorite Movies:
    - hiển thị danh sách movie đã yêu thích
    - mỗi item có icon để bỏ khỏi favorite

---

## 🧩 Task 1. Cập nhật database
- thêm field isFavorite vào MovieEntity
- tăng version database
- viết migration để thêm cột isFavorite
- add migration vào Room.databaseBuilder

---

## 🧩 Task 2. Bổ sung DAO
Thêm các hàm cần thiết để xử lý favorite.
- nếu movie **chưa có trong database** → **insert**
- nếu movie **đã có trong database** → **update `isFavorite`**

#### 1. Lấy movie theo idLấy movie theo id
```sql
 SELECT * FROM movie WHERE id = :movieId"
```
#### 2. Cập nhật trạng thái favorite
```sql
 UPDATE movies SET is_favorite = :isFavorite WHERE id = :movieId
```
#### 3. Lấy danh sách favorite movies
```sql
 SELECT * FROM movies WHERE isFavorite = 1
```

## 🧩 Task 3. Favorite ở màn hình Movie List

#### 1. UI
- Thêm icon favorite vào item movie
#### 2. Xử lý click
- Bắt sự kiện click trong adapter, đổi icon favorite (ví dụ: tim rỗng → tim đầy)
- callback về Fragment -> gọi function trong viewmodel để lưu favorite movie
#### 3. Logic xử lý
Trong ViewModel / Repository:
- gọi getMovieById(movie.id)
- nếu:
  - chưa có → insert với isFavorite = true
  - đã có → update isFavorite = true
   
#### Kết quả
Click icon → movie được lưu vào favorite và UI cập nhật ngay.

## 🧩 Task 4. Điều hướng
- Tạo fragment **FavoriteMovieFragment**
- Thêm icon mở màn hình Favorite Movies
- Navigate sang FavoriteMovieFragment

---

## 🧩 Task 5. Màn hình Favorite Movies

### UI
- Thêm RecyclerView

### RecyclerView
- Tạo adapter
- Set adapter
- Set LinearLayoutManager

### ViewModel
- Tạo FavoriteMovieViewModel
- Gọi getFavoriteMovies()
- Đưa dữ liệu ra UI

### Hiển thị
- Collect dữ liệu
- Submit list vào adapter

---

## 🧩 Task 6. Bỏ favorite

- Thêm icon remove
- Click vào icon remove -> callback đến fragment → gọi function trong viewmodel để update lại trạng thái isFavorite = false
- List data tự cập nhật lại

---
