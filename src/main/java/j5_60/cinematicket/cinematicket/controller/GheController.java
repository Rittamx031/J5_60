package j5_60.cinematicket.cinematicket.controller;

import j5_60.cinematicket.cinematicket.entity.Ghe;
import j5_60.cinematicket.cinematicket.entity.LoaiGhe;
import j5_60.cinematicket.cinematicket.entity.PhongChieu;
import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;
import j5_60.cinematicket.cinematicket.service.SeatService;
import j5_60.cinematicket.cinematicket.service.SeatTypeService;
import j5_60.cinematicket.cinematicket.service.CeminaRoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
