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

import j5_60.cinematicket.cinematicket.model.entity.MovieGener;
import j5_60.cinematicket.cinematicket.model.entity.key.TheLoaiPhimKey;
import j5_60.cinematicket.cinematicket.service.admin.MovieGenreService;

/**
 * TheLoaiPhimController
 */

@RestController
@RequestMapping("api/cimena/the-loai-phim")
@CrossOrigin
public class MovieGenreController {
    @Autowired
    private MovieGenreService service;

    @GetMapping
    public ResponseEntity<List<MovieGener>> getAll() {
        return ResponseEntity.ok().body(service.getAllTheLoaiPhim());
    }

    @GetMapping("{idphim}/{idtheloai}")
    public ResponseEntity<MovieGener> getOne(
            @PathVariable("idtheloai") UUID idtheloai,
            @PathVariable("idphim") UUID idphim) {
        return ResponseEntity.ok().body(service.getTheLoaiPhimById(idphim, idtheloai));
    }

    @GetMapping("pageno")
    public ResponseEntity<List<MovieGener>> getPageNo(@RequestParam(value = "sortby", required = false) String sortby,
            @RequestParam(value = "sortdir", required = false) String sortdir,
            @RequestParam(value = "pageNo", required = false) int pageNo) {

        return ResponseEntity.ok().body(service.getPageNo(pageNo, sortby, sortdir));
    }

    @GetMapping("prev-page")
    public ResponseEntity<List<MovieGener>> getPrePage(
            @RequestParam(value = "sortby", required = false) String sortby,
            @RequestParam(value = "sortdir", required = false) String sortdir) {
        return ResponseEntity.ok().body(service.getPrevPage(sortby, sortdir));
    }

    @GetMapping("next-page")
    public ResponseEntity<List<MovieGener>> getNextPage(
            @RequestParam(value = "sortby", required = false) String sortby,
            @RequestParam(value = "sortdir", required = false) String sortdir) {
        return ResponseEntity.ok().body(service.getNextPage(sortby, sortdir));
    }

    @GetMapping("delete/{idphim}/{idtheloai}")
    public HttpStatus delete(@PathVariable("idtheloai") UUID idtheloai, @PathVariable("idphim") UUID idphim) {
        this.service.setDeteleteState(idphim, idtheloai);
        return HttpStatus.OK;
    }

    @PostMapping
    public ResponseEntity<MovieGener> createHoaDon(@RequestBody MovieGener hoaDonDoAn) {
        hoaDonDoAn.setCreateAt(LocalDateTime.now());
        return ResponseEntity.ok().body(this.service.createTheLoaiPhim(hoaDonDoAn));
    }

    @PutMapping("{idphim}/{idtheloai}")
    public ResponseEntity<MovieGener> updateHoaDon(@PathVariable("idtheloai") UUID idtheloai,
            @PathVariable("idphim") UUID idphim,
            @RequestBody MovieGener hoaDonDoAn) {
        hoaDonDoAn.setId(new TheLoaiPhimKey(idphim, idtheloai));
        hoaDonDoAn.setUpdateAt(LocalDateTime.now());
        return ResponseEntity.ok().body(this.service.updateTheLoaiPhim(hoaDonDoAn));
    }

    @DeleteMapping("{idphim}/{idtheloai}")
    public HttpStatus deleteHoaDon(@PathVariable("idtheloai") UUID idtheloai, @PathVariable("idphim") UUID idphim) {
        this.service.deleteTheLoaiPhim(idphim, idtheloai);
        return HttpStatus.OK;
    }
}