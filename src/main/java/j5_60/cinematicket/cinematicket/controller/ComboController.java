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

import j5_60.cinematicket.cinematicket.entity.Combo;
import j5_60.cinematicket.cinematicket.service.ComboService;

/**
 * ComboController
 */
@RestController
@CrossOrigin
@RequestMapping("cimena/com-bo")
public class ComboController {
    @Autowired
    private ComboService service;

    @GetMapping
    public ResponseEntity<List<Combo>> getAllCombo() {
        return ResponseEntity.ok().body(service.getAllCombo());
    }

    @GetMapping("{id}")
    public ResponseEntity<Combo> getComboById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(service.getComboById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Combo>> getDoAnById(@RequestParam(value = "name", required = true) String name) {
        return ResponseEntity.ok().body(service.searchDoAn(name));
    }

    @GetMapping("pre")
    public ResponseEntity<List<Combo>> getPrevPage(
            @RequestParam(value = "sortby", required = false) String sortby,
            @RequestParam(value = "sortdir", required = false) String sortdir) {
        return ResponseEntity.ok().body(service.getPrevPage(sortby, sortdir));
    }

    @GetMapping("/page")
    public ResponseEntity<List<Combo>> getPageNo(
            @RequestParam(value = "sortby", required = false) String sortby,
            @RequestParam(value = "sortdir", required = false) String sortdir,
            @RequestParam(value = "pageno", required = false) int pageNo) {
        return ResponseEntity.ok().body(service.getPageNo(pageNo, sortby, sortdir));
    }

    @GetMapping("panigation")
    public ResponseEntity<int[]> getPanigation() {
        return ResponseEntity.ok().body(service.getPanigation());
    }

    @GetMapping("next")
    public ResponseEntity<List<Combo>> getNextPage(
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
    public ResponseEntity<Combo> createCombo(@RequestBody Combo comBo) {
        comBo.setCreateAt(LocalDateTime.now());
        return ResponseEntity.ok().body(this.service.createCombo(comBo));
    }

    @PutMapping("{id}")
    public ResponseEntity<Combo> updateCombo(@PathVariable UUID id,
            @RequestBody Combo comBo) {
        comBo.setId(id);
        comBo.setUpdateAt(LocalDateTime.now());
        return ResponseEntity.ok().body(this.service.updateCombo(comBo));
    }

    @DeleteMapping("{id}")
    public HttpStatus deleteCombo(@PathVariable UUID id) {
        this.service.deleteCombo(id);
        return HttpStatus.OK;
    }

}