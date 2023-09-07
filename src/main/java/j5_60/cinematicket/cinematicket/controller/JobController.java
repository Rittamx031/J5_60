package j5_60.cinematicket.cinematicket.controller;

import j5_60.cinematicket.cinematicket.model.entity.Job;
import j5_60.cinematicket.cinematicket.service.JobService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/cimena/chuc-vu")
@CrossOrigin
public class JobController {

    @Autowired
    private JobService service;

//    @GetMapping
//    public ResponseEntity hienThi() {
//        return new ResponseEntity(chucVuService.getAll1(), HttpStatus.OK);
//    }


    @GetMapping("/hien-thi")
    public ResponseEntity hienThi( @RequestParam(defaultValue = "1") int page) {
        if (page < 1) page = 1;
        Pageable pageable = PageRequest.of(page - 1, 5);
        return ResponseEntity.ok().body(service.findAll(pageable));
    }

    @PostMapping("/add")
    public ResponseEntity<Job> addChucVu(@RequestBody Job chucVu) {
        chucVu.setCreateAt(LocalDateTime.now());
        return new ResponseEntity<>(service.add(chucVu), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Job> updateChucVu(@PathVariable("id") UUID id, @RequestBody Job chucVu) {
        chucVu.setUpdateAt(LocalDateTime.now());
        return new ResponseEntity<>(service.update(id, chucVu), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") UUID id) {
        service.delete(id);
    }

    @GetMapping("/detail/{id}")
    public HttpStatus detail(@PathVariable("id") UUID id) {
        service.getById(id);
        return HttpStatus.OK;
    }

    @GetMapping("getall")
    public ResponseEntity<List<Job>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @GetMapping("pageno/{page}")
    public ResponseEntity<List<Job>> getPageNo(@PathVariable("page") int pageno) {
        return ResponseEntity.ok().body(service.getPageNo(pageno));
    }
}