package lab15.interfaces;

import java.io.IOException;
import java.util.List;

public interface IStringProcessor {
    List<String> rarestWords(List<String> text) throws IOException;
}
