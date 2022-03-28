/*
 * Copyright 2000-2017 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.componentfactory.addons.template;

import static org.junit.Assert.assertEquals;
import com.vaadin.componentfactory.addons.template.PaperInput;
import com.vaadin.flow.component.UI;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import net.jcip.annotations.NotThreadSafe;

@NotThreadSafe
public class PaperInputTest {

    private UI ui;

    @Before
    public void setUp() {
        ui = new UI();
        UI.setCurrent(ui);
    }

    @After
    public void tearDown() {
        UI.setCurrent(null);
    }

    @Test
    public void paperInput_basicCases() {
        PaperInput pinput = new PaperInput();

        assertEquals(null, pinput.getValue());
        assertEquals(null, pinput.getElement().getProperty("value"));

        pinput.setValue("test");
        assertEquals("test", pinput.getElement().getProperty("value"));
    }

}
