package j5_60.cinematicket.cinematicket.security.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import j5_60.cinematicket.cinematicket.entity.KhachHang;
import j5_60.cinematicket.cinematicket.repository.CustomerRepository;
import j5_60.cinematicket.cinematicket.security.config.UserInfoUserDetails;
import j5_60.cinematicket.cinematicket.security.dto.UserInfo;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomerInfoService implements UserDetailsService {
  private final CustomerRepository repository;
  @Autowired
  PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) {
    Optional<KhachHang> khachhang = repository.getuser(username);
    if (!khachhang.isPresent() || khachhang == null) {
      throw new UsernameNotFoundException("can not find nhan vien with username khachhang");
    }
    String Roles = "USER";
    UserInfo userinfo = new UserInfo(khachhang.get().getEmail(), khachhang.get().getPass(), Roles);
    return new UserInfoUserDetails(userinfo);
  }
}
