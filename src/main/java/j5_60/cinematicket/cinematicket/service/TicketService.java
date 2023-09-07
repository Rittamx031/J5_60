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
import j5_60.cinematicket.cinematicket.model.entity.Ticket;
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

    public Ticket createVe(Ticket ve) {
        return repo.save(ve);
    }

    public List<Ticket> fillterVe(VeSearch veSearch) {
        List<Ticket> list = repo.findAll();
        List<Ticket> result = list.stream()
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
        List<Ticket> list = this.repo.findAll();

        // Filter and collect distinct values from the specified field
        List<String> result = list.stream()
                .map(ve -> getFieldAsString(ve, field))
                .distinct()
                .collect(Collectors.toList());
        return result;
    }

    // Helper method to retrieve the field value as a string using reflection
    private String getFieldAsString(Ticket ve, String field) {
        try {
            java.lang.reflect.Field declaredField = Ticket.class.getDeclaredField(field);
            declaredField.setAccessible(true);
            Object value = declaredField.get(ve);
            return value != null ? value.toString() : null;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            // Handle exceptions if needed
            e.printStackTrace();
            return null;
        }
    }

    public Ticket updateVe(Ticket ve) {
        Optional<Ticket> veDb = this.repo.findById(ve.getId());
        if (veDb.isPresent()) {
            Ticket veud = veDb.get();
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

    public List<Ticket> getAllVe() {
        return repo.findAll();
    }

    public List<Ticket> getVeByHoaDonId(UUID hoaDonId) {
        List<Ticket> listhdda = repo.findAll();
        List<Ticket> result = listhdda.stream().filter(hdda -> hdda.getHoaDon() != null)
                .toList();
        return result.stream().filter(hdda -> hdda.getHoaDon().getId().equals(hoaDonId))
                .toList();
    }

    public Ticket getVeById(UUID id_lich_chieu, UUID id_ghe) {
        Optional<Ticket> ve = repo.findById(new VeKey(id_lich_chieu, id_ghe));
        if (ve.isPresent()) {
            return ve.get();
        } else {
            throw new ResourceNotFoundException("Cannot Find Phuong thuc thanh toan with id = ");
        }
    }

    public Ticket deleteVe(UUID id_lich_chieu, UUID id_ghe) {
        Optional<Ticket> ve = repo.findById(new VeKey(id_lich_chieu, id_ghe));
        if (ve.isPresent()) {
            return ve.get();
        } else {
            throw new ResourceNotFoundException("Cannot Find Phuong thuc thanh toan with id = ");
        }
    }

    public Ticket setDeteleteState(UUID id_lich_chieu, UUID id_ghe) {
        Optional<Ticket> veDb = this.repo.findById(new VeKey(id_lich_chieu, id_ghe));
        if (veDb.isPresent()) {
            Ticket veud = veDb.get();
            veud.setDeleted(true);
            return veud;
        } else {
            throw new ResourceNotFoundException("Can not find person with id");
        }
    }

    public List<Ticket> getPageNo(int pageNo, String sortBy, String sortDir) {
        this.PageNo = pageNo;
        List<Ticket> ves;
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        // Pageable object
        Pageable pageable = PageRequest.of(pageNo - 1, ROWCOUNT, sort);
        // findAll method and pass pageable instance
        Page<Ticket> page = repo.findAll(pageable);
        ves = page.getContent();
        return ves;
    }

    public List<Ticket> getNextPage(String sortBy, String sortDir) {
        if (this.PageNo >= getPanigation().length - 1) {
            return this.getPageNo(this.getPanigation().length, sortBy, sortBy);
        } else {
            return this.getPageNo(this.PageNo + 1, sortBy, sortBy);
        }
    }

    public List<Ticket> getPrevPage(String sortBy, String sortDir) {
        if (this.PageNo <= 0) {
            return this.getPageNo(1, sortBy, sortBy);
        } else {
            return this.getPageNo(this.PageNo - 1, sortBy, sortBy);
        }
    }

    public int[] getPanigation() {
        Pageable pageable = PageRequest.of(1, ROWCOUNT);
        Page<Ticket> page = repo.findAll(pageable);
        int totalPage = page.getTotalPages();
        int[] array = IntStream.rangeClosed(0, totalPage - 1).toArray();
        return array;
    }

    public int getCrrentPage() {
        return this.PageNo;
    }
}
