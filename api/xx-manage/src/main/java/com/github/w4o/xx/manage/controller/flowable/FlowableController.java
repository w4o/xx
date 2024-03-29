package com.github.w4o.xx.manage.controller.flowable;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Frank
 */
@RestController
@RequestMapping("/flowable")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@Slf4j
public class FlowableController {

//    private final RuntimeService runtimeService;
//    private final RepositoryService repositoryService;
//    private final ProcessEngine processEngine;

    @GetMapping("/pic")
    public void showPic(HttpServletResponse resp, String processId) throws Exception {
//        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processId).singleResult();
//        if (pi == null) {
//            return;
//        }
//        List<Execution> executions = runtimeService
//                .createExecutionQuery()
//                .processInstanceId(processId)
//                .list();
//
//        List<String> activityIds = new ArrayList<>();
//        List<String> flows = new ArrayList<>();
//        for (Execution exe : executions) {
//            List<String> ids = runtimeService.getActiveActivityIds(exe.getId());
//            activityIds.addAll(ids);
//        }
//
//        /*
//         * 生成流程图
//         */
//        BpmnModel bpmnModel = repositoryService.getBpmnModel(pi.getProcessDefinitionId());
//        ProcessEngineConfiguration engconf = processEngine.getProcessEngineConfiguration();
//        ProcessDiagramGenerator diagramGenerator = engconf.getProcessDiagramGenerator();
//        byte[] buf = new byte[1024];
//        int legth;
//        try (InputStream in = diagramGenerator.generateDiagram(bpmnModel, "png", activityIds, flows, engconf.getActivityFontName(), engconf.getLabelFontName(), engconf.getAnnotationFontName(), engconf.getClassLoader(), 1.0, false); OutputStream out = resp.getOutputStream()) {
//            while ((legth = in.read(buf)) != -1) {
//                out.write(buf, 0, legth);
//            }
//        }
    }

}
