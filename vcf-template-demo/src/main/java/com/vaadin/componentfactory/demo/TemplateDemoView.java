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
package com.vaadin.componentfactory.demo;

import com.vaadin.componentfactory.addons.template.PaperInput;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.demo.DemoView;
import com.vaadin.flow.router.Route;

/**
 * View for {@link PaperInput} demo.
 *
 * @author Vaadin Ltd
 */
@Route("")
public class TemplateDemoView extends DemoView {

    @Override
    public void initView() {
        createBasicPaperInputDemo();

        addCard("Additional code used in the demo",
                new Label("These methods are used in the demo."));
    }

    private void createBasicPaperInputDemo() {
        Div message = createMessageDiv("simple-paper-input-demo-message");

        // begin-source-example
        // source-example-heading: Simple paper input
        PaperInput paperInput = new PaperInput();
        paperInput.addValueChangeListener(ev->{
            updateMessage(message, paperInput);
        });
        // end-source-example

        paperInput.setId("simple-paper-input");

        addCard("Simple paper input", paperInput, message);
    }

   
    // begin-source-example
    // source-example-heading: Additional code used in the demo
    /**
     * Additional code used in the demo
     */
    private void updateMessage(Div message, PaperInput paperInput) {
        message.setText("Entered text: " + paperInput.getValue());
    }

    private Div createMessageDiv(String id) {
        Div message = new Div();
        message.setId(id);
        message.getStyle().set("whiteSpace", "pre");
        return message;
    }
    // end-source-example
}
