package com.kontron.qdw.boundary.service;

import com.kontron.util.log.TaskNodeLog;

public interface TaskCall {

    TaskNodeLog initTask();

    void execTask(TaskNodeLog ownTask);

}
