package parser;
import exception.KaidamaException;

import org.junit.jupiter.api.Test;

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
