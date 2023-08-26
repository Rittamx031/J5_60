package j5_60.cinematicket.cinematicket.dto.ghe;

import java.util.UUID;

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
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Seat {
  UUID id;
  int row;
  int colum;
  String ten;
  UUID idPhongChieu;
  UUID idLoaiGhe;

  public static String convertToCellReference(int row, int column) {
    StringBuilder cellReference = new StringBuilder();
    // Convert column index to letters (A, B, ..., Z, AA, AB, ...)
    while (row > 0) {
      int remainder = (row - 1) % 26;
      char letter = (char) ('A' + remainder);
      cellReference.insert(0, letter);
      row = (row - 1) / 26;
    }
    cellReference.append(column);
    return cellReference.toString();
  }

}
