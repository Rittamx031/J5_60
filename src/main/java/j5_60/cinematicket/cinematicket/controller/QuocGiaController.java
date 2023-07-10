package j5_60.cinematicket.cinematicket.controller;

import j5_60.cinematicket.cinematicket.entity.QuocGia;
import j5_60.cinematicket.cinematicket.exception.NotFoundException;
import j5_60.cinematicket.cinematicket.repository.QuocGiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/quoc-gia")
@CrossOrigin("http://localhost:3000")
public class QuocGiaController {

    @Autowired
    QuocGiaRepository quocGiaRepository;

    @GetMapping("/hien-thi")
    public List<QuocGia> get() {
        return quocGiaRepository.findAll();
    }

    @GetMapping("/{id}")
    public QuocGia getById(@PathVariable("id") UUID id) {
        return quocGiaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    @PostMapping("/add")
    public QuocGia add(@RequestBody QuocGia quocGia) {
        LocalDateTime localDateTime = LocalDateTime.now();
        quocGia.setCreateAt(localDateTime);
        return quocGiaRepository.save(quocGia);
    }

    @PutMapping("/update/{id}")
    public QuocGia update(@PathVariable("id") UUID id,
                          @RequestBody QuocGia quocGia) {
        return quocGiaRepository.findById(id)
                .map(quocGiaMoi -> {
                    quocGiaMoi.setTen(quocGia.getTen());
                    LocalDateTime localDateTime = LocalDateTime.now();
                    quocGiaMoi.setUpdateAt(localDateTime);
                    return quocGiaRepository.save(quocGiaMoi);
                }).orElseThrow(() -> new NotFoundException(id));
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") UUID id) {
        if (!quocGiaRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        quocGiaRepository.deleteById(id);
        return "Xóa thành công quốc gia có id: " + id + ". ";
    }
}