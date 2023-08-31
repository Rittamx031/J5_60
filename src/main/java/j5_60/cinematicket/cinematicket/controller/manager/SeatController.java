package j5_60.cinematicket.cinematicket.controller.manager;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import j5_60.cinematicket.cinematicket.model.dto.ghe.RowSeat;
import j5_60.cinematicket.cinematicket.model.dto.ghe.request.NewRow;
import j5_60.cinematicket.cinematicket.model.dto.ghe.request.Seat;
import j5_60.cinematicket.cinematicket.model.entity.Seat;
import j5_60.cinematicket.cinematicket.service.manager.RowSeatService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/cimena/seat")
@CrossOrigin
public class SeatController {
  @Autowired
  RowSeatService service;

  @PostMapping("add-seat-in-row")
  public ResponseEntity<RowSeat> addSeatInRow(@RequestBody @Valid Seat seat) {
    return ResponseEntity.ok().body(service.addSeatInRow(seat));
  }

  @PostMapping("/add-new-row")
  public ResponseEntity<RowSeat> addNewRow(@RequestBody @Valid NewRow seat) {
    return ResponseEntity.ok().body(service.addNewRowSeat(seat));
  }

  @GetMapping("get-row-seat/{idphongchieu}/{row}")
  public ResponseEntity<RowSeat> getRowSate(@PathVariable("idphongchieu") UUID idPhongChieu,
      @PathVariable("row") int row) {
    return ResponseEntity.ok().body(service.getRow(row, idPhongChieu));
  }

  @PutMapping("updateghe/{id}")
  public ResponseEntity<Seat> updateGhe(@PathVariable("id") UUID idGhe, @RequestBody Seat seatud) {
    seatud.setId(idGhe);
    return ResponseEntity.ok().body(service.updateSeat(seatud));
  }

  @DeleteMapping("delete/{id}")
  public HttpStatusCode deleteGhe(@PathVariable("id") UUID idGhe) {
    return null;
  }
}
