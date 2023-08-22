package j5_60.cinematicket.cinematicket.security.jwt;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * jwtResponse
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class jwtResponse {
  private String token;
  private String type = "Bearer";
  private String email;
  private List<String> Roles;

  public jwtResponse(String token, String email, List<String> Roles) {
    this.token = token;
    this.email = email;
    this.Roles = Roles;
  }
}