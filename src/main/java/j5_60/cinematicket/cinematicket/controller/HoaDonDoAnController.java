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

import j5_60.cinematicket.cinematicket.entity.HoaDonDoAn;
import j5_60.cinematicket.cinematicket.entity.key.HoaDonDoAnKey;
import j5_60.cinematicket.cinematicket.service.HoaDonDoAnService;

/**
 * HoaDonDoAnController
 */
@RestController
@RequestMapping("cimena/hoa-don-do-an")
@CrossOrigin
public class HoaDonDoAnController {
    @Autowired
    private HoaDonDoAnService service;

    @GetMapping
    public ResponseEntity<List<HoaDonDoAn>> getAll() {
        return ResponseEntity.ok().body(service.getAllHoaDonDoAn());
    }

    @GetMapping("{idhoadon}/{idcombo}")
    public ResponseEntity<HoaDonDoAn> getOne(
            @PathVariable("idhoadon") UUID idhoadon,
            @PathVariable("idcombo") UUID idcombo) {
        return ResponseEntity.ok().body(service.getHoaDonDoAnById(idcombo, idhoadon));
    }

    @GetMapping("/hoadon/{idhoadon}")
    public ResponseEntity<List<HoaDonDoAn>> getHoaDonDoAnbyHoaDonId(
            @PathVariable("idhoadon") UUID idhoadon) {
        return ResponseEntity.ok().body(service.getHoaDonDoAnByHoaDonId(idhoadon));
    }

    @GetMapping("page")
    public ResponseEntity<List<HoaDonDoAn>> getPageNo(@RequestParam(value = "sortby", required = false) String sortby,
            @RequestParam(value = "sortdir", required = false) String sortdir,
            @RequestParam(value = "pageno", required = false) int pageNo) {
        return ResponseEntity.ok().body(service.getPageNo(pageNo, sortby, sortdir));
    }

    @GetMapping("prev")
    public ResponseEntity<List<HoaDonDoAn>> getPrePage(
            @RequestParam(value = "sortby", required = false) String sortby,
            @RequestParam(value = "sortdir", required = false) String sortdir) {
        return ResponseEntity.ok().body(service.getPrevPage(sortby, sortdir));
    }

    @GetMapping("next")
    public ResponseEntity<List<HoaDonDoAn>> getNextPage(
            @RequestParam(value = "sortby", required = false) String sortby,
            @RequestParam(value = "sortdir", required = false) String sortdir) {
        return ResponseEntity.ok().body(service.getNextPage(sortby, sortdir));
    }

    @GetMapping("delete/{idhoadon}/{idcombo}")
    public HttpStatus delete(@PathVariable("idhoadon") UUID idhoadon, @PathVariable("idcombo") UUID idcombo) {
        this.service.setDeteleteState(idcombo, idhoadon);
        return HttpStatus.OK;
    }

    @PostMapping
    public ResponseEntity<HoaDonDoAn> createHoaDon(@RequestBody HoaDonDoAn hoaDonDoAn) {
        hoaDonDoAn.setCreateAt(LocalDateTime.now());
        return ResponseEntity.ok().body(this.service.createHoaDonDoAn(hoaDonDoAn));
    }

    @PutMapping("{idhoadon}/{idcombo}")
    public ResponseEntity<HoaDonDoAn> updateHoaDon(@PathVariable("idhoadon") UUID idhoadon,
            @PathVariable("idcombo") UUID idcombo,
            @RequestBody HoaDonDoAn hoaDonDoAn) {
        hoaDonDoAn.setId(new HoaDonDoAnKey(idcombo, idhoadon));
        hoaDonDoAn.setUpdateAt(LocalDateTime.now());
        return ResponseEntity.ok().body(this.service.updateHoaDonDoAn(hoaDonDoAn));
    }

    @DeleteMapping("{idhoadon}/{idcombo}")
    public HttpStatus deleteHoaDon(@PathVariable("idhoadon") UUID idhoadon, @PathVariable("idcombo") UUID idcombo) {
        this.service.deleteHoaDonDoAn(idcombo, idhoadon);
        return HttpStatus.OK;
    }
}