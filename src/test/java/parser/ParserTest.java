package parser;
import kaidama.command.AddCommand;
import kaidama.command.Command;
import kaidama.exception.KaidamaException;
import kaidama.parser.Parser;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void isDateFormatTest() throws KaidamaException {
        String dateFail = "2020/12/02";
        String dateSuccess = "02/02/2024";
        Parser parser = new Parser();
        assertEquals(false, parser.isDateFormat(dateFail));
        assertEquals(true, parser.isDateFormat(dateSuccess));
    }

}
