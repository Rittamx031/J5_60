package j5_60.cinematicket.cinematicket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import j5_60.cinematicket.cinematicket.model.dto.response.MovieUserReponse;
import j5_60.cinematicket.cinematicket.model.entity.Movie;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * ThongTinPhimRepository
 */
public interface MovieRepository extends JpaRepository<Movie, UUID> {
    List<Movie> search(@Param("txtSearch") String txt);

    @Query(value = "SELECT ttp.id AS 'Id', " +
            "      ttp.ten AS 'Ten', " +
            "      ttp.dao_dien AS 'DaoDien', " +
            "      ttp.nha_san_xuat AS 'NhaSanXuat', " +
            "      ttp.dien_vien AS 'DienVien', " +
            "      ttp.nam_phat_hanh AS 'NamPhatHanh', " +
            "      ttp.thoi_luong AS 'ThoiLuong', " +
            "      ttp.tuoi_gioi_han AS 'TuoiGioiHan', " +
            "      qg.ten AS 'QuocGia', " +
            "      ng.ten AS 'NgonNgu', " +
            "      ttp.noidung AS 'NoiDung', " +
            "      ttp.trailer AS 'UrlTrailer', " +
            "      ttp.poster AS 'UrlImagePoster', " +
            "      STRING_AGG(tl.ten, ', ') WITHIN GROUP (ORDER BY tl.ten) AS 'TheLoai' " +
            "FROM ThongTinPhim ttp " +
            "JOIN QuocGia qg ON qg.id = ttp.id_quoc_gia " +
            "JOIN NgonNgu ng ON ng.id = ttp.id_ngon_ngu " +
            "LEFT JOIN TheLoaiPhim tlp ON tlp.id_film = ttp.id " +
            "LEFT JOIN TheLoai tl ON tl.id = tlp.id_the_loai " +
            "WHERE ttp.id = :idPhim " +
            "GROUP BY ttp.id, ttp.ten, ttp.dao_dien, ttp.nha_san_xuat, ttp.dien_vien, ttp.nam_phat_hanh, ttp.thoi_luong, ttp.tuoi_gioi_han, qg.ten, ng.ten, ttp.noidung, ttp.trailer, ttp.poster", nativeQuery = true)
    Optional<MovieUserReponse> getMovieResponse(@Param("idPhim") UUID idPhim);

    @Query(value = "SELECT ttp.id AS 'Id', " +
            "      ttp.ten AS 'Ten', " +
            "      ttp.dao_dien AS 'DaoDien', " +
            "      ttp.nha_san_xuat AS 'NhaSanXuat', " +
            "      ttp.dien_vien AS 'DienVien', " +
            "      ttp.nam_phat_hanh AS 'NamPhatHanh', " +
            "      ttp.thoi_luong AS 'ThoiLuong', " +
            "      ttp.tuoi_gioi_han AS 'TuoiGioiHan', " +
            "      qg.ten AS 'QuocGia', " +
            "      ng.ten AS 'NgonNgu', " +
            "      ttp.noidung AS 'NoiDung', " +
            "      ttp.trailer AS 'UrlTrailer', " +
            "      ttp.poster AS 'UrlImagePoster', " +
            "      STRING_AGG(tl.ten, ', ') WITHIN GROUP (ORDER BY tl.ten) AS 'TheLoai' " +
            "FROM ThongTinPhim ttp " +
            "JOIN QuocGia qg ON qg.id = ttp.id_quoc_gia " +
            "JOIN NgonNgu ng ON ng.id = ttp.id_ngon_ngu " +
            "LEFT JOIN TheLoaiPhim tlp ON tlp.id_film = ttp.id " +
            "LEFT JOIN TheLoai tl ON tl.id = tlp.id_the_loai " +
            "WHERE ttp.id IN (SELECT id_phim FROM LichChieu WHERE ngay_chieu = :date )" +
            "GROUP BY ttp.id, ttp.ten, ttp.dao_dien, ttp.nha_san_xuat, ttp.dien_vien, ttp.nam_phat_hanh, ttp.thoi_luong, ttp.tuoi_gioi_han, qg.ten, ng.ten, ttp.noidung, ttp.trailer, ttp.poster", nativeQuery = true)
    List<MovieUserReponse> getAllMovieDangChieu(@Param("date") LocalDate date);

    @Query(value = "SELECT ttp.id AS 'Id', " +
            "      ttp.ten AS 'Ten', " +
            "      ttp.dao_dien AS 'DaoDien', " +
            "      ttp.nha_san_xuat AS 'NhaSanXuat', " +
            "      ttp.dien_vien AS 'DienVien', " +
            "      ttp.nam_phat_hanh AS 'NamPhatHanh', " +
            "      ttp.thoi_luong AS 'ThoiLuong', " +
            "      ttp.tuoi_gioi_han AS 'TuoiGioiHan', " +
            "      qg.ten AS 'QuocGia', " +
            "      ng.ten AS 'NgonNgu', " +
            "      ttp.noidung AS 'NoiDung', " +
            "      ttp.trailer AS 'UrlTrailer', " +
            "      ttp.poster AS 'UrlImagePoster', " +
            "      STRING_AGG(tl.ten, ', ') WITHIN GROUP (ORDER BY tl.ten) AS 'TheLoai' " +
            "FROM ThongTinPhim ttp " +
            "JOIN QuocGia qg ON qg.id = ttp.id_quoc_gia " +
            "JOIN NgonNgu ng ON ng.id = ttp.id_ngon_ngu " +
            "LEFT JOIN TheLoaiPhim tlp ON tlp.id_film = ttp.id " +
            "LEFT JOIN TheLoai tl ON tl.id = tlp.id_the_loai " +
            "WHERE ttp.status = 1" +
            "GROUP BY ttp.id, ttp.ten, ttp.dao_dien, ttp.nha_san_xuat, ttp.dien_vien, ttp.nam_phat_hanh, ttp.thoi_luong, ttp.tuoi_gioi_han, qg.ten, ng.ten, ttp.noidung, ttp.trailer, ttp.poster", nativeQuery = true)
    List<MovieUserReponse> getAllMovieSapChieu();
}