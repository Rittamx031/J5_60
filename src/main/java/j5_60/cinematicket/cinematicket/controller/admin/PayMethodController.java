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

import j5_60.cinematicket.cinematicket.model.entity.PayMethod;
import j5_60.cinematicket.cinematicket.service.admin.PayMethodService;

/**
 * PhuongThucThanhToanController
 */
@RestController
@CrossOrigin
@RequestMapping("api/cimena/phuong-thuc-thanh-toan")
public class PayMethodController {
    @Autowired
    private PayMethodService service;

    @GetMapping
    public ResponseEntity<List<PayMethod>> getAllPhuongThucThanhToan() {
        return ResponseEntity.ok().body(service.getAllPhuongThucThanhToan());
    }

    @GetMapping("{id}")
    public ResponseEntity<PayMethod> getPhuongThucThanhToanById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(service.getPhuongThucThanhToanById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<PayMethod>> getDoAnById(
            @RequestParam(value = "name", required = true) String name) {
        return ResponseEntity.ok().body(service.searchByName(name));
    }

    @GetMapping("prev-page")
    public ResponseEntity<List<PayMethod>> getPrevPage(
            @RequestParam(value = "sortby", required = false) String sortby,
            @RequestParam(value = "sortdir", required = false) String sortdir) {
        return ResponseEntity.ok().body(service.getPrevPage(sortby, sortdir));
    }

    @GetMapping("page")
    public ResponseEntity<List<PayMethod>> getPageNo(
            @RequestParam(value = "sortby", required = false) String sortby,
            @RequestParam(value = "sortdir", required = false) String sortdir,
            @RequestParam(value = "pageno", required = false) int pageNo) {
        System.out.println(pageNo + " , " + sortby + " ," + sortdir);
        return ResponseEntity.ok().body(service.getPageNo(pageNo, sortby, sortdir));
    }

    @GetMapping("next-page")
    public ResponseEntity<List<PayMethod>> getNextPage(
            @RequestParam(value = "sortby", required = false) String sortby,
            @RequestParam(value = "sortdir", required = false) String sortdir) {
        return ResponseEntity.ok().body(service.getNextPage(sortby, sortdir));
    }

    @GetMapping("delete/{id}")
    public HttpStatus delete(@PathVariable UUID id) {
        this.service.setDeteleteState(id);
        return HttpStatus.OK;
    }

    @GetMapping("panigation")
    public ResponseEntity<int[]> getPanigation() {
        return ResponseEntity.ok().body(service.getPanigation());
    }

    @PostMapping
    public ResponseEntity<PayMethod> createPhuongThucThanhToan(
            @RequestBody PayMethod phuongThucThanhToan) {
        phuongThucThanhToan.setCreateAt(LocalDateTime.now());
        return ResponseEntity.ok().body(this.service.createPhuongThucThanhToan(phuongThucThanhToan));
    }

    @PutMapping("{id}")
    public ResponseEntity<PayMethod> updatePhuongThucThanhToan(@PathVariable UUID id,
            @RequestBody PayMethod phuongThucThanhToan) {
        phuongThucThanhToan.setId(id);
        phuongThucThanhToan.setUpdateAt(LocalDateTime.now());
        return ResponseEntity.ok().body(this.service.updatePhuongThucThanhToan(phuongThucThanhToan));
    }

    @DeleteMapping("{id}")
    public HttpStatus deletePhuongThucThanhToan(@PathVariable UUID id) {
        this.service.deletePhuongThucThanhToan(id);
        return HttpStatus.OK;
    }
}