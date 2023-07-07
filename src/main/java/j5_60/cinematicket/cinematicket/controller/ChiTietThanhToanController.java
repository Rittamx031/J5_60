package j5_60.cinematicket.cinematicket.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import j5_60.cinematicket.cinematicket.entity.ChiTietThanhToan;
import j5_60.cinematicket.cinematicket.entity.key.ChiTietThanhToanKey;
import j5_60.cinematicket.cinematicket.service.ChiTietThanhToanService;

/**
 * ChiTietThanhToanController
 */
@RestController
@CrossOrigin
@RequestMapping("cemina/chitietthanhtoans")
public class ChiTietThanhToanController {
    @Autowired
    private ChiTietThanhToanService service;

    @GetMapping("/index")
    public ResponseEntity<List<ChiTietThanhToan>> getAllChiTietThanhToan() {
        return ResponseEntity.ok().body(service.getAllChiTietThanhToan());
    }

    @GetMapping
    public ResponseEntity<ChiTietThanhToan> getChiTietThanhToanById(@RequestParam(value = "idhd", required = true) UUID idhd,
            @RequestParam(value = "idpttt", required = true) UUID idpttt) {
        return ResponseEntity.ok().body(service.getChiTietThanhToanById(idhd,idpttt));
    }
    // @GetMapping("/search")
    // public ResponseEntity<List<ChiTietThanhToan>>
    // getChiTietThanhToanById(@RequestParam(value ="name", required = true)
    // String name) {
    // return ResponseEntity.ok().body(service.searchByName(name));
    // }

    @GetMapping("pre")
    public ResponseEntity<List<ChiTietThanhToan>> getPrevPage(
            @RequestParam(value = "sortby", required = false) String sortby,
            @RequestParam(value = "sortdir", required = false) String sortdir) {
        return ResponseEntity.ok().body(service.getPrevPage(sortby, sortdir));
    }

    @GetMapping("pageno")
    public ResponseEntity<List<ChiTietThanhToan>> getPageNo(
            @RequestParam(value = "sortby", required = false) String sortby,
            @RequestParam(value = "sortdir", required = false) String sortdir,
            @RequestParam(value = "pageNo", required = false) int pageNo) {
        System.out.println(pageNo + " , " + sortby + " ," + sortdir);
        return ResponseEntity.ok().body(service.getPageNo(pageNo, sortby, sortdir));
    }

    @GetMapping("next")
    public ResponseEntity<List<ChiTietThanhToan>> getNextPage(
            @RequestParam(value = "sortby", required = false) String sortby,
            @RequestParam(value = "sortdir", required = false) String sortdir) {
        return ResponseEntity.ok().body(service.getNextPage(sortby, sortdir));
    }

    @GetMapping("delete")
    public HttpStatus delete(@RequestParam(value = "idhd", required = true) UUID idhd,
            @RequestParam(value = "idpttt", required = true) UUID idpttt) {
        this.service.setDeteleteState(idhd,idpttt);
        return HttpStatus.OK;
    }

    @PostMapping
    public ResponseEntity<ChiTietThanhToan> createChiTietThanhToan(@RequestBody ChiTietThanhToan person) {
        return ResponseEntity.ok().body(this.service.createChiTietThanhToan(person));
    }

    @PutMapping
    public ResponseEntity<ChiTietThanhToan> updateChiTietThanhToan(@RequestParam(value = "idhd", required = true) UUID idhd,
            @RequestParam(value = "idpttt", required = true) UUID idpttt,
            @RequestBody ChiTietThanhToan person) {
        person.setId(new ChiTietThanhToanKey(idhd,idpttt));
        return ResponseEntity.ok().body(this.service.updateChiTietThanhToan(person));
    }

    @DeleteMapping
    public HttpStatus deleteChiTietThanhToan(@RequestParam(value = "idhd", required = true) UUID idhd,
            @RequestParam(value = "idpttt", required = true) UUID idpttt) {
        this.service.deleteChiTietThanhToan(idhd,idpttt);
        return HttpStatus.OK;
    }
    
}