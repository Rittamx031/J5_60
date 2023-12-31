package j5_60.cinematicket.cinematicket.controller;

import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;
import j5_60.cinematicket.cinematicket.model.entity.SeatType;
import j5_60.cinematicket.cinematicket.service.SeatTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("api/cimena/loaighe")
public class TypeSeatController {

    @Autowired
    private SeatTypeService loaiGheService;

     @GetMapping("/index")
     public ResponseEntity<Page<SeatType>> getAll(@RequestParam(defaultValue = "1") int page,
                                                 @RequestParam(defaultValue = "5") int size) {
         if (page < 1) page = 1;
         Pageable pageable = PageRequest.of(page - 1, size);
         Page<SeatType> loaiGhePage = loaiGheService.findAll(pageable);
         return ResponseEntity.ok().body(loaiGhePage);
     }

    @GetMapping("/{id}")
    public ResponseEntity<SeatType> getById(@PathVariable("id") UUID id) throws ResourceNotFoundException {
        SeatType loaiGhe = loaiGheService.findById(id);
        return ResponseEntity.ok().body(loaiGhe);
    }

    @PostMapping("/add")
    public ResponseEntity<SeatType> addLoaiGhe(@RequestBody @Valid SeatType loaiGhe) {
        SeatType savedLoaiGhe = loaiGheService.save(loaiGhe);
        return ResponseEntity.ok().body(savedLoaiGhe);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SeatType> updateLoaiGhe(@PathVariable("id") UUID id, @RequestBody @Valid SeatType loaiGhe) throws ResourceNotFoundException {
        SeatType existingLoaiGhe = loaiGheService.findById(id);
        existingLoaiGhe.setTen(loaiGhe.getTen());
        existingLoaiGhe.setTrangThai(loaiGhe.getTrangThai());
        existingLoaiGhe.setUpdateAt(loaiGhe.getUpdateAt());
        // Set other properties of LoaiGhe as needed

        SeatType updatedLoaiGhe = loaiGheService.updateLoaiGhe(id, existingLoaiGhe);
        return ResponseEntity.ok().body(updatedLoaiGhe);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteLoaiGhe(@PathVariable("id") UUID id) {
        loaiGheService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<SeatType>> searchLoaiGhe(@RequestParam("keyword") String keyword) {
        List<SeatType> loaiGheList = loaiGheService.searchLoaiGhe(keyword);
        return ResponseEntity.ok().body(loaiGheList);
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<SeatType>> getSortedLoaiGheList() {
        List<SeatType> sortedLoaiGheList = loaiGheService.sapXep();
        return ResponseEntity.ok().body(sortedLoaiGheList);
    }


      @GetMapping
    public ResponseEntity<List<SeatType>> getAll() {
        return ResponseEntity.ok().body(loaiGheService.getAll());
    }
 
}