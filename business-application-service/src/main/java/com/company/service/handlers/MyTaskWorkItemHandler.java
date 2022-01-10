package com.company.service.handlers;

import com.company.model.Person;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;
import org.springframework.stereotype.Component;

@Component("CustomTask")
public class MyTaskWorkItemHandler implements WorkItemHandler {

    @Override
    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
        System.out.println("Work item being executed " + workItem);
        Person person = (Person) workItem.getParameter("MyFirstParam");
        person.setFirstName(person.getFirstName() + "_" + workItem.getId());
        manager.completeWorkItem(workItem.getId(), workItem.getParameters());
    }

    @Override
    public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {

    }

}