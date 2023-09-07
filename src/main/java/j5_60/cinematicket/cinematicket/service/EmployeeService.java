package j5_60.cinematicket.cinematicket.service;

import j5_60.cinematicket.cinematicket.model.entity.Employee;
import j5_60.cinematicket.cinematicket.model.modelsearch.NhanVienSearch;
import j5_60.cinematicket.cinematicket.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository nvRepository;

    public List<Employee> getAllNV() {
        return nvRepository.findAll();
    }

    public Page<Employee> findAll(Pageable pageable) {
        return nvRepository.findAll(pageable);
    }

    public Page<Employee> sapXep(Pageable pageable) {
        return nvRepository.findAllByOrderByHoTenDesc(pageable);
    }

    public Page<Employee> findAllNV(Pageable pageable) {
        return nvRepository.findAllByOrderByCreateAtDesc(pageable);
    }

    public Employee add(Employee nv) {
        return nvRepository.save(nv);
    }

    public Employee update(UUID id, Employee nhanVien) {
        Employee nv = nvRepository.findById(id).get();
        nv.setHoTen(nhanVien.getHoTen());
        nv.setEmail(nhanVien.getEmail());
        nv.setGioiTinh(nhanVien.isGioiTinh());
        nv.setNgaySinh(nhanVien.getNgaySinh());
        nv.setPass(nhanVien.getPass());
        nv.setSdt(nhanVien.getSdt());
        nv.setIdCV(nhanVien.getIdCV());
        nv.setTrangThai(nhanVien.getTrangThai());
        nv.setUpdateAt(nhanVien.getUpdateAt());
        return nvRepository.save(nv);
    }

    public void delete(UUID id) {
        Employee nv = nvRepository.findById(id).get();
        nvRepository.delete(nv);
    }

    public Employee findById(UUID id) {
        return nvRepository.findById(id).get();
    }

    public List<Employee> fillterNhanVien(NhanVienSearch nhanVienSearch) {
        List<Employee> result = nvRepository.getNhanVienListFilter(nhanVienSearch);
        return result;
    }
}
