package j5_60.cinematicket.cinematicket.controller;


import j5_60.cinematicket.cinematicket.entity.LichChieu;
import j5_60.cinematicket.cinematicket.service.LichChieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("cimena/lich-chieu")
public class LichChieuController {
    @Autowired
    private LichChieuService lcSer;

    @GetMapping
    public ResponseEntity<List<LichChieu>> getAllLichChieu(){
        return ResponseEntity.ok().body(lcSer.getAllLichChieu());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LichChieu> getLichChieuById(@PathVariable("id") UUID id){
        return ResponseEntity.ok().body(lcSer.getLichChieuById(id));
    }

    @GetMapping("prev")
    public ResponseEntity<List<LichChieu>> getPrevPage(
            @RequestParam(value = "sortby",required = false) String srotby,
            @RequestParam(value = "sortdir",required = false)String sortdir
    ){
        return ResponseEntity.ok().body(lcSer.getPrevPage(srotby,sortdir));
    }
    @GetMapping("pageno")
    public ResponseEntity<List<LichChieu>> getPageNo(
            @RequestParam(value = "sortby",required = false)String sortby,
            @RequestParam(value = "sortdir",required = false)String sortdir,
            @RequestParam(value = "pageNo",required = false)int pageNo
            ) {
        System.out.println(pageNo+" , "+sortby+" , "+sortdir);
        return ResponseEntity.ok().body(lcSer.getPageNo(pageNo,sortby,sortdir));
    }

    @GetMapping("next")
    public ResponseEntity<List<LichChieu>> getNextPage(
            @RequestParam(value = "sortby", required = false) String sortby,
            @RequestParam(value = "sortdir", required = false) String sortdir) {
        return ResponseEntity.ok().body(lcSer.getNextPage(sortby, sortdir));
    }
    @GetMapping("delete/{id}")
    public HttpStatus delete(@PathVariable UUID id) {
        this.lcSer.setDeleteState(id);
        return HttpStatus.OK;
    }

    @PostMapping
    public ResponseEntity<LichChieu> createCombo(@RequestBody LichChieu lichChieu) {
        lichChieu.setCreateAt(LocalDateTime.now());
        return ResponseEntity.ok().body(this.lcSer.addLichChieu(lichChieu));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<LichChieu> updateCombo(@PathVariable UUID id,
                                             @RequestBody LichChieu lichChieu) {
        lichChieu.setId(id);
        lichChieu.setUpdateAt(LocalDateTime.now());
        return ResponseEntity.ok().body(this.lcSer.updateLichChieu(lichChieu));
    }

    @DeleteMapping("{id}")
    public HttpStatus deleteCombo(@PathVariable UUID id) {
        this.lcSer.deleteLichChieu(id);
        return HttpStatus.OK;
    }


}
