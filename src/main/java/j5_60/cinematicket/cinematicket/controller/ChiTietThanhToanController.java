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

import j5_60.cinematicket.cinematicket.entity.ChiTietThanhToan;
import j5_60.cinematicket.cinematicket.entity.key.ChiTietThanhToanKey;
import j5_60.cinematicket.cinematicket.service.ChiTietThanhToanService;

/**
 * ChiTietThanhToanController
 */
@RestController
@CrossOrigin
@RequestMapping("cimena/chi-tiet-thanh-toan")
public class ChiTietThanhToanController {
    @Autowired
    private ChiTietThanhToanService service;

       
    @GetMapping
    public ResponseEntity<List<ChiTietThanhToan>> getAllChiTietThanhToan() {
        List<ChiTietThanhToan> chiTietThanhToanList = service.getAllChiTietThanhToan();
        return ResponseEntity.ok(chiTietThanhToanList);
    }

    @GetMapping("/{idhd}/{idpttt}")
    public ResponseEntity<ChiTietThanhToan> getChiTietThanhToanById(
            @PathVariable("idhd") UUID idhd,
            @PathVariable("idpttt") UUID idpttt) {
        ChiTietThanhToan chiTietThanhToan = service.getChiTietThanhToanById(idhd, idpttt);
        return ResponseEntity.ok(chiTietThanhToan);
    }

    // @GetMapping("/search")
    // public ResponseEntity<List<ChiTietThanhToan>> getChiTietThanhToanByName(
    //         @RequestParam("name") String name) {
    //     List<ChiTietThanhToan> chiTietThanhToanList = service.searchByName(name);
    //     return ResponseEntity.ok(chiTietThanhToanList);
    // }

    @GetMapping("/prev-page")
    public ResponseEntity<List<ChiTietThanhToan>> getPreviousPage(
            @RequestParam(value = "sortby", required = false) String sortBy,
            @RequestParam(value = "sortdir", required = false) String sortDir) {
        List<ChiTietThanhToan> chiTietThanhToanList = service.getPrevPage(sortBy, sortDir);
        return ResponseEntity.ok(chiTietThanhToanList);
    }

    @GetMapping("/page")
    public ResponseEntity<List<ChiTietThanhToan>> getPage(
            @RequestParam(value = "sortby", required = false) String sortBy,
            @RequestParam(value = "sortdir", required = false) String sortDir,
            @RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo) {
        List<ChiTietThanhToan> chiTietThanhToanList = service.getPageNo(pageNo, sortBy, sortDir);
        return ResponseEntity.ok(chiTietThanhToanList);
    }

    @GetMapping("/next-page")
    public ResponseEntity<List<ChiTietThanhToan>> getNextPage(
            @RequestParam(value = "sortby", required = false) String sortBy,
            @RequestParam(value = "sortdir", required = false) String sortDir) {
        List<ChiTietThanhToan> chiTietThanhToanList = service.getNextPage(sortBy, sortDir);
        return ResponseEntity.ok(chiTietThanhToanList);
    }

    @DeleteMapping("/{idhd}/{idpttt}")
    public ResponseEntity<Void> deleteChiTietThanhToan(
            @PathVariable("idhd") UUID idhd,
            @PathVariable("idpttt") UUID idpttt) {
        service.setDeteleteState(idhd, idpttt);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<ChiTietThanhToan> createChiTietThanhToan(
            @RequestBody ChiTietThanhToan chiTietThanhToan) {
        chiTietThanhToan.setCreateAt(LocalDateTime.now());
        ChiTietThanhToan createdChiTietThanhToan = service.createChiTietThanhToan(chiTietThanhToan);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdChiTietThanhToan);
    }

    @PutMapping("/{idhd}/{idpttt}")
    public ResponseEntity<ChiTietThanhToan> updateChiTietThanhToan(
            @PathVariable("idhd") UUID idhd,
            @PathVariable("idpttt") UUID idpttt,
            @RequestBody ChiTietThanhToan chiTietThanhToan) {
        chiTietThanhToan.setId(new ChiTietThanhToanKey(idhd, idpttt));
        chiTietThanhToan.setUpdateAt(LocalDateTime.now());
        ChiTietThanhToan updatedChiTietThanhToan = service.updateChiTietThanhToan(chiTietThanhToan);
        return ResponseEntity.ok(updatedChiTietThanhToan);
    }
}