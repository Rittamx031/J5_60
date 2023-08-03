package j5_60.cinematicket.cinematicket.controller;

import j5_60.cinematicket.cinematicket.entity.ThongTinPhim;
import j5_60.cinematicket.cinematicket.entity.ThongTinPhim;
import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;
import j5_60.cinematicket.cinematicket.repository.ThongTinPhimRepository;
import j5_60.cinematicket.cinematicket.service.ThongTinPhimService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("cimena/thong-tin-phim")
@CrossOrigin
public class ThongTinPhimController {

    @Autowired
    private ThongTinPhimService service;

    @GetMapping
    public ResponseEntity<List<ThongTinPhim>> getAllThongTinPhim() {
        return ResponseEntity.ok().body(service.getAllThongTinPhim());
    }

    @GetMapping("{id}")
    public ResponseEntity<ThongTinPhim> getThongTinPhimById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(service.getThongTinPhimById(id));
    }

    // @GetMapping("/search")
    // public ResponseEntity<List<ThongTinPhim>> getSearchResult(
    // @RequestParam(value = "txt", required = true) String txtSearch) {
    // return ResponseEntity.ok().body(service.search(txtSearch));
    // }

    @GetMapping("pre")
    public ResponseEntity<List<ThongTinPhim>> getPrevPage(
            @RequestParam(value = "sortby", required = false) String sortby,
            @RequestParam(value = "sortdir", required = false) String sortdir) {
        return ResponseEntity.ok().body(service.getPrevPage(sortby, sortdir));
    }

    @GetMapping("page")
    public ResponseEntity<List<ThongTinPhim>> getPageNo(
            @RequestParam(value = "sortby", required = false) String sortby,
            @RequestParam(value = "sortdir", required = false) String sortdir,
            @RequestParam(value = "pageno", required = false) int pageNo) {
        System.out.println(pageNo + " , " + sortby + " ," + sortdir);
        return ResponseEntity.ok().body(service.getPageNo(pageNo, sortby, sortdir));
    }

    @GetMapping("next")
    public ResponseEntity<List<ThongTinPhim>> getNextPage(
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
    public ResponseEntity<ThongTinPhim> createThongTinPhim(@RequestBody ThongTinPhim thongTinPhim) {
        thongTinPhim.setCreateAt(LocalDateTime.now());
        return ResponseEntity.ok().body(this.service.createThongTinPhim(thongTinPhim));
    }

    @PutMapping("{id}")
    public ResponseEntity<ThongTinPhim> updateThongTinPhim(@PathVariable UUID id,
            @RequestBody ThongTinPhim thongTinPhim) {
        thongTinPhim.setId(id);
        thongTinPhim.setUpdateAt(LocalDateTime.now());
        return ResponseEntity.ok().body(this.service.updateThongTinPhim(thongTinPhim));
    }

    @DeleteMapping("{id}")
    public HttpStatus deleteThongTinPhim(@PathVariable UUID id) {
        this.service.deleteThongTinPhim(id);
        return HttpStatus.OK;
    }

}