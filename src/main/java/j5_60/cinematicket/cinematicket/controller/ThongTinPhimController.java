package j5_60.cinematicket.cinematicket.controller;


import j5_60.cinematicket.cinematicket.entity.ThongTinPhim;
import j5_60.cinematicket.cinematicket.exception.NotFoundException;
import j5_60.cinematicket.cinematicket.repository.ThongTinPhimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/thong-tin-phim")
@CrossOrigin("http://localhost:3000")
public class ThongTinPhimController {

    @Autowired
    ThongTinPhimRepository thongTinPhimRepository;

    @GetMapping("/hien-thi")
    public List<ThongTinPhim> get() {
        return thongTinPhimRepository.findAll();
    }

    @GetMapping("/{id}")
    public ThongTinPhim getById(@PathVariable("id") UUID id) {
        return thongTinPhimRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    @PostMapping("/add")
    public ThongTinPhim add(@RequestBody ThongTinPhim thongTinPhim) {
        LocalDateTime localDateTime = LocalDateTime.now();
        thongTinPhim.setCreateAt(localDateTime);
        return thongTinPhimRepository.save(thongTinPhim);
    }

    @PutMapping("/update/{id}")
    public ThongTinPhim update(@PathVariable("id") UUID id,
                               @RequestBody ThongTinPhim thongTinPhim) {
        return thongTinPhimRepository.findById(id)
                .map(thongTinPhimUpdate -> {
                    thongTinPhimUpdate.setTen(thongTinPhim.getTen());
                    thongTinPhimUpdate.setDaoDien(thongTinPhim.getDaoDien());
                    thongTinPhimUpdate.setNhaSanXuat(thongTinPhim.getNhaSanXuat());
                    thongTinPhimUpdate.setDienVien(thongTinPhim.getDienVien());
                    thongTinPhimUpdate.setNamPhatHanh(thongTinPhim.getNamPhatHanh());
                    thongTinPhimUpdate.setThoiLuong(thongTinPhim.getThoiLuong());
                    thongTinPhimUpdate.setTuoiGioiHan(thongTinPhim.getTuoiGioiHan());
                    thongTinPhimUpdate.setQuocGia(thongTinPhim.getQuocGia());
                    thongTinPhimUpdate.setNgonNgu(thongTinPhim.getNgonNgu());
                    thongTinPhimUpdate.setNgayKhoiChieu(thongTinPhim.getNgayKhoiChieu());
                    thongTinPhimUpdate.setNoiDung(thongTinPhim.getNoiDung());
                    thongTinPhimUpdate.setTrailer(thongTinPhim.getTrailer());
                    thongTinPhimUpdate.setPoster(thongTinPhim.getPoster());
                    LocalDateTime localDateTime = LocalDateTime.now();
                    thongTinPhimUpdate.setUpdateAt(localDateTime);
                    return thongTinPhimRepository.save(thongTinPhimUpdate);
                }).orElseThrow(() -> new NotFoundException(id));
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") UUID id) {
        if (!thongTinPhimRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        thongTinPhimRepository.deleteById(id);
        return "Xoa thanh cong phim co id: " + id + ".";
    }
}