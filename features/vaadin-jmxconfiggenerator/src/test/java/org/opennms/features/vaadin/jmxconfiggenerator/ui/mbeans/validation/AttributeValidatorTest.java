package org.opennms.features.vaadin.jmxconfiggenerator.ui.mbeans.validation;

import com.google.common.base.Strings;
import com.vaadin.data.Validator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.opennms.features.vaadin.jmxconfiggenerator.Config;
import org.opennms.features.vaadin.jmxconfiggenerator.TestHelper;
import org.opennms.features.vaadin.jmxconfiggenerator.ui.mbeans.NameProvider;
import org.opennms.features.vaadin.jmxconfiggenerator.ui.mbeans.SelectionManager;
import org.opennms.xmlns.xsd.config.jmx_datacollection.Attrib;
import org.opennms.xmlns.xsd.config.jmx_datacollection.CompMember;
import org.opennms.xmlns.xsd.config.jmx_datacollection.Mbean;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class AttributeValidatorTest {

    private final NameProvider nameProvider = TestHelper.DUMMY_NAME_PROVIDER;

    private ValidationManager validationManager;

    private ValidationResult result;

    @Before
    public void before() {
        result = new ValidationResult();
        validationManager = new ValidationManager(nameProvider, SelectionManager.EMPTY);
    }

    @Test
    public void testAttribAttributeValidator() {
        // simple test first
        Attrib attrib = new Attrib();
        attrib.setAlias("ulf");
        attrib.setName("ulf");

        validationManager.validate(attrib, result);
        Assert.assertEquals(Boolean.TRUE, result.isValid());

        // lets have a length issue
        attrib.setAlias(Strings.repeat("X", Config.ATTRIBUTES_ALIAS_MAX_LENGTH + 1));
        validationManager.validate(attrib, result);
        verifyResult(result, attrib, 1, MaximumLengthValidator.ERROR_MESSAGE);

        // lets also have a name issue
        attrib.setAlias("Ülf");
        validationManager.validate(attrib, result);
        verifyResult(result, attrib, 2, MaximumLengthValidator.ERROR_MESSAGE, AttributeNameValidator.ERROR_MESSAGE);
    }

    @Test
    public void testCompositeMemberValidator() {
        // simple test first
        CompMember compMember = new CompMember();
        compMember.setName("ulf");
        compMember.setAlias("ulf");

        validationManager.validate(compMember, result);
        Assert.assertEquals(Boolean.TRUE, result.isValid());

        // lets have a length issue
        compMember.setAlias(Strings.repeat("X", Config.ATTRIBUTES_ALIAS_MAX_LENGTH + 1));
        validationManager.validate(compMember, result);
        verifyResult(result, compMember, 1, MaximumLengthValidator.ERROR_MESSAGE);

        // lets also have a name issue
        compMember.setAlias("Ülf");
        validationManager.validate(compMember, result);
        verifyResult(result, compMember, 2, MaximumLengthValidator.ERROR_MESSAGE, AttributeNameValidator.ERROR_MESSAGE);
    }

    @Test
    public void testUniqueAliasValidator() {
        Mbean mbean = new Mbean();
        mbean.setName("Mbean 1");
    }

    private void verifyResult(ValidationResult result, Object verifiedObject, int numberErrors, String... expectedErrorMessages) {
        Assert.assertNotNull(result);
        Assert.assertEquals(numberErrors == 0 ? Boolean.TRUE : Boolean.FALSE, result.isValid());
        Assert.assertEquals(numberErrors, result.getErrorCount());

        if (expectedErrorMessages != null && expectedErrorMessages.length > 0) {
            Arrays.sort(expectedErrorMessages);
            List<Validator.InvalidValueException> errors = result.getValidationError(verifiedObject).getExceptionList();
            Collections.sort(errors, new Comparator<Validator.InvalidValueException>() {

                @Override
                public int compare(Validator.InvalidValueException o1, Validator.InvalidValueException o2) {
                    return o1.getMessage().compareTo(o2.getMessage());
                }
            });
            for (int i = 0; i < errors.size(); i++) {
                Assert.assertEquals(expectedErrorMessages[i], errors.get(i).getMessage());
            }
        }
    }
}