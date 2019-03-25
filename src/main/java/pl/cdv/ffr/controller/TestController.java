package pl.cdv.ffr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.cdv.ffr.model.Test;
import pl.cdv.ffr.service.TestService;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class TestController {

    @Autowired
    TestService testService;

    @RequestMapping(path = "/tests", method = RequestMethod.GET)
    public List<Test> getAllTests() {
        return testService.findAllTests();
    }

    @RequestMapping(path = "/tests", method = RequestMethod.POST)
    public Test createTest(@RequestBody Test test) {
        return testService.createFlat(test);
    }
}