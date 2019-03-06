package pl.cdv.ffr.controller;

import org.springframework.web.bind.annotation.*;
import pl.cdv.ffr.model.Person;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {
    private static final List<Person> persons;

    static {
        persons = new ArrayList<>();
        persons.add(new Person("Hello", "World"));
        persons.add(new Person("Foo", "Bar"));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(path = "/persons", method = RequestMethod.GET)
    public static List<Person> getPersons() {
        return persons;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(path = "/persons/{name}", method = RequestMethod.GET)
    public static Person getPerson(@PathVariable("name") String name) {
        return persons.stream()
                .filter(person -> name.equalsIgnoreCase(person.getName()))
                .findAny().orElse(null);
    }
}
