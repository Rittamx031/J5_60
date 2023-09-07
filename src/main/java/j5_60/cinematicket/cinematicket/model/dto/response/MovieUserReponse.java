package j5_60.cinematicket.cinematicket.model.dto.response;

import java.util.UUID;

public interface MovieUserReponse {
  UUID getId();

  String getTen();

  String getTheLoai();

  String getUrlImagePoster();

  String getUrlTrailer();

  String getDaoDien();

  String getNhaSanXuat();

  String getDienVien();

  int getThoiLuong();

  int getNamPhatHanh();

  int getTuoiGioiHan();

  String getNoiDung();

  String getQuocGia();

  String getNgonNgu();
}
