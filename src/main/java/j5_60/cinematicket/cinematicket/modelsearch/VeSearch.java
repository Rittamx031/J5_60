package j5_60.cinematicket.cinematicket.modelsearch;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VeSearch {
  UUID idHoaDon;
  UUID idLichChieu;
  UUID idGhe;
  LocalDate ngayDatVeMin;
  LocalDate ngayDatVeMax;
  double giaMin;
  double giaMax;
}
