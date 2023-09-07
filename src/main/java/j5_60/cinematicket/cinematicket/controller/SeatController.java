package j5_60.cinematicket.cinematicket.controller;

import j5_60.cinematicket.cinematicket.model.dto.request.NewRow;
import j5_60.cinematicket.cinematicket.model.dto.request.SeatRequest;
import j5_60.cinematicket.cinematicket.model.dto.seat.RowSeat;
import j5_60.cinematicket.cinematicket.model.entity.Seat;
import j5_60.cinematicket.cinematicket.service.SeatService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
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
// manager
  @PostMapping("/manager/add-seat-in-row")
  public ResponseEntity<RowSeat> addSeatInRow(@RequestBody @Valid SeatRequest seat) {
    return ResponseEntity.ok().body(service.addSeatInRow(seat));
  }

  @PostMapping("/manager/add-new-row")
  public ResponseEntity<RowSeat> addNewRow(@RequestBody @Valid NewRow seat) {
    return ResponseEntity.ok().body(service.addNewRowSeat(seat));
  }

  @GetMapping("manager/get-row-seat/{idphongchieu}/{row}")
  public ResponseEntity<RowSeat> getRowSate(@PathVariable("idphongchieu") UUID idPhongChieu,
      @PathVariable("row") int row) {
    return ResponseEntity.ok().body(service.getRow(row, idPhongChieu));
  }

  @PutMapping("manager/updateghe/{id}")
  public ResponseEntity<Seat> updateGhe(@PathVariable("id") UUID idGhe, @RequestBody SeatRequest seatud) {
    seatud.setId(idGhe);
    return ResponseEntity.ok().body(service.updateSeat(seatud));
  }

  @DeleteMapping("manager/delete/{id}")
  public HttpStatusCode deleteGhe(@PathVariable("id") UUID idGhe) {
    return null;
  }
}
