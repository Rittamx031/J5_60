package j5_60.cinematicket.cinematicket.model.dto.ghe;

import java.util.List;
import java.util.UUID;

import j5_60.cinematicket.cinematicket.model.entity.Seat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RowSeat {
  UUID idPhongChieu;
  int row;
  List<Seat> rowSeat;
}
