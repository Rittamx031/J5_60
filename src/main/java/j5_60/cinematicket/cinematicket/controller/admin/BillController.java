package j5_60.cinematicket.cinematicket.controller.admin;

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

import j5_60.cinematicket.cinematicket.model.entity.Bill;
import j5_60.cinematicket.cinematicket.model.modelsearch.HoaDonSearch;
import j5_60.cinematicket.cinematicket.service.admin.BillService;

/**
 * HoaDonController
 */
@RestController
@CrossOrigin
@RequestMapping("api/cimena/hoa-don")
public class BillController {
    @Autowired
    private BillService service;

    @GetMapping
    public ResponseEntity<List<Bill>> getAllHoaDon() {
        return ResponseEntity.ok().body(service.getAllHoaDon());
    }

    @GetMapping("{id}")
    public ResponseEntity<Bill> getHoaDonById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(service.getHoaDonById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Bill>> getSearchResult(
            @RequestParam(value = "txt", required = true) String txtSearch) {
        return ResponseEntity.ok().body(service.search(txtSearch));
    }

    @GetMapping("pre")
    public ResponseEntity<List<Bill>> getPrevPage(
            @RequestParam(value = "sortby", required = false) String sortby,
            @RequestParam(value = "sortdir", required = false) String sortdir) {
        return ResponseEntity.ok().body(service.getPrevPage(sortby, sortdir));
    }

    @GetMapping("page")
    public ResponseEntity<List<Bill>> getPageNo(
            @RequestParam(value = "sortby", required = false) String sortby,
            @RequestParam(value = "sortdir", required = false) String sortdir,
            @RequestParam(value = "pageno", required = false) int pageNo) {
        System.out.println(pageNo + " , " + sortby + " ," + sortdir);
        return ResponseEntity.ok().body(service.getPageNo(pageNo, sortby, sortdir));
    }

    @GetMapping("next")
    public ResponseEntity<List<Bill>> getNextPage(
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
    public ResponseEntity<Bill> createHoaDon(@RequestBody Bill hoaDon) {
        hoaDon.setCreateAt(LocalDateTime.now());
        return ResponseEntity.ok().body(this.service.createHoaDon(hoaDon));
    }

    @PostMapping("/fillter")
    public ResponseEntity<List<Bill>> fillter(@RequestBody HoaDonSearch hoaDon) {
        return ResponseEntity.ok().body(this.service.fillterHoaDon(hoaDon));
    }

    @PutMapping("{id}")
    public ResponseEntity<Bill> updateHoaDon(@PathVariable UUID id,
            @RequestBody Bill hoaDon) {
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