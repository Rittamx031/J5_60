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

import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;
import j5_60.cinematicket.cinematicket.model.entity.MovieGener;
import j5_60.cinematicket.cinematicket.model.entity.key.TheLoaiPhimKey;
import j5_60.cinematicket.cinematicket.repository.MovieGenreRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class MovieGenreService {
    @Autowired
    private MovieGenreRepository repo;
    private final int ROWCOUNT = 5;
    private int PageNo = -1;

    public MovieGener createTheLoaiPhim(MovieGener theLoaiPhimDetail) {
        return repo.save(theLoaiPhimDetail);
    }

    public MovieGener updateTheLoaiPhim(MovieGener theLoaiPhimDetail) {
        Optional<MovieGener> theLoaiPhimDetailDb = this.repo.findById(theLoaiPhimDetail.getId());
        if (theLoaiPhimDetailDb.isPresent()) {
            MovieGener theLoaiPhimDetailud = theLoaiPhimDetailDb.get();
            theLoaiPhimDetailud.setId(theLoaiPhimDetail.getId());
            theLoaiPhimDetailud.setCreateAt(theLoaiPhimDetail.getCreateAt());
            theLoaiPhimDetailud.setCreateBy(theLoaiPhimDetail.getCreateBy());
            theLoaiPhimDetailud.setDeleted(theLoaiPhimDetail.isDeleted());
            theLoaiPhimDetailud.setUpdateAt(theLoaiPhimDetail.getUpdateAt());
            theLoaiPhimDetailud.setUpdateBy(theLoaiPhimDetail.getUpdateBy());
            return theLoaiPhimDetailud;
        } else {
            throw new ResourceNotFoundException("Can not find The Loai Film with id" + theLoaiPhimDetail.getId());
        }
    }

    public List<MovieGener> getAllTheLoaiPhim() {
        return repo.findAll();
    }

    // public List<TheLoaiPhim> searchByName(){
    // return repo.findAll();
    // }
    public MovieGener getTheLoaiPhimById(UUID id_phim, UUID id_the_loai) {
        Optional<MovieGener> phuongthucThanhToan = repo.findById(new TheLoaiPhimKey(id_phim, id_the_loai));
        if (phuongthucThanhToan.isPresent()) {
            return phuongthucThanhToan.get();
        } else {
            throw new ResourceNotFoundException("Cannot Find The Loai Film  with id = ");
        }
    }

    public MovieGener deleteTheLoaiPhim(UUID id_phim, UUID id_the_loai) {
        Optional<MovieGener> phuongthucThanhToan = repo.findById(new TheLoaiPhimKey(id_phim, id_the_loai));
        if (phuongthucThanhToan.isPresent()) {
            return phuongthucThanhToan.get();
        } else {
            throw new ResourceNotFoundException("Cannot Find The Loai Film  with id = ");
        }
    }

    public MovieGener setDeteleteState(UUID id_phim, UUID id_the_loai) {
        Optional<MovieGener> theLoaiPhimDetailDb = this.repo.findById(new TheLoaiPhimKey(id_phim, id_the_loai));
        if (theLoaiPhimDetailDb.isPresent()) {
            MovieGener theLoaiPhimDetailud = theLoaiPhimDetailDb.get();
            theLoaiPhimDetailud.setDeleted(true);
            return theLoaiPhimDetailud;
        } else {
            throw new ResourceNotFoundException(
                    "Can not find The Loai Film  with id_phim=" + id_phim + " and id_the_loai=" + id_the_loai);
        }
    }

    public List<MovieGener> getPageNo(int pageNo, String sortBy, String sortDir) {
        this.PageNo = pageNo;
        List<MovieGener> theLoaiPhimDetails;
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        // Pageable object
        Pageable pageable = PageRequest.of(pageNo - 1, ROWCOUNT, sort);
        // findAll method and pass pageable instance
        Page<MovieGener> page = repo.findAll(pageable);
        theLoaiPhimDetails = page.getContent();
        return theLoaiPhimDetails;
    }

    public List<MovieGener> getNextPage(String sortBy, String sortDir) {
        if (this.PageNo >= getPanigation().length - 1) {
            return this.getPageNo(this.getPanigation().length - 1, sortBy, sortBy);
        } else {
            return this.getPageNo(this.PageNo + 1, sortBy, sortBy);
        }
    }

    public List<MovieGener> getPrevPage(String sortBy, String sortDir) {
        if (this.PageNo <= 0) {
            return this.getPageNo(0, sortBy, sortBy);
        } else {
            return this.getPageNo(this.PageNo - 1, sortBy, sortBy);
        }
    }

    public int[] getPanigation() {
        Pageable pageable = PageRequest.of(1, ROWCOUNT);
        Page<MovieGener> page = repo.findAll(pageable);
        int totalPage = page.getTotalPages();
        int[] array = IntStream.rangeClosed(1, totalPage).toArray();
        return array;
    }
}
