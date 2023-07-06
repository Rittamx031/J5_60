package j5_60.cinematicket.cinematicket.controller;

import j5_60.cinematicket.cinematicket.entity.ChucVu;
import j5_60.cinematicket.cinematicket.service.ChucVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;


@RestController
@RequestMapping("/admin/chuc-vu")
@CrossOrigin
public class ChucVuController {

    @Autowired
    private ChucVuService chucVuService;

//    @GetMapping
//    public ResponseEntity hienThi() {
//        return new ResponseEntity(chucVuService.getAll1(), HttpStatus.OK);
//    }


    @GetMapping
    public ResponseEntity hienThi( @RequestParam(defaultValue = "1") int page) {
        if (page < 1) page = 1;
        Pageable pageable = PageRequest.of(page - 1, 5);
        return new ResponseEntity(chucVuService.findAll(pageable), HttpStatus.OK);
    }



    @PostMapping("/add")
    public ResponseEntity<ChucVu> addChucVu(@RequestBody ChucVu chucVu) {
        chucVu.setCreateAt(LocalDateTime.now());
        return new ResponseEntity<>(chucVuService.add(chucVu), HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ChucVu> updateChucVu(@PathVariable("id") UUID id, @RequestBody ChucVu chucVu) {
        chucVu.setUpdateAt(LocalDateTime.now());
        return new ResponseEntity<>(chucVuService.update(id, chucVu), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") UUID id) {
        chucVuService.delete(id);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity detail(@PathVariable("id") UUID id) {
        return new ResponseEntity(chucVuService.getById(id), HttpStatus.OK);
    }
}