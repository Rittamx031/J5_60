package j5_60.cinematicket.cinematicket.controller.admin;

import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;
import j5_60.cinematicket.cinematicket.model.entity.Coutry;
import j5_60.cinematicket.cinematicket.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/cimena/quoc-gia")
@CrossOrigin
public class CountryController {

    @Autowired
    CountryRepository quocGiaRepository;

    @GetMapping("/hien-thi")
    public List<Coutry> get() {
        return quocGiaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Coutry getById(@PathVariable("id") UUID id) {
        return quocGiaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not find Quoc Gia With id= " + id));
    }

    @PostMapping("/add")
    public Coutry add(@RequestBody Coutry quocGia) {
        LocalDateTime localDateTime = LocalDateTime.now();
        quocGia.setCreateAt(localDateTime);
        return quocGiaRepository.save(quocGia);
    }

    @PutMapping("/update/{id}")
    public Coutry update(@PathVariable("id") UUID id,
            @RequestBody Coutry quocGia) {
        return quocGiaRepository.findById(id)
                .map(quocGiaMoi -> {
                    quocGiaMoi.setTen(quocGia.getTen());
                    LocalDateTime localDateTime = LocalDateTime.now();
                    quocGiaMoi.setUpdateAt(localDateTime);
                    return quocGiaRepository.save(quocGiaMoi);
                }).orElseThrow(() -> new ResourceNotFoundException("not find Quoc Gia With id= " + id));
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") UUID id) {
        if (!quocGiaRepository.existsById(id)) {
            throw new ResourceNotFoundException("not find Quoc Gia With id= " + id);
        }
        quocGiaRepository.deleteById(id);
        return "Xóa thành công quốc gia có id: " + id + ". ";
    }
}