package j5_60.cinematicket.cinematicket.controller;

import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;
import j5_60.cinematicket.cinematicket.model.entity.Gener;
import j5_60.cinematicket.cinematicket.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/cimena/the-loai")
@CrossOrigin
public class GenreController {

    @Autowired
    GenreRepository theLoaiRepository;

    @GetMapping("/hien-thi")
    public List<Gener> get() {
        return theLoaiRepository.findAll();
    }

    @GetMapping("/{id}")
    public Gener getById(@PathVariable("id") UUID id) {
        return theLoaiRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not find The  Loai With id= " + id));
    }

    @PostMapping("/add")
    public Gener add(@RequestBody Gener theLoai) {
        LocalDateTime localDateTime = LocalDateTime.now();
        theLoai.setCreateAt(localDateTime);
        return theLoaiRepository.save(theLoai);
    }

    @PutMapping("/update/{id}")
    public Gener update(@PathVariable("id") UUID id,
            @RequestBody Gener theLoai) {
        return theLoaiRepository.findById(id)
                .map(theLoaiUpdate -> {
                    theLoaiUpdate.setTen(theLoai.getTen());
                    theLoaiUpdate.setMoTa(theLoai.getMoTa());
                    LocalDateTime localDateTime = LocalDateTime.now();
                    theLoaiUpdate.setUpdateAt(localDateTime);
                    return theLoaiRepository.save(theLoaiUpdate);
                }).orElseThrow(() -> new ResourceNotFoundException("not find The loai With id= " + id));
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") UUID id) {
        if (!theLoaiRepository.existsById(id)) {
            throw new ResourceNotFoundException("not find The loai With id= " + id);
        }
        theLoaiRepository.deleteById(id);
        return "Xoa thanh cong the loai co id: " + id + ".";
    }
}