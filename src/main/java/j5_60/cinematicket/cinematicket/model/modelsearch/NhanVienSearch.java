package j5_60.cinematicket.cinematicket.model.modelsearch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

import j5_60.cinematicket.cinematicket.model.entity.ChucVu;

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
