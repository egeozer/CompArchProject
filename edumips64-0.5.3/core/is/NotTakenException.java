package core.is;

import core.CountController;

public class NotTakenException extends Exception {
    public NotTakenException(){
        CountController.incrementNotTakenCount();
    }
}
