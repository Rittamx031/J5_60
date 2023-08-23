package j5_60.cinematicket.cinematicket.security.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import j5_60.cinematicket.cinematicket.entity.NhanVien;
import j5_60.cinematicket.cinematicket.repository.EmployeeRepository;
import j5_60.cinematicket.cinematicket.security.config.UserInfoUserDetails;
import j5_60.cinematicket.cinematicket.security.dto.UserInfo;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmployeeInfoService implements UserDetailsService {
  private final EmployeeRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<NhanVien> nhanvien = repository.getuser(username);
    if (!nhanvien.isPresent() || nhanvien == null) {
      throw new UsernameNotFoundException("can not find nhan vien with username nhanvien");
    }
    String Roles = nhanvien.get().getIdCV().getTenCV();
    UserInfo userinfo = new UserInfo(nhanvien.get().getEmail(), nhanvien.get().getPass(), Roles);
    return new UserInfoUserDetails(userinfo);
  }
  // @Autowired

}
