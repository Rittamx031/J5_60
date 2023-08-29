package j5_60.cinematicket.cinematicket.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;
import j5_60.cinematicket.cinematicket.model.entity.Ve;
import j5_60.cinematicket.cinematicket.model.entity.key.VeKey;
import j5_60.cinematicket.cinematicket.model.modelsearch.VeSearch;
import j5_60.cinematicket.cinematicket.repository.TicketRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class TicketService {
    @Autowired
    private TicketRepository repo;

    private final int ROWCOUNT = 5;
    private int PageNo = -1;

    public Ve createVe(Ve ve) {
        return repo.save(ve);
    }

    public List<Ve> fillterVe(VeSearch veSearch) {
        List<Ve> list = repo.findAll();
        List<Ve> result = list.stream()
                .filter(ve -> ((ve.getHoaDon().getId().equals(veSearch.getIdHoaDon())
                        || (veSearch.getIdHoaDon() == null))
                        && (ve.getLichChieu().getId().equals(veSearch.getIdLichChieu())
                                || (veSearch.getIdLichChieu() == null))
                        && (ve.getGhe().getId().equals(veSearch.getIdGhe())
                                || (veSearch.getIdGhe() == null))
                        && (ve.getNgayDatVe().compareTo(veSearch.getNgayDatVeMax()) <= 0)
                        && (ve.getNgayDatVe().compareTo(veSearch.getNgayDatVeMin()) >= 0)
                        && (ve.getGia() <= veSearch.getGiaMax())
                        && (ve.getGia() >= veSearch.getGiaMin())))
                .collect(Collectors.toList());
        return result;
    }

    public List<String> getDistingValueinField(String field) {
        List<Ve> list = this.repo.findAll();

        // Filter and collect distinct values from the specified field
        List<String> result = list.stream()
                .map(ve -> getFieldAsString(ve, field))
                .distinct()
                .collect(Collectors.toList());
        return result;
    }

    // Helper method to retrieve the field value as a string using reflection
    private String getFieldAsString(Ve ve, String field) {
        try {
            java.lang.reflect.Field declaredField = Ve.class.getDeclaredField(field);
            declaredField.setAccessible(true);
            Object value = declaredField.get(ve);
            return value != null ? value.toString() : null;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            // Handle exceptions if needed
            e.printStackTrace();
            return null;
        }
    }

    public Ve updateVe(Ve ve) {
        Optional<Ve> veDb = this.repo.findById(ve.getId());
        if (veDb.isPresent()) {
            Ve veud = veDb.get();
            veud.setId(ve.getId());
            veud.setGhe(ve.getGhe());
            veud.setHoaDon(ve.getHoaDon());
            veud.setLichChieu(ve.getLichChieu());
            veud.setTrangThai(ve.getTrangThai());
            veud.setGia(ve.getGia());
            veud.setNgayDatVe(ve.getNgayDatVe());
            veud.setCreateAt(ve.getCreateAt());
            veud.setCreateBy(ve.getCreateBy());
            veud.setDeleted(ve.isDeleted());
            veud.setUpdateAt(ve.getUpdateAt());
            veud.setUpdateBy(ve.getUpdateBy());
            return veud;
        } else {
            throw new ResourceNotFoundException("Can not find person with id" + ve.getId());
        }
    }

    public List<Ve> getAllVe() {
        return repo.findAll();
    }

    public List<Ve> getVeByHoaDonId(UUID hoaDonId) {
        List<Ve> listhdda = repo.findAll();
        List<Ve> result = listhdda.stream().filter(hdda -> hdda.getHoaDon() != null)
                .toList();
        return result.stream().filter(hdda -> hdda.getHoaDon().getId().equals(hoaDonId))
                .toList();
    }

    public Ve getVeById(UUID id_lich_chieu, UUID id_ghe) {
        Optional<Ve> ve = repo.findById(new VeKey(id_lich_chieu, id_ghe));
        if (ve.isPresent()) {
            return ve.get();
        } else {
            throw new ResourceNotFoundException("Cannot Find Phuong thuc thanh toan with id = ");
        }
    }

    public Ve deleteVe(UUID id_lich_chieu, UUID id_ghe) {
        Optional<Ve> ve = repo.findById(new VeKey(id_lich_chieu, id_ghe));
        if (ve.isPresent()) {
            return ve.get();
        } else {
            throw new ResourceNotFoundException("Cannot Find Phuong thuc thanh toan with id = ");
        }
    }

    public Ve setDeteleteState(UUID id_lich_chieu, UUID id_ghe) {
        Optional<Ve> veDb = this.repo.findById(new VeKey(id_lich_chieu, id_ghe));
        if (veDb.isPresent()) {
            Ve veud = veDb.get();
            veud.setDeleted(true);
            return veud;
        } else {
            throw new ResourceNotFoundException("Can not find person with id");
        }
    }

    public List<Ve> getPageNo(int pageNo, String sortBy, String sortDir) {
        this.PageNo = pageNo;
        List<Ve> ves;
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        // Pageable object
        Pageable pageable = PageRequest.of(pageNo - 1, ROWCOUNT, sort);
        // findAll method and pass pageable instance
        Page<Ve> page = repo.findAll(pageable);
        ves = page.getContent();
        return ves;
    }

    public List<Ve> getNextPage(String sortBy, String sortDir) {
        if (this.PageNo >= getPanigation().length - 1) {
            return this.getPageNo(this.getPanigation().length, sortBy, sortBy);
        } else {
            return this.getPageNo(this.PageNo + 1, sortBy, sortBy);
        }
    }

    public List<Ve> getPrevPage(String sortBy, String sortDir) {
        if (this.PageNo <= 0) {
            return this.getPageNo(1, sortBy, sortBy);
        } else {
            return this.getPageNo(this.PageNo - 1, sortBy, sortBy);
        }
    }

    public int[] getPanigation() {
        Pageable pageable = PageRequest.of(1, ROWCOUNT);
        Page<Ve> page = repo.findAll(pageable);
        int totalPage = page.getTotalPages();
        int[] array = IntStream.rangeClosed(0, totalPage - 1).toArray();
        return array;
    }

    public int getCrrentPage() {
        return this.PageNo;
    }
}
