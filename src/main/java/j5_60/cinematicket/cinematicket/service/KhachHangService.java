package j5_60.cinematicket.cinematicket.service;

import j5_60.cinematicket.cinematicket.entity.KhachHang;
import j5_60.cinematicket.cinematicket.entity.ThongTinPhim;
import j5_60.cinematicket.cinematicket.modelsearch.KhachHangSearch;
import j5_60.cinematicket.cinematicket.modelsearch.ThongTinPhimSearch;
import j5_60.cinematicket.cinematicket.repository.KhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class KhachHangService {
    @Autowired
    private KhachHangRepository khRepository;

    public List<KhachHang> getAllKH() {
        return khRepository.findAll();
    }

    public Page<KhachHang> findAllKH(Pageable pageable) {
        return khRepository.findAll(pageable);
    }

    public Page<KhachHang> sapXepTheoNgayTao(Pageable pageable) {
        return khRepository.findAllByOrderByCreateAtDesc(pageable);
    }

    public KhachHang add(KhachHang kh) {
     return khRepository.save(kh);
    }

    public KhachHang update(UUID id, KhachHang khachHang) {
        KhachHang kh = khRepository.findById(id).get();
        kh.setHoTen(khachHang.getHoTen());
        kh.setEmail(khachHang.getEmail());
        kh.setPass(khachHang.getPass());
        kh.setGioiTinh(khachHang.getGioiTinh());
        kh.setNgaySinh(khachHang.getNgaySinh());
        kh.setSdt(khachHang.getSdt());
        kh.setTrangThai(khachHang.getTrangThai());
        kh.setUpdateAt(khachHang.getUpdateAt());
     return khRepository.save(kh);
    }

    public void delete(UUID id) {
        KhachHang kh = khRepository.findById(id).get();
        khRepository.delete(kh);
    }

    public KhachHang findById(UUID id) {
        return khRepository.findById(id).get();
    }

    public List<KhachHang> fillterKhachHang(KhachHangSearch khachHangSearch) {
        List<KhachHang> result = khRepository.getKhachHangListFilter(khachHangSearch);
        return result;
    }
}
