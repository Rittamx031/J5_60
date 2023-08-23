package j5_60.cinematicket.cinematicket.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import j5_60.cinematicket.cinematicket.entity.HoaDonDoAn;
import j5_60.cinematicket.cinematicket.entity.key.HoaDonDoAnKey;
import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;
import j5_60.cinematicket.cinematicket.repository.BillSnacksRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class BillSnackService {
    @Autowired
    private BillSnacksRepository repo;
    private final int ROWCOUNT = 5;
    private int PageNo = -1;

    public HoaDonDoAn createHoaDonDoAn(HoaDonDoAn hoaDonDoAnDetail) {
        return repo.save(hoaDonDoAnDetail);
    }

    public HoaDonDoAn updateHoaDonDoAn(HoaDonDoAn hoaDonDoAnDetail) {
        Optional<HoaDonDoAn> hoaDonDoAnDetailDb = this.repo.findById(hoaDonDoAnDetail.getId());
        if (hoaDonDoAnDetailDb.isPresent()) {
            HoaDonDoAn hoaDonDoAnDetailud = hoaDonDoAnDetailDb.get();
            hoaDonDoAnDetailud.setId(hoaDonDoAnDetail.getId());
            hoaDonDoAnDetailud.setSoLuong(hoaDonDoAnDetail.getSoLuong());
            hoaDonDoAnDetailud.setGia(hoaDonDoAnDetail.getGia());
            hoaDonDoAnDetailud.setCreateAt(hoaDonDoAnDetail.getCreateAt());
            hoaDonDoAnDetailud.setCreateBy(hoaDonDoAnDetail.getCreateBy());
            hoaDonDoAnDetailud.setDeleted(hoaDonDoAnDetail.isDeleted());
            hoaDonDoAnDetailud.setUpdateAt(hoaDonDoAnDetail.getUpdateAt());
            hoaDonDoAnDetailud.setUpdateBy(hoaDonDoAnDetail.getUpdateBy());
            return hoaDonDoAnDetailud;
        } else {
            throw new ResourceNotFoundException("Can not find Hoa Don Do An  with id" + hoaDonDoAnDetail.getId());
        }
    }

    public List<HoaDonDoAn> getAllHoaDonDoAn() {
        return repo.findAll();
    }

    public List<HoaDonDoAn> getHoaDonDoAnByHoaDonId(UUID idHoaDon) {
        List<HoaDonDoAn> listhdda = repo.findAll();
        List<HoaDonDoAn> result = listhdda.stream().filter(hdda -> hdda.getId().getId_hoa_don().equals(idHoaDon))
                .toList();
        return result;
    }

    // public List<HoaDonDoAn> searchByName(){
    // return repo.findAll();
    // }
    public HoaDonDoAn getHoaDonDoAnById(UUID idcombo, UUID idHoaDon) {
        Optional<HoaDonDoAn> phuongthucThanhToan = repo.findById(new HoaDonDoAnKey(idcombo, idHoaDon));
        if (phuongthucThanhToan.isPresent()) {
            return phuongthucThanhToan.get();
        } else {
            throw new ResourceNotFoundException("Cannot Find Hoa Don Do An  with id = ");
        }
    }

    public HoaDonDoAn deleteHoaDonDoAn(UUID idcombo, UUID idHoaDon) {
        Optional<HoaDonDoAn> phuongthucThanhToan = repo.findById(new HoaDonDoAnKey(idcombo, idHoaDon));
        if (phuongthucThanhToan.isPresent()) {
            return phuongthucThanhToan.get();
        } else {
            throw new ResourceNotFoundException("Cannot Find Hoa Don Do An  with id = ");
        }
    }

    public HoaDonDoAn setDeteleteState(UUID idcombo, UUID idHoaDon) {
        Optional<HoaDonDoAn> hoaDonDoAnDetailDb = this.repo.findById(new HoaDonDoAnKey(idcombo, idHoaDon));
        if (hoaDonDoAnDetailDb.isPresent()) {
            HoaDonDoAn hoaDonDoAnDetailud = hoaDonDoAnDetailDb.get();
            hoaDonDoAnDetailud.setDeleted(true);
            return hoaDonDoAnDetailud;
        } else {
            throw new ResourceNotFoundException(
                    "Can not find Hoa Don Do An  with idcombo=" + idcombo + " and idHoaDon=" + idHoaDon);
        }
    }

    public List<HoaDonDoAn> getPageNo(int pageNo, String sortBy, String sortDir) {
        this.PageNo = pageNo;
        List<HoaDonDoAn> hoaDonDoAnDetails;
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        // Pageable object
        Pageable pageable = PageRequest.of(pageNo-1, ROWCOUNT, sort);
        // findAll method and pass pageable instance
        Page<HoaDonDoAn> page = repo.findAll(pageable);
        hoaDonDoAnDetails = page.getContent();
        return hoaDonDoAnDetails;
    }

    public List<HoaDonDoAn> getNextPage(String sortBy, String sortDir) {
        if (this.PageNo >= getPanigation().length - 1) {
            return this.getPageNo(this.getPanigation().length - 1, sortBy, sortBy);
        } else {
            return this.getPageNo(this.PageNo + 1, sortBy, sortBy);
        }
    }

    public List<HoaDonDoAn> getPrevPage(String sortBy, String sortDir) {
        if (this.PageNo <= 0) {
            return this.getPageNo(0, sortBy, sortBy);
        } else {
            return this.getPageNo(this.PageNo - 1, sortBy, sortBy);
        }
    }

    public int[] getPanigation() {
        Pageable pageable = PageRequest.of(1, ROWCOUNT);
        Page<HoaDonDoAn> page = repo.findAll(pageable);
        int totalPage = page.getTotalPages();
        int[] array = IntStream.rangeClosed(1, totalPage).toArray();
        return array;
    }

}
