package j5_60.cinematicket.cinematicket.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import j5_60.cinematicket.cinematicket.entity.PhuongThucThanhToan;
import j5_60.cinematicket.cinematicket.service.PhuongThucThanhToanService;

/**
 * PhuongThucThanhToanController
 */
@RestController
@CrossOrigin
@RequestMapping("cemina/phuongthucthanhtoans")
public class PhuongThucThanhToanController {
    @Autowired
    private PhuongThucThanhToanService service;

    @GetMapping
    public ResponseEntity<List<PhuongThucThanhToan>> getAllPhuongThucThanhToan() {
        return ResponseEntity.ok().body(service.getAllPhuongThucThanhToan());
    }

    @GetMapping("{id}")
    public ResponseEntity<PhuongThucThanhToan> getPhuongThucThanhToanById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(service.getPhuongThucThanhToanById(id));
    }
    // @GetMapping("/search")
    // public ResponseEntity<List<PhuongThucThanhToan>>
    // getPhuongThucThanhToanById(@RequestParam(value ="name", required = true)
    // String name) {
    // return ResponseEntity.ok().body(service.searchByName(name));
    // }

    @GetMapping("pre")
    public ResponseEntity<List<PhuongThucThanhToan>> getPrevPage(
            @RequestParam(value = "sortby", required = false) String sortby,
            @RequestParam(value = "sortdir", required = false) String sortdir) {
        return ResponseEntity.ok().body(service.getPrevPage(sortby, sortdir));
    }

    @GetMapping("pageno")
    public ResponseEntity<List<PhuongThucThanhToan>> getPageNo(
            @RequestParam(value = "sortby", required = false) String sortby,
            @RequestParam(value = "sortdir", required = false) String sortdir,
            @RequestParam(value = "pageNo", required = false) int pageNo) {
        System.out.println(pageNo + " , " + sortby + " ," + sortdir);
        return ResponseEntity.ok().body(service.getPageNo(pageNo, sortby, sortdir));
    }

    @GetMapping("next")
    public ResponseEntity<List<PhuongThucThanhToan>> getNextPage(
            @RequestParam(value = "sortby", required = false) String sortby,
            @RequestParam(value = "sortdir", required = false) String sortdir) {
        return ResponseEntity.ok().body(service.getNextPage(sortby, sortdir));
    }

    @GetMapping("delete/{id}")
    public HttpStatus delete(@PathVariable UUID id) {
        this.service.setDeteleteState(id);
        return HttpStatus.OK;
    }

    @PostMapping
    public ResponseEntity<PhuongThucThanhToan> createPhuongThucThanhToan(@RequestBody PhuongThucThanhToan person) {
        return ResponseEntity.ok().body(this.service.createPhuongThucThanhToan(person));
    }

    @PutMapping("{id}")
    public ResponseEntity<PhuongThucThanhToan> updatePhuongThucThanhToan(@PathVariable UUID id,
            @RequestBody PhuongThucThanhToan person) {
        person.setId(id);
        return ResponseEntity.ok().body(this.service.updatePhuongThucThanhToan(person));
    }

    @DeleteMapping("{id}")
    public HttpStatus deletePhuongThucThanhToan(@PathVariable UUID id) {
        this.service.deletePhuongThucThanhToan(id);
        return HttpStatus.OK;
    }
}