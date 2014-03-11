package ni.gob.minsa.hsf.serializer;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.joda.time.DateTime;

public class CustomDateSerializer extends JsonSerializer<DateTime> {

    @Override
    public void serialize(DateTime value, JsonGenerator gen, 
                          SerializerProvider arg2)
        throws IOException, JsonProcessingException {

    	gen.writeNumber(value.getMillis());
    }

}