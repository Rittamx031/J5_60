package j5_60.cinematicket.cinematicket.model.modelsearch;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class HoaDonSearch {
  int tongGiaMin;
  int tongGiaMax;
  LocalDateTime thoiGianThanhToanMin;
  LocalDateTime thoiGianThanhToanMax;
  int trangThai;
  UUID idNhanVien;
  UUID idKhachHang;
  UUID idPhuongThucThanhToan;
}
