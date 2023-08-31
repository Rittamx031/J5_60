
package j5_60.cinematicket.cinematicket.service.admin;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import j5_60.cinematicket.cinematicket.model.entity.Customer;
import j5_60.cinematicket.cinematicket.model.modelsearch.KhachHangSearch;
import j5_60.cinematicket.cinematicket.repository.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository khRepository;

    public List<Customer> getAllKH() {
        return khRepository.findAll();
    }

    public Page<Customer> findAllKH(Pageable pageable) {
        return khRepository.findAll(pageable);
    }

    public Page<Customer> sapXepTheoNgayTao(Pageable pageable) {
        return khRepository.findAllByOrderByCreateAtDesc(pageable);
    }

    public Customer add(Customer kh) {
     return khRepository.save(kh);
    }

    public Customer update(UUID id, Customer khachHang) {
        Customer kh = khRepository.findById(id).get();
        kh.setHoTen(khachHang.getHoTen());
        kh.setEmail(khachHang.getEmail());
        kh.setPass(khachHang.getPass());
        kh.setGioiTinh(khachHang.getGioiTinh());
        kh.setNgaySinh(khachHang.getNgaySinh());
        kh.setSdt(khachHang.getSdt());
        kh.setTrangThai(khachHang.getTrangThai());
        kh.setUpdateAt(khachHang.getUpdateAt());
     return khRepository.save(kh);
    }

    public void delete(UUID id) {
        Customer kh = khRepository.findById(id).get();
        khRepository.delete(kh);
    }

    public Customer findById(UUID id) {
        return khRepository.findById(id).get();
    }

    public List<Customer> fillterKhachHang(KhachHangSearch khachHangSearch) {
        List<Customer> result = khRepository.getKhachHangListFilter(khachHangSearch);
        return result;
    }
}
