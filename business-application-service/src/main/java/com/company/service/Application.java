package com.company.service;

import com.company.model.Person;
import org.jbpm.services.api.ProcessService;
import org.jbpm.services.api.RuntimeDataService;
import org.jbpm.services.api.UserTaskService;
import org.kie.api.task.model.Status;
import org.kie.api.task.model.TaskSummary;
import org.kie.internal.query.QueryFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
public class Application {

    @Autowired
    private ProcessService processService;
    @Autowired
    private RuntimeDataService runtimeDataService;
    @Autowired
    private UserTaskService userTaskService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private final String CONTAINER_ID = "business-application-kjar_1.0-SNAPSHOT";

    @PostMapping(value = "/customTaskProcess", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> customTaskProcess(@RequestBody Person person) throws Exception {
        Map<String, Object> vars = new HashMap<>();
        vars.put("person", person);
        Long processInstanceId = processService.startProcess(CONTAINER_ID, "business-application-kjar.CustomTaskProcess", vars);
        //   Collection<NodeInstanceDesc> history = runtimeDataService.getProcessInstanceHistoryCompleted(processInstanceId, null);
        return ResponseEntity.status(HttpStatus.CREATED).body(person);
    }

    @GetMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sayHello(@RequestParam Integer age) throws Exception {

        Long processInstanceId = processService.startProcess(CONTAINER_ID, "business-application-kjar.process");

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("age", age);

        List<TaskSummary> taskSummaries = runtimeDataService.getTasksAssignedAsPotentialOwner("john",
                new QueryFilter());

        taskSummaries.forEach(s -> {
            Status status = taskSummaries.get(0).getStatus();
            if (status == Status.Ready)
                userTaskService.claim(s.getId(), "john");
            userTaskService.start(s.getId(), "john");
            userTaskService.complete(s.getId(), "john", params);
        });

        return ResponseEntity.status(HttpStatus.CREATED).body("Task completed!");
    }
}