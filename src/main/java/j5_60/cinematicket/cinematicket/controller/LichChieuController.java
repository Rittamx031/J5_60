// package j5_60.cinematicket.cinematicket.controller;

// import j5_60.cinematicket.cinematicket.entity.LichChieu;
// import j5_60.cinematicket.cinematicket.entity.PhongChieu;
// import j5_60.cinematicket.cinematicket.exception.LichChieuNotFoundException;
// import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;
// import j5_60.cinematicket.cinematicket.repository.LichChieuRepository;
// import j5_60.cinematicket.cinematicket.repository.PhongChieuRepository;
// import j5_60.cinematicket.cinematicket.repository.ThongTinPhimRepository;
// import j5_60.cinematicket.cinematicket.service.serviceimpl.LichChieuIF;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.*;

// import java.time.LocalDateTime;
// import java.time.LocalTime;
// import java.util.UUID;

// /**
//  * LichChieuController
//  */
// @RequestMapping("/admin/lich-chieu")
// @RestController
// @CrossOrigin(origins = "http://localhost:3000")
// public class LichChieuController {

//     @Autowired
//     LichChieuIF lichChieuSer;

//     @Autowired
//     LichChieuRepository lichChieuRepository;



//     @GetMapping("/hien-thi")
//     public Page<LichChieu> get(Pageable pageable) {
//         return lichChieuRepository.findAll(pageable);
//     }

//     @GetMapping("/{id}")
//     public LichChieu detail(@PathVariable("id")
//                                     UUID id) throws ResourceNotFoundException {
//         return lichChieuRepository.findById(id).orElseThrow(() -> new LichChieuNotFoundException(id));
//     }

//     @PostMapping("/add")
//     public LichChieu add(@RequestBody LichChieu lichChieu) {
//         lichChieu.setCreateAt(LocalDateTime.now());
//         return new ResponseEntity<>(lichChieuSer.save(lichChieu), HttpStatus.CREATED);
//     }

//     @PutMapping("/update/{id}")
//     public LichChieu update(@PathVariable("id") UUID id,
//                             @RequestBody LichChieu lichChieu) throws ResourceNotFoundException {
//         lichChieu.setUpdateAt(LocalDateTime.now());
//         return new ResponseEntity<>(lichChieuSer.update(id,lichChieu),HttpStatus.OK);
//     }

//     @DeleteMapping("/delete/{id}")
//     public void delete(@PathVariable("id") UUID id) {
//         lichChieuSer.deleteById(id);
//     }
// }