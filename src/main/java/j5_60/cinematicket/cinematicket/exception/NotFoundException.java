package j5_60.cinematicket.cinematicket.exception;

import java.util.UUID;

public class NotFoundException extends RuntimeException{
    public NotFoundException(UUID id){
        super("Can not find object with id: "+id);
    }
}
