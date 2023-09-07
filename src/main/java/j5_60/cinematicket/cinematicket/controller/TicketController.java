package j5_60.cinematicket.cinematicket.controller;

import java.time.LocalDate;
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

import j5_60.cinematicket.cinematicket.model.entity.Ticket;
import j5_60.cinematicket.cinematicket.model.modelsearch.VeSearch;
import j5_60.cinematicket.cinematicket.service.TicketService;
import jakarta.servlet.http.HttpServletRequest;

/**
 * VeController
 */

@RestController
@CrossOrigin
@RequestMapping("api/cimena/ve")
public class TicketController {

    @Autowired
    public TicketService service;

    @GetMapping
    public ResponseEntity<List<Ticket>> getAllVe() {
        return ResponseEntity.ok().body(service.getAllVe());
    }

    @GetMapping("panigation")
    public ResponseEntity<int[]> getPanigation() {
        return ResponseEntity.ok().body(service.getPanigation());
    }

    @GetMapping("/hoadon/{idhoadon}")
    public ResponseEntity<List<Ticket>> getVebyHoaDonId(
            @PathVariable("idhoadon") UUID idhoadon) {
        return ResponseEntity.ok().body(service.getVeByHoaDonId(idhoadon));
    }

    @GetMapping("currentpage")
    public int getCrrentPage() {
        return service.getCrrentPage();
    }

    @GetMapping("getDistingValue")
    public List<String> getDistingValueInField(@RequestParam("field") String field) {
        return service.getDistingValueinField(field);
    }

    @GetMapping("{id_ghe}/{id_lich_chieu}")
    public ResponseEntity<Ticket> getOne(
            @PathVariable("id_ghe") UUID id_ghe,
            @PathVariable("id_lich_chieu") UUID id_lich_chieu) {
        return ResponseEntity.ok().body(service.getVeById(id_lich_chieu, id_ghe));
    }

    @GetMapping("page")
    public ResponseEntity<List<Ticket>> getPage(
            @RequestParam(value = "sortby", required = false) String sortby,
            @RequestParam(value = "sortdir", required = false) String sortdir,
            @RequestParam(value = "pageno", required = false) int pageno) {
        System.out.println("sortby = " + sortby + " sort dir = " + sortdir + " page No = " + pageno);
        return ResponseEntity.ok().body(service.getPageNo(pageno, sortby, sortdir));
    }

    @GetMapping("prev-page")
    public ResponseEntity<List<Ticket>> getPrevPage(
            @RequestParam(value = "sortby", required = false) String sortby,
            @RequestParam(value = "sortdir", required = false) String sortdir) {
        return ResponseEntity.ok().body(service.getPrevPage(sortby, sortdir));
    }

    @GetMapping("next-page")
    public ResponseEntity<List<Ticket>> getNextPage(
            @RequestParam(value = "sortby", required = false) String sortby,
            @RequestParam(value = "sortdir", required = false) String sortdir) {
        return ResponseEntity.ok().body(service.getNextPage(sortby, sortdir));
    }

    @GetMapping("delete/{id_ghe}/{id_lich_chieu}")
    public HttpStatus delete(@PathVariable("id_ghe") UUID id_ghe,
            @PathVariable("id_lich_chieu") UUID id_lich_chieu) {
        this.service.setDeteleteState(id_lich_chieu, id_ghe);
        return HttpStatus.OK;
    }

    @PostMapping
    public ResponseEntity<Ticket> createVe(@RequestBody Ticket ve) {
        ve.setNgayDatVe(LocalDate.now());
        ve.setCreateAt(LocalDateTime.now());
        return ResponseEntity.ok().body(this.service.createVe(ve));
    }

    @PostMapping("fillter")
    public ResponseEntity<List<Ticket>> fillterVe(@RequestBody VeSearch veSearch) {
        return ResponseEntity.ok().body(this.service.fillterVe(veSearch));
    }

    @PutMapping("{id_ghe}/{id_lich_chieu}")
    public ResponseEntity<Ticket> updateVe(
            @PathVariable("id_ghe") UUID id_ghe,
            @PathVariable("id_lich_chieu") UUID id_lich_chieu,
            @RequestBody Ticket ve) {
        ve.setUpdateAt(LocalDateTime.now());
        return ResponseEntity.ok().body(this.service.updateVe(ve));
    }

    @DeleteMapping("{id_ghe}/{id_lich_chieu}")
    public HttpStatus deleteVe(@PathVariable("id_ghe") UUID id_ghe,
            @PathVariable("id_lich_chieu") UUID id_lich_chieu) {
        this.service.deleteVe(id_lich_chieu, id_ghe);
        return HttpStatus.OK;
    }

    @GetMapping("fillter")
    public String fillter(
            HttpServletRequest request) {

        return request.getRequestURL().toString();
    }
}