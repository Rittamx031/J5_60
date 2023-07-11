package j5_60.cinematicket.cinematicket.exception;

import java.util.UUID;

public class LichChieuNotFoundException extends RuntimeException{
   public LichChieuNotFoundException(UUID id){
        super("Can not find Lich chieu with id: "+id);
    }
}
