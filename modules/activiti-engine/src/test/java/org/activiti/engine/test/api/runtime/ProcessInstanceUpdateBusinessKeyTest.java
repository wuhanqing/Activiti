/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.activiti.engine.test.api.runtime;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.Deployment;

public class ProcessInstanceUpdateBusinessKeyTest extends PluggableActivitiTestCase {

  @Deployment
  public void testProcessInstanceUpdateBusinessKey() {

    ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().singleResult();
    runtimeService.startProcessInstanceByKey(processDefinition.getKey());

    ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().singleResult();
    assertEquals("bzKey", processInstance.getBusinessKey());

    HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().singleResult();
    assertEquals("bzKey", historicProcessInstance.getBusinessKey());

  }

  public static class UpdateBusinessKeyExecutionListener implements ExecutionListener {
    public void notify(DelegateExecution delegateExecution) {
      delegateExecution.updateProcessBusinessKey("bzKey");
    }
  }

}