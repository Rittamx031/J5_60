package j5_60.cinematicket.cinematicket.controller;

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
import org.springframework.web.bind.annotation.RestController;

import j5_60.cinematicket.cinematicket.entity.ComboDoAnDetail;
import j5_60.cinematicket.cinematicket.entity.key.ComBoDoAnDetailKey;
import j5_60.cinematicket.cinematicket.service.ComboDoAnDetailService;

/**
 * ComboDoAnDetailController
 */
@RestController
@CrossOrigin
@RequestMapping("api/cimena/com-bo-do-an-detail")
public class ComboDoAnDetailController {
    @Autowired
    private ComboDoAnDetailService service;

    @PostMapping
    public ResponseEntity<ComboDoAnDetail> createComboDoAnDetail(@RequestBody ComboDoAnDetail comboDoAnDetail) {
        ComboDoAnDetail createdComboDoAnDetail = service.createComboDoAnDetail(comboDoAnDetail);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComboDoAnDetail);
    }

    @PutMapping("/{idcombo}/{iddoan}")
    public ResponseEntity<ComboDoAnDetail> updateComboDoAnDetail(@PathVariable("idcombo") UUID idcombo,
            @PathVariable("iddoan") UUID iddoan, @RequestBody ComboDoAnDetail comboDoAnDetail) {
        comboDoAnDetail.setId(new ComBoDoAnDetailKey(idcombo, iddoan));
        ComboDoAnDetail updatedComboDoAnDetail = service.updateComboDoAnDetail(comboDoAnDetail);
        return ResponseEntity.ok(updatedComboDoAnDetail);
    }

    @GetMapping
    public ResponseEntity<List<ComboDoAnDetail>> getAllComboDoAnDetail() {
        List<ComboDoAnDetail> comboDoAnDetails = service.getAllComboDoAnDetail();
        return ResponseEntity.ok(comboDoAnDetails);
    }

    @GetMapping("/{idcombo}/{iddoan}")
    public ResponseEntity<ComboDoAnDetail> getComboDoAnDetailById(@PathVariable("idcombo") UUID idcombo,
            @PathVariable("iddoan") UUID iddoan) {
        ComboDoAnDetail comboDoAnDetail = service.getComboDoAnDetailById(idcombo, iddoan);
        return ResponseEntity.ok(comboDoAnDetail);
    }

    @GetMapping("/{idcombo}")
    public ResponseEntity<List<ComboDoAnDetail>> getListDoAnbyCombo(@PathVariable("idcombo") UUID idcombo) {
        List<ComboDoAnDetail> listdt = service.getComboDoAnDetailByIdComboo(idcombo);
        return ResponseEntity.ok(listdt);
    }

    @DeleteMapping("/{idcombo}/{iddoan}")
    public ResponseEntity<ComboDoAnDetail> deleteComboDoAnDetail(@PathVariable("idcombo") UUID idcombo,
            @PathVariable("iddoan") UUID iddoan) {
        ComboDoAnDetail deletedComboDoAnDetail = service.deleteComboDoAnDetail(idcombo, iddoan);
        return ResponseEntity.ok(deletedComboDoAnDetail);
    }

    @PutMapping("/{idcombo}/{iddoan}/delete")
    public ResponseEntity<ComboDoAnDetail> setDeleteState(@PathVariable("idcombo") UUID idcombo,
            @PathVariable("iddoan") UUID iddoan) {
        ComboDoAnDetail comboDoAnDetail = service.setDeteleteState(idcombo, iddoan);
        return ResponseEntity.ok(comboDoAnDetail);
    }
}