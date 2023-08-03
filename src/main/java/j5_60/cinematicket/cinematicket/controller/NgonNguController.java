package j5_60.cinematicket.cinematicket.controller;

import j5_60.cinematicket.cinematicket.entity.NgonNgu;
import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;
import j5_60.cinematicket.cinematicket.repository.NgonNguRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("cimena/ngon-ngu")
public class NgonNguController {

    @Autowired
    NgonNguRepository ngonNguRepository;

    @GetMapping
    public List<NgonNgu> get() {
        return ngonNguRepository.findAll();
    }

    @GetMapping("/{id}")
    public NgonNgu getById(@PathVariable("id") UUID id) {
        return ngonNguRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not find Ngon ngu With id= " + id));
    }

    @PostMapping("/add")
    public NgonNgu add(@RequestBody NgonNgu ngonNgu) {
        LocalDateTime localDateTime = LocalDateTime.now();
        ngonNgu.setCreateAt(localDateTime);
        return ngonNguRepository.save(ngonNgu);
    }

    @PutMapping("/update/{id}")
    public NgonNgu update(@RequestBody NgonNgu ngonNgu,
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