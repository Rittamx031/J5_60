
package j5_60.cinematicket.cinematicket.service;

import j5_60.cinematicket.cinematicket.exception.ResourceNotFoundException;
import j5_60.cinematicket.cinematicket.model.entity.ShowTimes;
import j5_60.cinematicket.cinematicket.repository.ShowtimesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

@Service
@Transactional
public class ShowTimeService {
    @Autowired
    private ShowtimesRepository lichChieuRepository;
    private final int ROWCOUNT = 5;
    private int PageNo = -1;

    public ShowTimes addLichChieu(ShowTimes lichChieu) {
        return lichChieuRepository.save(lichChieu);
    }

    public ShowTimes updateLichChieu(ShowTimes lichChieu) {
        Optional<ShowTimes> lichChieuDb = this.lichChieuRepository.findById(lichChieu.getId());
        if (lichChieuDb.isPresent()) {
            ShowTimes lc = lichChieuDb.get();
            lc.setId(lichChieu.getId());
            lc.setPhongChieu(lichChieu.getPhongChieu());
            lc.setThongTinPhim(lichChieu.getThongTinPhim());
            lc.setGioiChieu(lichChieu.getGioiChieu());
            lc.setGioiKetThuc(lichChieu.getGioiKetThuc());
            lc.setNgayChieu(lichChieu.getNgayChieu());
            lc.setTrangThai(lichChieu.getTrangThai());
            lc.setUpdateAt(lichChieu.getUpdateAt());
            lc.setCreateAt(lichChieu.getCreateAt());
            lc.setCreateBy(lichChieu.getCreateBy());
            lc.setUpdateBy(lichChieu.getUpdateBy());
            lc.setDeleted(lichChieu.isDeleted());
            return lc;
        } else {
            throw new ResourceNotFoundException("Can not find lich chieu with id: " + lichChieu.getId());
        }
    }

    public List<ShowTimes> getAllLichChieu() {
        return lichChieuRepository.findAll();
    }

    public ShowTimes getLichChieuById(UUID id) {
        Optional<ShowTimes> lichChieu = lichChieuRepository.findById(id);
        if (lichChieu.isPresent()) {
            return lichChieu.get();
        } else {
            throw new ResourceNotFoundException("Can not find lich chieu with id: " + id);
        }
    }

    public ShowTimes deleteLichChieu(UUID id) {
        Optional<ShowTimes> lichChieu = lichChieuRepository.findById(id);
        if (lichChieu.isPresent()) {
            return lichChieu.get();
        } else {
            throw new ResourceNotFoundException("Can not find lich chieu with id: " + id);
        }
    }

    public ShowTimes setDeleteState(UUID id) {
        Optional<ShowTimes> lichChieuDb = this.lichChieuRepository.findById(id);
        if (lichChieuDb.isPresent()) {
            ShowTimes lc = lichChieuDb.get();
            lc.setDeleted(true);
            return lc;
        } else {
            throw new ResourceNotFoundException("Can not find lich chieu with id: " + id);
        }
    }

    public List<ShowTimes> getPageNo(int pageNo, String sortBy, String sortDir) {
        this.PageNo = pageNo;
        List<ShowTimes> lichChieus;
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, ROWCOUNT, sort);
        Page<ShowTimes> page = lichChieuRepository.findAll(pageable);
        lichChieus = page.getContent();
        return lichChieus;
    }

    public int[] getPanigation() {
        Pageable pageable = PageRequest.of(1, ROWCOUNT);
        Page<ShowTimes> page = lichChieuRepository.findAll(pageable);
        int totalPage = page.getTotalPages();
        int[] array = IntStream.rangeClosed(0, totalPage).toArray();
        return array;
    }

    public List<ShowTimes> getNextPage(String sortBy, String sortDir) {
        if (this.PageNo >= getPanigation().length - 1) {
            return this.getPageNo(this.getPanigation().length - 1, sortBy, sortDir);
        } else {
            return this.getPageNo(this.PageNo + 1, sortBy, sortDir);
        }
    }

    public List<ShowTimes> getPrevPage(String sortBy, String sortDri) {
        if (this.PageNo <= 0) {
            return this.getPageNo(0, sortBy, sortBy);
        } else {
            return this.getPageNo(this.PageNo - 1, sortBy, sortBy);
        }
    }

    @Service
    public class LichChieuScheduler {

        @Autowired
        private ShowTimeService lichChieuService;

        // Thực hiện kiểm tra và cập nhật trạng thái trước 3 phút trước thời gian chiếu
        @Scheduled(fixedDelay = 1000) // Kiểm tra sau mỗi 1 giây (1.000 miliseconds)
        public void updateLichChieuStatus() {
            List<ShowTimes> ls = lichChieuService.getAllLichChieu();
            LocalDateTime now = LocalDateTime.now();

            for (ShowTimes lichChieu : ls) {
                LocalDateTime gioChieu = lichChieu.getNgayChieu().atTime(lichChieu.getGioiChieu());
                Duration timeDifference = Duration.between(now, gioChieu);

                if (timeDifference.toMinutes() <= 3 && lichChieu.getTrangThai() != 2) {
                    lichChieu.setTrangThai(2); // Chuyển trạng thái thành 2 (hoặc bất kỳ trạng thái bạn định nghĩa)
                    lichChieuService.updateLichChieu(lichChieu);
                }
            }
        }
    }
}