/**
 * <a href="http://www.openolat.org">
 * OpenOLAT - Online Learning and Training</a><br>
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); <br>
 * you may not use this file except in compliance with the License.<br>
 * You may obtain a copy of the License at the
 * <a href="http://www.apache.org/licenses/LICENSE-2.0">Apache homepage</a>
 * <p>
 * Unless required by applicable law or agreed to in writing,<br>
 * software distributed under the License is distributed on an "AS IS" BASIS, <br>
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. <br>
 * See the License for the specific language governing permissions and <br>
 * limitations under the License.
 * <p>
 * Initial code contributed and copyrighted by<br>
 * frentix GmbH, http://www.frentix.com
 * <p>
 */
package org.olat.course.nodes.gta.rule;

import java.util.Date;

import org.olat.course.nodes.GTACourseNode;
import org.olat.course.nodes.gta.GTAManager;
import org.olat.course.nodes.gta.ui.BeforeDateTaskRuleEditor;
import org.olat.group.BusinessGroupService;
import org.olat.modules.ModuleConfiguration;
import org.olat.modules.reminder.ReminderRule;
import org.olat.modules.reminder.RuleEditorFragment;
import org.olat.repository.RepositoryEntry;
import org.olat.repository.manager.RepositoryEntryRelationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * Initial date: 10.04.2015<br>
 * @author srosse, stephane.rosse@frentix.com, http://www.frentix.com
 *
 */
@Service
public class SubmissionTaskRuleSPI extends AbstractDueDateTaskRuleSPI {
	
	@Autowired
	private GTAManager gtaManager;
	@Autowired
	private BusinessGroupService businessGroupService;
	@Autowired
	private RepositoryEntryRelationDAO repositoryEntryRelationDao;

	@Override
	public String getLabelI18nKey() {
		return "rule.submission.task";
	}

	@Override
	public String getCategory() {
		return "assessment";
	}

	@Override
	public RuleEditorFragment getEditorFragment(ReminderRule rule, RepositoryEntry entry) {
		return new BeforeDateTaskRuleEditor(rule, entry, SubmissionTaskRuleSPI.class.getSimpleName());
	}

	@Override
	protected Date getDueDate(GTACourseNode gtaNode) {
		Date dueDate = null;
		ModuleConfiguration config = gtaNode.getModuleConfiguration();
		boolean submit = config.getBooleanSafe(GTACourseNode.GTASK_SUBMIT);
		if(submit) {
			dueDate = config.getDateValue(GTACourseNode.GTASK_SUBMIT_DEADLINE);
		}
		return dueDate;
	}
}
