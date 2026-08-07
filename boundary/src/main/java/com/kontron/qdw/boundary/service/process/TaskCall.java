package com.kontron.qdw.boundary.service.process;

import com.kontron.util.log.TaskNodeLog;

public interface TaskCall {

    TaskNodeLog initTask();

    void execTask(TaskNodeLog ownTask);

}
