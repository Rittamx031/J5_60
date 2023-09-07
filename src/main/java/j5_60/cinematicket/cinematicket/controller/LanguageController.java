package j5_60.cinematicket.cinematicket.controller;

import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;
import j5_60.cinematicket.cinematicket.model.entity.Language;
import j5_60.cinematicket.cinematicket.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("api/cimena/ngon-ngu")
public class LanguageController {

    @Autowired
    LanguageRepository ngonNguRepository;

    @GetMapping
    public List<Language> get() {
        return ngonNguRepository.findAll();
    }

    @GetMapping("/{id}")
    public Language getById(@PathVariable("id") UUID id) {
        return ngonNguRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not find Ngon ngu With id= " + id));
    }

    @PostMapping("/add")
    public Language add(@RequestBody Language ngonNgu) {
        LocalDateTime localDateTime = LocalDateTime.now();
        ngonNgu.setCreateAt(localDateTime);
        return ngonNguRepository.save(ngonNgu);
    }

    @PutMapping("/update/{id}")
    public Language update(@RequestBody Language ngonNgu,
            @PathVariable("id") UUID id) {
        return ngonNguRepository.findById(id)
                .map(ngonNguMoi -> {
                    ngonNguMoi.setTen(ngonNgu.getTen());
                    LocalDateTime localDateTime = LocalDateTime.now();
                    ngonNguMoi.setUpdateAt(localDateTime);
                    return ngonNguRepository.save(ngonNguMoi);
                }).orElseThrow(() -> new ResourceNotFoundException("not find Ngon ngu With id= " + id));
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") UUID id) {
        if (!ngonNguRepository.existsById(id)) {
            throw new ResourceNotFoundException("not find Ngon ngu With id= " + id);
        }
        ngonNguRepository.deleteById(id);
        return "Xóa thành công ngôn ngữ có id: " + id + ". ";
    }

}