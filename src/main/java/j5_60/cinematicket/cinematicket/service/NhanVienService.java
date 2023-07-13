package j5_60.cinematicket.cinematicket.service;

import j5_60.cinematicket.cinematicket.entity.NhanVien;
import j5_60.cinematicket.cinematicket.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NhanVienService {
    @Autowired
    private NhanVienRepository nvRepository;

    public List<NhanVien> getAllNV() {
        return nvRepository.findAll();
    }

    public Page<NhanVien> findAll(Pageable pageable) {
        return nvRepository.findAll(pageable);
    }
    public Page<NhanVien> sapXep(Pageable pageable) {
        return nvRepository.findAllByOrderByHoTenDesc(pageable);
    }

    public NhanVien add(NhanVien nv) {
      return nvRepository.save(nv);
    }

    public NhanVien update(UUID id, NhanVien nhanVien) {
        NhanVien nv = nvRepository.findById(id).get();
        nv.setHoTen(nhanVien.getHoTen());
        nv.setEmail(nhanVien.getEmail());
        nv.setGioiTinh(nhanVien.isGioiTinh());
        nv.setNgaySinh(nhanVien.getNgaySinh());
        nv.setPass(nhanVien.getPass());
        nv.setSdt(nhanVien.getSdt());
        nv.setIdCV(nhanVien.getIdCV());
        nv.setImagaNV(nhanVien.getImagaNV());
        nv.setTrangThai(nhanVien.getTrangThai());
        nv.setUpdateAt(nhanVien.getUpdateAt());
        return nvRepository.save(nv);
    }

    public void delete(UUID id) {
        NhanVien nv = nvRepository.findById(id).get();
        nvRepository.delete(nv);
    }

    public NhanVien findById(UUID id) {
        return nvRepository.findById(id).get();
    }
}
