/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2017-2017 The OpenNMS Group, Inc.
 * OpenNMS(R) is Copyright (C) 1999-2017 The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is a registered trademark of The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * OpenNMS(R) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with OpenNMS(R).  If not, see:
 *      http://www.gnu.org/licenses/
 *
 * For more information contact:
 *     OpenNMS(R) Licensing <license@opennms.org>
 *     http://www.opennms.org/
 *     http://www.opennms.com/
 *******************************************************************************/

package org.opennms.netmgt.ticketer.jira.fieldmapper;

import java.util.Map;
import java.util.function.Supplier;

import com.atlassian.jira.rest.client.api.domain.FieldSchema;

// Multiselect and checkboxes
public class MultiSelectFieldMapper extends AbstractModifyableOptionKeyFieldMapper {
    public MultiSelectFieldMapper(Supplier<Map<String, String>> optionKeySupplier) {
        super(optionKeySupplier);
    }

    @Override
    public boolean matches(FieldSchema schema) {
        return "com.atlassian.jira.plugin.system.customfieldtypes:multiselect".equals(schema.getCustom())
                || "com.atlassian.jira.plugin.system.customfieldtypes:multicheckboxes".equals(schema.getCustom());
    }

    @Override
    public Object mapToFieldValue(String fieldId, FieldSchema schema, String attributeValue) {
        return new ArrayWrapper().map(eachItem -> createComplexIssueInputField(fieldId, "value", eachItem), attributeValue);
    }
}
