package j5_60.cinematicket.cinematicket.controller;

import j5_60.cinematicket.cinematicket.entity.Ghe;
import j5_60.cinematicket.cinematicket.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("api/cimena/ghe")
public class GheController {
  @Autowired
  SeatService service;

  @GetMapping("phongchieu/{idphongchieu}")
  public ResponseEntity<List<Ghe>> getGheInPhongChieu(@PathVariable("idphongchieu") UUID idphongchieu) {
    return ResponseEntity.ok().body(service.getAllgheByPhongChieu(idphongchieu));
  }

  @GetMapping
  public ResponseEntity<List<Ghe>> getAllGhe() {
    return ResponseEntity.ok().body(service.getAll());
  }
}
