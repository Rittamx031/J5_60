package j5_60.cinematicket.cinematicket.controller;


import j5_60.cinematicket.cinematicket.model.entity.ShowTimes;
import j5_60.cinematicket.cinematicket.service.ShowTimeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("api/cimena/lich-chieu")
public class ShowTimeController {
    @Autowired
    private ShowTimeService lcSer;

    @GetMapping
    public ResponseEntity<List<ShowTimes>> getAllLichChieu() {
        return ResponseEntity.ok().body(lcSer.getAllLichChieu());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowTimes> getLichChieuById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok().body(lcSer.getLichChieuById(id));
    }

    @GetMapping("prev")
    public ResponseEntity<List<ShowTimes>> getPrevPage(
            @RequestParam(value = "sortby", required = false) String sortBy,
            @RequestParam(value = "sortdir", required = false) String sortDir) {
        return ResponseEntity.ok().body(lcSer.getPrevPage(sortBy, sortDir));
    }

    @GetMapping("pageno")
    public ResponseEntity<List<ShowTimes>> getPageNo(
            @RequestParam(value = "sortby", required = false) String sortBy,
            @RequestParam(value = "sortdir", required = false) String sortDir,
            @RequestParam(value = "pageNo", required = false) int pageNo) {
        System.out.println(pageNo + " , " + sortBy + " , " + sortDir);
        return ResponseEntity.ok().body(lcSer.getPageNo(pageNo, sortBy, sortDir));
    }

    @GetMapping("next")
    public ResponseEntity<List<ShowTimes>> getNextPage(
            @RequestParam(value = "sortby", required = false) String sortBy,
            @RequestParam(value = "sortdir", required = false) String sortDir) {
        return ResponseEntity.ok().body(lcSer.getNextPage(sortBy, sortDir));
    }

    @GetMapping("delete/{id}")
    public HttpStatus delete(@PathVariable UUID id) {
        this.lcSer.setDeleteState(id);
        return HttpStatus.OK;
    }

    @PostMapping
    public ResponseEntity<ShowTimes> createLichChieu(@RequestBody @Valid ShowTimes lichChieu) {
        lichChieu.setCreateAt(LocalDateTime.now());
        return ResponseEntity.ok().body(this.lcSer.addLichChieu(lichChieu));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ShowTimes> updateLichChieu(@PathVariable UUID id,
                                                     @RequestBody @Valid ShowTimes lichChieu) {
        lichChieu.setId(id);
        lichChieu.setUpdateAt(LocalDateTime.now());
        return ResponseEntity.ok().body(this.lcSer.updateLichChieu(lichChieu));
    }

    @DeleteMapping("{id}")
    public HttpStatus deleteLichChieu(@PathVariable UUID id) {
        this.lcSer.deleteLichChieu(id);
        return HttpStatus.OK;
    }
}
