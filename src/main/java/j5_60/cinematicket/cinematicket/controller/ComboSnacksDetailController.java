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

import j5_60.cinematicket.cinematicket.model.entity.ComboSnacksDetail;
import j5_60.cinematicket.cinematicket.model.entity.key.ComBoDoAnDetailKey;
import j5_60.cinematicket.cinematicket.service.SnackDetailService;

/**
 * ComboDoAnDetailController
 */
@RestController
@CrossOrigin
@RequestMapping("api/cimena/com-bo-do-an-detail")
public class ComboSnacksDetailController {
    @Autowired
    private SnackDetailService service;

    @PostMapping
    public ResponseEntity<ComboSnacksDetail> createComboDoAnDetail(@RequestBody ComboSnacksDetail comboDoAnDetail) {
        ComboSnacksDetail createdComboDoAnDetail = service.createComboDoAnDetail(comboDoAnDetail);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComboDoAnDetail);
    }

    @PutMapping("/{idcombo}/{iddoan}")
    public ResponseEntity<ComboSnacksDetail> updateComboDoAnDetail(@PathVariable("idcombo") UUID idcombo,
            @PathVariable("iddoan") UUID iddoan, @RequestBody ComboSnacksDetail comboDoAnDetail) {
        comboDoAnDetail.setId(new ComBoDoAnDetailKey(idcombo, iddoan));
        ComboSnacksDetail updatedComboDoAnDetail = service.updateComboDoAnDetail(comboDoAnDetail);
        return ResponseEntity.ok(updatedComboDoAnDetail);
    }

    @GetMapping
    public ResponseEntity<List<ComboSnacksDetail>> getAllComboDoAnDetail() {
        List<ComboSnacksDetail> comboDoAnDetails = service.getAllComboDoAnDetail();
        return ResponseEntity.ok(comboDoAnDetails);
    }

    @GetMapping("/{idcombo}/{iddoan}")
    public ResponseEntity<ComboSnacksDetail> getComboDoAnDetailById(@PathVariable("idcombo") UUID idcombo,
            @PathVariable("iddoan") UUID iddoan) {
        ComboSnacksDetail comboDoAnDetail = service.getComboDoAnDetailById(idcombo, iddoan);
        return ResponseEntity.ok(comboDoAnDetail);
    }

    @GetMapping("/{idcombo}")
    public ResponseEntity<List<ComboSnacksDetail>> getListDoAnbyCombo(@PathVariable("idcombo") UUID idcombo) {
        List<ComboSnacksDetail> listdt = service.getComboDoAnDetailByIdComboo(idcombo);
        return ResponseEntity.ok(listdt);
    }

    @DeleteMapping("/{idcombo}/{iddoan}")
    public ResponseEntity<ComboSnacksDetail> deleteComboDoAnDetail(@PathVariable("idcombo") UUID idcombo,
            @PathVariable("iddoan") UUID iddoan) {
        ComboSnacksDetail deletedComboDoAnDetail = service.deleteComboDoAnDetail(idcombo, iddoan);
        return ResponseEntity.ok(deletedComboDoAnDetail);
    }

    @PutMapping("/{idcombo}/{iddoan}/delete")
    public ResponseEntity<ComboSnacksDetail> setDeleteState(@PathVariable("idcombo") UUID idcombo,
            @PathVariable("iddoan") UUID iddoan) {
        ComboSnacksDetail comboDoAnDetail = service.setDeteleteState(idcombo, iddoan);
        return ResponseEntity.ok(comboDoAnDetail);
    }
}