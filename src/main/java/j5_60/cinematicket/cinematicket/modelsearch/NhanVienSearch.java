package j5_60.cinematicket.cinematicket.modelsearch;

import j5_60.cinematicket.cinematicket.entity.ChucVu;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NhanVienSearch {
    String maNV;
    String hoTen;
    String email;
    Boolean gioiTinh;
    String sdt;
    Date ngaySinh;
    ChucVu chucVu;
    int trangThai;
}
