package dk.dtu.engauge;

import java.util.concurrent.atomic.AtomicLong;

import org.bson.Document;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.BasicDBObject;

@RestController
public class ApiController {

	@Autowired
	private MongoTemplate mongoTemplate;

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@RequestMapping(path = "/api/sessions", method = RequestMethod.POST)
	public String SessionUpload(@RequestBody String jsonString) throws JSONException {

		System.out.println(jsonString);

		Document doc = Document.parse(jsonString);
		
		mongoTemplate.insert(doc, "training-session");

		return "ok";
	}

	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
}