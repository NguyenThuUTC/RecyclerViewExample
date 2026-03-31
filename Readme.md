# Màn hình Movie Detail

## Tổng quan

Trong bài thực hành này, bạn sẽ mở rộng ứng dụng movie hiện tại.

Các chức năng hiện tại:

* Màn hình hiển thị **popular movies**
* Chức năng phân trang (load more)
* Đã xử lý sự kiện click và có movie `id`

📋 Bài tập:

* Xây dựng màn hình Movie Detail
* Hiển thị thông tin chi tiết phim
* Hiển thị danh sách diễn viên và thể loại

---

## 🧩 Task 1: Tạo UI cho màn hình Movie Detail

### 📝 Hướng dẫn

Tạo `MovieDetailFragment` và layout tương ứng, bao gồm:

* ImageView → poster
* TextView → title
* TextView → overview
* TextView → rating
* RecyclerView → cast (horizontal)
* FlexboxLayout → genres
* ...

## 🧩 Task 2: Nhận movie id từ màn hình trước

Lấy được `id` trong `MovieDetailFragment`

### 📝 Hướng dẫn

* Nhận dữ liệu từ arguments (Bundle / SafeArgs)
* Kiểm tra log để đảm bảo `id` không null

## 🧩 Task 3: Tạo ViewModel

Chuẩn bị ViewModel để quản lý dữ liệu cho màn hình Movie Detail

### 📝 Hướng dẫn

1. Tạo class `MovieDetailViewModel`

2. Tạo một **data class** để chứa toàn bộ dữ liệu mà UI cần hiển thị:

    * Thông tin phim (title, overview, poster, rating, genres)
    * Danh sách diễn viên (cast)

Ví dụ:

```kotlin
data class MovieDetailUiModel(
    val id: String,
    val title: String,
    val overview: String,
    val posterUrl: String,
    val rating: Double,
    val genres: List<String>,
    val cast: List<Cast>
)
```

---

3. Trong ViewModel, tạo biến để lưu dữ liệu này:

```kotlin
private val _movieDetail = MutableLiveData<MovieDetailUiModel>()
val movieDetail: LiveData<MovieDetailUiModel> = _movieDetail
```

---

## 🧩 Task 4: Tạo API

Chuẩn bị API cần thiết

---
### 📝 Hướng dẫn

Thêm các API sau vào service:

```http
GET /movie/{movie_id}
GET /movie/{movie_id}/credits
```

---


Tạo model để parse dữ liệu

→ Xem response của từng API và tạo model tương ứng

## 🧩 Task 5: Gọi API trong ViewModel

Load dữ liệu phim và cast

### 📝 Hướng dẫn

* Gọi 2 API trong ViewModel
* Có thể gọi song song = async

```kotlin
viewModelScope.launch {
    val detail = async { ... }
    val credits = async { ... }
}
```

## 🧩 Task 6: Mapping dữ liệu cho UI

Chuyển dữ liệu từ API sang dữ liệu phù hợp để hiển thị trên UI

---

### 📝 Hướng dẫn

1. Sau khi gọi API trong ViewModel, lấy dữ liệu từ response:

* movie detail
* movie credits (cast)

---

2. Chuyển đổi dữ liệu sang `MovieDetailUiModel`

* Không dùng trực tiếp dữ liệu từ API để hiển thị UI
* Chỉ sử dụng `MovieDetailUiModel`

---

3. Gán dữ liệu vào biến trong ViewModel

```kotlin
val movie = detail.await()
val credit = credits.await()

_movieDetail.value = MovieDetailUiModel(
    title = movie.title,
    overview = movie.overview,
    posterUrl = movie.posterPath,
    rating = movie.voteAverage,
    genres = movie.genres.map { it.name },
    cast = credit.cast
)
```

## 🧩 Task 7: Bind dữ liệu lên UI

Hiển thị dữ liệu phim

### 📝 Hướng dẫn

Observe dữ liệu từ ViewModel và bind:

* Poster
* Title
* Overview
* Rating
* ...

---

## 🧩 Task 8: Hiển thị danh sách Cast

Hiển thị danh sách diễn viên

* Sort theo `order`
* Lấy top 10 diễn viên

### 📝 Hướng dẫn

1. Tạo `CastAdapter`
2. Set RecyclerView dạng horizontal
3. Bind dữ liệu cast

---