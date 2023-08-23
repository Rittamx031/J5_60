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

import j5_60.cinematicket.cinematicket.entity.ThongTinPhim;
import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;
import j5_60.cinematicket.cinematicket.modelsearch.ThongTinPhimSearch;
import j5_60.cinematicket.cinematicket.repository.ThongTinPhimRepository;

@Service
public class ThongTinPhimService {
  @Autowired
  private ThongTinPhimRepository repo;
  private final int ROWCOUNT = 5;
  private int PageNo = -1;

  public ThongTinPhim createThongTinPhim(ThongTinPhim thongTinPhim) {
    return repo.save(thongTinPhim);
  }

  public ThongTinPhim updateThongTinPhim(ThongTinPhim thongTinPhim) {
    Optional<ThongTinPhim> thongTinPhimDb = this.repo.findById(thongTinPhim.getId());
    if (thongTinPhimDb.isPresent()) {
      ThongTinPhim thongTinPhimud = thongTinPhimDb.get();
      thongTinPhimud.setId(thongTinPhim.getId());
      thongTinPhimud.setTen(thongTinPhim.getTen());
      thongTinPhimud.setDaoDien(thongTinPhim.getDaoDien());
      thongTinPhimud.setNhaSanXuat(thongTinPhim.getNhaSanXuat());
      thongTinPhimud.setDienVien(thongTinPhim.getDienVien());
      thongTinPhimud.setNamPhatHanh(thongTinPhim.getNamPhatHanh());
      thongTinPhimud.setThoiLuong(thongTinPhim.getThoiLuong());
      thongTinPhimud.setTuoiGioiHan(thongTinPhim.getTuoiGioiHan());
      thongTinPhimud.setNgonNgu(thongTinPhim.getNgonNgu());
      thongTinPhimud.setNoiDung(thongTinPhim.getNoiDung());
      thongTinPhimud.setTrailer(thongTinPhim.getTrailer());
      thongTinPhimud.setPoster(thongTinPhim.getPoster());
      thongTinPhimud.setQuocGia(thongTinPhim.getQuocGia());
      thongTinPhimud.setUpdateAt(thongTinPhim.getUpdateAt());
      thongTinPhimud.setCreateAt(thongTinPhim.getCreateAt());
      thongTinPhimud.setCreateBy(thongTinPhim.getCreateBy());
      thongTinPhimud.setUpdateBy(thongTinPhim.getUpdateBy());
      thongTinPhimud.setUpdateAt(thongTinPhim.getUpdateAt());
      thongTinPhimud.setDeleted(thongTinPhim.isDeleted());
      return thongTinPhimud;
    } else {
      throw new ResourceNotFoundException("Can not find Hoa Don with id" + thongTinPhim.getId());
    }
  }

  public List<ThongTinPhim> getAllThongTinPhim() {
    return repo.findAll();
  }

  public List<ThongTinPhim> search(String txtSearch) {
    return repo.search(txtSearch);
  }

  public ThongTinPhim getThongTinPhimById(UUID id) {
    Optional<ThongTinPhim> thongTinPhim = repo.findById(id);
    if (thongTinPhim.isPresent()) {
      return thongTinPhim.get();
    } else {
      throw new ResourceNotFoundException("Cannot Find Hoa Don  with id = " + id);
    }
  }

  public ThongTinPhim deleteThongTinPhim(UUID id) {
    Optional<ThongTinPhim> thongTinPhim = repo.findById(id);
    if (thongTinPhim.isPresent()) {
      return thongTinPhim.get();
    } else {
      throw new ResourceNotFoundException("Cannot Find Hoa Don  with id = " + id);
    }
  }

  public ThongTinPhim setDeteleteState(UUID id) {
    Optional<ThongTinPhim> thongTinPhimDb = this.repo.findById(id);
    if (thongTinPhimDb.isPresent()) {
      ThongTinPhim thongTinPhimud = thongTinPhimDb.get();
      thongTinPhimud.setDeleted(true);
      return thongTinPhimud;
    } else {
      throw new ResourceNotFoundException("Can not find Hoa Don with id" + id);
    }
  }

  public List<ThongTinPhim> getPageNo(int pageNo, String sortBy, String sortDir) {
    this.PageNo = pageNo;
    List<ThongTinPhim> thongTinPhims;
    Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
        : Sort.by(sortBy).descending();
    // Pageable object
    Pageable pageable = PageRequest.of(pageNo - 1, ROWCOUNT, sort);
    // findAll method and pass pageable instance
    Page<ThongTinPhim> page = repo.findAll(pageable);
    thongTinPhims = page.getContent();
    return thongTinPhims;
  }

  public List<ThongTinPhim> getNextPage(String sortBy, String sortDir) {
    if (this.PageNo >= getPanigation().length - 1) {
      return this.getPageNo(this.getPanigation().length - 1, sortBy, sortBy);
    } else {
      return this.getPageNo(this.PageNo + 1, sortBy, sortBy);
    }
  }

  public List<ThongTinPhim> getPrevPage(String sortBy, String sortDir) {
    if (this.PageNo <= 0) {
      return this.getPageNo(0, sortBy, sortBy);
    } else {
      return this.getPageNo(this.PageNo - 1, sortBy, sortBy);
    }
  }

  public int[] getPanigation() {
    Pageable pageable = PageRequest.of(1, ROWCOUNT);
    Page<ThongTinPhim> page = repo.findAll(pageable);
    int totalPage = page.getTotalPages();
    int[] array = IntStream.rangeClosed(1, totalPage).toArray();
    return array;
  }

  public List<ThongTinPhim> fillterThongTinPhim(ThongTinPhimSearch thongTinPhimSearch) {
    List<ThongTinPhim> list = repo.findAll();
    List<ThongTinPhim> result = list.stream()
        .filter(thongtinphim -> ((thongtinphim.getNgonNgu().getId().equals(thongTinPhimSearch.getIdNgonNgu())
            || (thongTinPhimSearch.getIdNgonNgu() == null))
            &&
            (thongtinphim.getQuocGia().getId().equals(thongTinPhimSearch.getIdQuocGia())
                || (thongTinPhimSearch.getIdQuocGia() == null))
            &&
            (thongtinphim.getDaoDien().equalsIgnoreCase(thongTinPhimSearch.getDaoDien())
                || thongTinPhimSearch.getDaoDien() == "" || thongTinPhimSearch.getDaoDien() == null)
            &&
            (thongtinphim.getNhaSanXuat().equalsIgnoreCase(thongTinPhimSearch.getNhaSanXuat())
                || thongTinPhimSearch.getNhaSanXuat() == "" || thongTinPhimSearch.getNhaSanXuat() == null)
            && (thongtinphim.getThoiLuong() <= thongTinPhimSearch.getThoiLuongMax())
            && (thongtinphim.getNamPhatHanh() == thongTinPhimSearch.getNamPhatHanh()
                || thongTinPhimSearch.getNhaSanXuat() == null)
            && (thongtinphim.getThoiLuong() >= thongTinPhimSearch.getThoiLuongMin())
            && (thongtinphim.getTuoiGioiHan() <= thongTinPhimSearch.getTuoiGioiHanMax())
            && (thongtinphim.getTuoiGioiHan() >= thongTinPhimSearch.getTuoiGioiHanMin())))
        .collect(Collectors.toList());
    return result;
  }

}
