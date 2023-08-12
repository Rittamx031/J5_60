package j5_60.cinematicket.cinematicket.modelsearch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KhachHangSearch {
    String maKhachHang;
    String hoTen;
    String email;
    Boolean gioiTinh;
    String sdt;
    String diaChi;
    Date ngaySinh;
    int trangThai;
}
