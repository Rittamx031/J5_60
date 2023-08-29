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

import j5_60.cinematicket.cinematicket.model.entity.GiaVeLichChieu;
import j5_60.cinematicket.cinematicket.model.entity.key.GiaVeLichChieuKey;
import j5_60.cinematicket.cinematicket.service.admin.ShowtimePriceService;

/**
 * GiaVeLichChieuController
 */
@RestController
@RequestMapping("api/cimena/gia-ve-lich-chieu")
@CrossOrigin
public class ShowTimePriceController {

      @Autowired
    private ShowtimePriceService service;

    @GetMapping
    public ResponseEntity<List<GiaVeLichChieu>> getAll() {
        return ResponseEntity.ok().body(service.getAllGiaVeLichChieu());
    }

    @GetMapping("{idlichchieu}/{idloaighe}")
    public ResponseEntity<GiaVeLichChieu> getOne(
            @PathVariable("idloaighe") UUID idloaighe,
            @PathVariable("idlichchieu") UUID idlichchieu) {
        return ResponseEntity.ok().body(service.getGiaVeLichChieuById(idlichchieu, idloaighe));
    }

    @GetMapping("pageno")
    public ResponseEntity<List<GiaVeLichChieu>> getPageNo(@RequestParam(value = "sortby", required = false) String sortby,
            @RequestParam(value = "sortdir", required = false) String sortdir,
            @RequestParam(value = "pageNo", required = false) int pageNo) {

        return ResponseEntity.ok().body(service.getPageNo(pageNo, sortby, sortdir));
    }

    @GetMapping("prev-page")
    public ResponseEntity<List<GiaVeLichChieu>> getPrePage(
            @RequestParam(value = "sortby", required = false) String sortby,
            @RequestParam(value = "sortdir", required = false) String sortdir) {
        return ResponseEntity.ok().body(service.getPrevPage(sortby, sortdir));
    }

    @GetMapping("next-page")
    public ResponseEntity<List<GiaVeLichChieu>> getNextPage(
            @RequestParam(value = "sortby", required = false) String sortby,
            @RequestParam(value = "sortdir", required = false) String sortdir) {
        return ResponseEntity.ok().body(service.getNextPage(sortby, sortdir));
    }

    @GetMapping("delete/{idlichchieu}/{idloaighe}")
    public HttpStatus delete(@PathVariable("idloaighe") UUID idloaighe, @PathVariable("idlichchieu") UUID idlichchieu) {
        this.service.setDeteleteState(idlichchieu, idloaighe);
        return HttpStatus.OK;
    }

    @PostMapping
    public ResponseEntity<GiaVeLichChieu> createHoaDon(@RequestBody GiaVeLichChieu hoaDonDoAn) {
        hoaDonDoAn.setCreateAt(LocalDateTime.now());
        return ResponseEntity.ok().body(this.service.createGiaVeLichChieu(hoaDonDoAn));
    }

    @PutMapping("{idlichchieu}/{idloaighe}")
    public ResponseEntity<GiaVeLichChieu> updateHoaDon(@PathVariable("idloaighe") UUID idloaighe,
            @PathVariable("idlichchieu") UUID idlichchieu,
            @RequestBody GiaVeLichChieu hoaDonDoAn) {
        hoaDonDoAn.setId(new GiaVeLichChieuKey(idlichchieu, idloaighe));
        hoaDonDoAn.setUpdateAt(LocalDateTime.now());
        return ResponseEntity.ok().body(this.service.updateGiaVeLichChieu(hoaDonDoAn));
    }

    @DeleteMapping("{idlichchieu}/{idloaighe}")
    public HttpStatus deleteHoaDon(@PathVariable("idloaighe") UUID idloaighe, @PathVariable("idlichchieu") UUID idlichchieu) {
        this.service.deleteGiaVeLichChieu(idlichchieu, idloaighe);
        return HttpStatus.OK;
    }
}