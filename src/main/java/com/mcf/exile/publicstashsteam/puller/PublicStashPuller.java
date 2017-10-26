package com.mcf.exile.publicstashsteam.puller;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.mcf.exile.publicstashsteam.queue.Queue;
import java.io.InputStream;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PublicStashPuller {

  private final Queue queue;
  private final RestTemplate restTemplate;
  private final static String BASE_URI = "http://api.pathofexile.com/public-stash-tabs?id=";
  private final static String NEXT_CHANGE_ID = "next_change_id";

  private JsonFactory jsonFactory;


  @PostConstruct
  public void setup() {
    jsonFactory = new JsonFactory();
  }

  public String getAndSend(String nextChangeId) {
    ResponseEntity<Resource> responseEntity =
        restTemplate.exchange(BASE_URI + nextChangeId, HttpMethod.GET, null, Resource.class);

    try {
      InputStream inputStream = responseEntity.getBody().getInputStream();
      byte[] result = StreamUtils.copyToByteArray(inputStream);
      queue.send(result);

      return getNextChangeId(result);

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private String getNextChangeId(byte[] data) throws Exception {
    JsonParser parser = jsonFactory.createParser(data);
    // loop until token equal to "}"
    while (parser.nextToken() != JsonToken.END_OBJECT) {
      String fieldname = parser.getCurrentName();
      if (NEXT_CHANGE_ID.equals(fieldname)) {
        parser.nextToken();
        String nextChangeId = parser.getText();
        parser.close();
        return nextChangeId;
      }
    }
    parser.close();
    return null;
  }
}
