package j5_60.cinematicket.cinematicket.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserInfo {
  private String name;
  private String password;
  private String roles;
}
