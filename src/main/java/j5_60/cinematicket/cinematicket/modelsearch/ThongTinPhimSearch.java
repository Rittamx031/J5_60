package j5_60.cinematicket.cinematicket.modelsearch;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ThongTinPhimSearch {
  String daoDien;
  String nhaSanXuat;
  int namPhatHanh;
  int thoiLuongMin;
  int thoiLuongMax;
  int tuoiGioiHanMin;
  int tuoiGioiHanMax;
  UUID idQuocGia;
  UUID idNgonNgu;
}
