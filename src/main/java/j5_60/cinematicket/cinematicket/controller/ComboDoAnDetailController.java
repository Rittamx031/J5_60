package j5_60.cinematicket.cinematicket.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import j5_60.cinematicket.cinematicket.entity.ComboDoAnDetail;
import j5_60.cinematicket.cinematicket.entity.key.ComBoDoAnDetailKey;
import j5_60.cinematicket.cinematicket.service.ComboDoAnDetailService;

/**
 * ComboDoAnDetailController
 */
public class ComboDoAnDetailController {
    @Autowired
    private ComboDoAnDetailService  service;

    @PostMapping
    public ResponseEntity<ComboDoAnDetail> createComboDoAnDetail(@RequestBody ComboDoAnDetail comboDoAnDetail) {
        ComboDoAnDetail createdComboDoAnDetail = service.createComboDoAnDetail(comboDoAnDetail);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComboDoAnDetail);
    }

    @PutMapping("/{idcombo}/{idDoAn}")
    public ResponseEntity<ComboDoAnDetail> updateComboDoAnDetail(@PathVariable("idcombo") UUID idcombo,@PathVariable("idDoAn") UUID idDoAn, @RequestBody ComboDoAnDetail comboDoAnDetail) {
        comboDoAnDetail.setId(new ComBoDoAnDetailKey(idcombo, idDoAn));
        ComboDoAnDetail updatedComboDoAnDetail = service.updateComboDoAnDetail(comboDoAnDetail);
        return ResponseEntity.ok(updatedComboDoAnDetail);
    }

    @GetMapping
    public ResponseEntity<List<ComboDoAnDetail>> getAllComboDoAnDetail() {
        List<ComboDoAnDetail> comboDoAnDetails = service.getAllComboDoAnDetail();
        return ResponseEntity.ok(comboDoAnDetails);
    }

    @GetMapping("/{idcombo}/{idDoAn}")
    public ResponseEntity<ComboDoAnDetail> getComboDoAnDetailById(@PathVariable("idcombo") UUID idcombo, @PathVariable("idDoAn") UUID idDoAn) {
        ComboDoAnDetail comboDoAnDetail = service.getComboDoAnDetailById(idcombo, idDoAn);
        return ResponseEntity.ok(comboDoAnDetail);
    }

    @DeleteMapping("/{idcombo}/{idDoAn}")
    public ResponseEntity<ComboDoAnDetail> deleteComboDoAnDetail(@PathVariable("idcombo") UUID idcombo, @PathVariable("idDoAn") UUID idDoAn) {
        ComboDoAnDetail deletedComboDoAnDetail = service.deleteComboDoAnDetail(idcombo, idDoAn);
        return ResponseEntity.ok(deletedComboDoAnDetail);
    }

    @PutMapping("/{idcombo}/{idDoAn}/delete")
    public ResponseEntity<ComboDoAnDetail> setDeleteState(@PathVariable("idcombo") UUID idcombo, @PathVariable("idDoAn") UUID idDoAn) {
        ComboDoAnDetail comboDoAnDetail = service.setDeteleteState(idcombo, idDoAn);
        return ResponseEntity.ok(comboDoAnDetail);
    }
}