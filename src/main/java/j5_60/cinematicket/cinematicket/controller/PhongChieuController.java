package j5_60.cinematicket.cinematicket.controller;

/**
 * PhongChieuController
 */

import j5_60.cinematicket.cinematicket.entity.PhongChieu;
import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;
import j5_60.cinematicket.cinematicket.repository.PhongChieuRepository;
import j5_60.cinematicket.cinematicket.service.PhongChieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/phongchieu")
public class PhongChieuController {

    @Autowired
    private PhongChieuService phongChieuService;

    @Autowired
    private PhongChieuRepository phongChieuRepository;

    @GetMapping("/index")
    public List<PhongChieu> getAll() {
        return phongChieuService.getAll();
    }

    @GetMapping("/{id}")
    public PhongChieu getById(@PathVariable("id") UUID id) throws ResourceNotFoundException {
        return phongChieuService.findById(id);
    }

    @PostMapping("/add")
    public PhongChieu add(@RequestBody PhongChieu phongChieu) {
        return phongChieuService.save(phongChieu);
    }

    @PutMapping("/update/{id}")
    public PhongChieu update(@PathVariable("id") UUID id, @RequestBody PhongChieu phongChieu) throws ResourceNotFoundException {
        return phongChieuService.updatePhongChieu(id, phongChieu);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) {
        phongChieuService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
