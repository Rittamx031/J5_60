package j5_60.cinematicket.cinematicket.controller.admin;

import j5_60.cinematicket.cinematicket.model.entity.Seat;
import j5_60.cinematicket.cinematicket.service.admin.SeatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("api/cimena/ghe")
public class SeatController {
  @Autowired
  SeatService service;

  @GetMapping("phongchieu/{idphongchieu}")
  public ResponseEntity<List<Seat>> getGheInPhongChieu(@PathVariable("idphongchieu") UUID idphongchieu) {
    return ResponseEntity.ok().body(service.getAllgheByPhongChieu(idphongchieu));
  }

  @GetMapping
  public ResponseEntity<List<Seat>> getAllGhe() {
    return ResponseEntity.ok().body(service.getAll());
  }
}
