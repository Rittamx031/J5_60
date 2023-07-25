package j5_60.cinematicket.cinematicket.controller;

import java.time.LocalDateTime;
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

import j5_60.cinematicket.cinematicket.entity.HoaDon;
import j5_60.cinematicket.cinematicket.service.HoaDonService;

/**
 * HoaDonController
 */
@RestController
@CrossOrigin
@RequestMapping("cimena/hoa-don")
public class HoaDonController {
    @Autowired
    private HoaDonService service;

    @GetMapping
    public ResponseEntity<List<HoaDon>> getAllHoaDon() {
        return ResponseEntity.ok().body(service.getAllHoaDon());
    }

    @GetMapping("{id}")
    public ResponseEntity<HoaDon> getHoaDonById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(service.getHoaDonById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<HoaDon>> getSearchResult(@RequestParam(value = "txt", required = true) String txtSearch) {
        return ResponseEntity.ok().body(service.search(txtSearch));
    }

    @GetMapping("pre")
    public ResponseEntity<List<HoaDon>> getPrevPage(
            @RequestParam(value = "sortby", required = false) String sortby,
            @RequestParam(value = "sortdir", required = false) String sortdir) {
        return ResponseEntity.ok().body(service.getPrevPage(sortby, sortdir));
    }

    @GetMapping("page")
    public ResponseEntity<List<HoaDon>> getPageNo(
            @RequestParam(value = "sortby", required = false) String sortby,
            @RequestParam(value = "sortdir", required = false) String sortdir,
            @RequestParam(value = "pageno", required = false) int pageNo) {
        System.out.println(pageNo + " , " + sortby + " ," + sortdir);
        return ResponseEntity.ok().body(service.getPageNo(pageNo, sortby, sortdir));
    }

    @GetMapping("next")
    public ResponseEntity<List<HoaDon>> getNextPage(
            @RequestParam(value = "sortby", required = false) String sortby,
            @RequestParam(value = "sortdir", required = false) String sortdir) {
        return ResponseEntity.ok().body(service.getNextPage(sortby, sortdir));
    }

    @GetMapping("panigation")
    public ResponseEntity<int[]> getPanigation() {
        return ResponseEntity.ok().body(service.getPanigation());
    }

    @GetMapping("delete/{id}")
    public HttpStatus delete(@PathVariable UUID id) {
        this.service.setDeteleteState(id);
        return HttpStatus.OK;
    }

    @PostMapping
    public ResponseEntity<HoaDon> createHoaDon(@RequestBody HoaDon hoaDon) {
        hoaDon.setCreateAt(LocalDateTime.now());
        return ResponseEntity.ok().body(this.service.createHoaDon(hoaDon));
    }

    @PutMapping("{id}")
    public ResponseEntity<HoaDon> updateHoaDon(@PathVariable UUID id,
            @RequestBody HoaDon hoaDon) {
        hoaDon.setId(id);
        hoaDon.setUpdateAt(LocalDateTime.now());
        return ResponseEntity.ok().body(this.service.updateHoaDon(hoaDon));
    }

    @DeleteMapping("{id}")
    public HttpStatus deleteHoaDon(@PathVariable UUID id) {
        this.service.deleteHoaDon(id);
        return HttpStatus.OK;
    }

}