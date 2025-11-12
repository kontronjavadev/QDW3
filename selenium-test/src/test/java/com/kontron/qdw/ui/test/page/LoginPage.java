package com.kontron.qdw.ui.test.page;

import net.sourceforge.jbizmo.commons.selenium.data.*;
import net.sourceforge.jbizmo.commons.selenium.junit.*;
import net.sourceforge.jbizmo.commons.selenium.page.imp.primefaces.*;

public class LoginPage extends AbstractPageObject {
    private static final int TEST_DATA_COUNT = 2;

    /**
     * Constructor
     * @param testContext
     */
    public LoginPage(SeleniumTestContext testContext) {
        super(testContext);
    }

    /**
     * Perform user login
     * @param testData
     * @return an instance of the index page
     * @throws AssertionError if the test data is invalid
     */
    public IndexPage login(PageActionTestData testData) {
        if (testData.getElementTestData().size() != TEST_DATA_COUNT)
            fail("Test data for performing login is either missing or incomplete!");

        setInputFieldValue(testData.getElementTestData().get(0));
        setInputFieldValue(testData.getElementTestData().get(1));

        pressButton(BUTTON_ID_LOG_IN);

        return createPageObject(IndexPage.class);
    }

}
