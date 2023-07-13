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

import j5_60.cinematicket.cinematicket.entity.DoAn;
import j5_60.cinematicket.cinematicket.service.DoAnService;

/**
 * DoAnController
 */
@RestController
@CrossOrigin
@RequestMapping("cimena/do-an")
public class DoAnController {
  @Autowired 
    private DoAnService service;

    @GetMapping
    public ResponseEntity<List<DoAn>> getAllDoAn() {
        return ResponseEntity.ok().body(service.getAllDoAn());
    }

    @GetMapping("{id}")
    public ResponseEntity<DoAn> getDoAnById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(service.getDoAnById(id));
    }
    // @GetMapping("/search")
    // public ResponseEntity<List<DoAn>>
    // getDoAnById(@RequestParam(value ="name", required = true)
    // String name) {
    // return ResponseEntity.ok().body(service.searchByName(name));
    // }

    @GetMapping("pre")
    public ResponseEntity<List<DoAn>> getPrevPage(
            @RequestParam(value = "sortby", required = false) String sortby,
            @RequestParam(value = "sortdir", required = false) String sortdir) {
        return ResponseEntity.ok().body(service.getPrevPage(sortby, sortdir));
    }

    @GetMapping("pageno")
    public ResponseEntity<List<DoAn>> getPageNo(
            @RequestParam(value = "sortby", required = false) String sortby,
            @RequestParam(value = "sortdir", required = false) String sortdir,
            @RequestParam(value = "pageNo", required = false) int pageNo) {
        System.out.println(pageNo + " , " + sortby + " ," + sortdir);
        return ResponseEntity.ok().body(service.getPageNo(pageNo, sortby, sortdir));
    }

    @GetMapping("next")
    public ResponseEntity<List<DoAn>> getNextPage(
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
    public ResponseEntity<DoAn> createDoAn(@RequestBody DoAn hoaDon) {
        hoaDon.setCreateAt(LocalDateTime.now());
        return ResponseEntity.ok().body(this.service.createDoAn(hoaDon));
    }

    @PutMapping("{id}")
    public ResponseEntity<DoAn> updateDoAn(@PathVariable UUID id,
            @RequestBody DoAn hoaDon) {
        hoaDon.setId(id);
        hoaDon.setUpdateAt(LocalDateTime.now());
        return ResponseEntity.ok().body(this.service.updateDoAn(hoaDon));
    }

    @DeleteMapping("{id}")
    public HttpStatus deleteDoAn(@PathVariable UUID id) {
        this.service.deleteDoAn(id);
        return HttpStatus.OK;
    }
    
    
}