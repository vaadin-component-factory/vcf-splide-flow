/*
 * Copyright 2000-2017 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.componentfactory.addons.template;

import com.vaadin.flow.component.AbstractSinglePropertyField;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;

/**
 * Template Addon showing the basics of how to build a Vaadin Wrapper Addon
 *
 */
@Tag("paper-input")
@NpmPackage(value = "@polymer/paper-input", version = "^3.2.1")
@JsModule("@polymer/paper-input/paper-input.js")
public class PaperInput extends AbstractSinglePropertyField<PaperInput, String> {

    public PaperInput() {
        this(null);
    }

    public PaperInput(String defaultValue) {
        super("value", defaultValue, true);
    }

    @Override
    protected void setPresentationValue(String newPresentationValue) {
        this.getElement().setProperty("value", newPresentationValue);
    }
}
