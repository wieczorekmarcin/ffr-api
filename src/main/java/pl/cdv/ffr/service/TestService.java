package pl.cdv.ffr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.cdv.ffr.model.Test;
import pl.cdv.ffr.repository.TestRepository;

import java.util.List;

@Service
public class TestService {

    @Autowired
    TestRepository testRepository;



    public List<Test> findAllTests() {
        return testRepository.findAll();
    }

    public Test createFlat(Test test) {
        return testRepository.save(test);
    }


}