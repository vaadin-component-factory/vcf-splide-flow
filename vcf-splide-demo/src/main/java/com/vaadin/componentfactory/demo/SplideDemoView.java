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
package com.vaadin.componentfactory.demo;

import com.vaadin.componentfactory.addons.splide.ImageSlide;
import com.vaadin.componentfactory.addons.splide.Splide;
import com.vaadin.componentfactory.addons.splide.VideoSlide;
import com.vaadin.componentfactory.addons.splide.VideoType;
import com.vaadin.flow.demo.DemoView;
import com.vaadin.flow.router.Route;
import java.util.Arrays;

/**
 * View for {@link Splide} demo.
 *
 * @author Vaadin Ltd
 */
@Route("")
public class SplideDemoView extends DemoView {

  @Override
  public void initView() {
    createBasicSliderDemo();    
  }

  private void createBasicSliderDemo() {

    // begin-source-example
    // source-example-heading: Simple demo
    
    ImageSlide imageSlide1 = new ImageSlide("https://source.unsplash.com/random/1000x1000?sig=1");
    ImageSlide imageSlide2 = new ImageSlide("https://source.unsplash.com/random/1000x1000?sig=2");
    ImageSlide imageSlide3 = new ImageSlide("https://source.unsplash.com/random/1000x1000?sig=3");
    VideoSlide videoSlide1 = new VideoSlide("https://www.youtube.com/watch?v=cdz__ojQOuU", VideoType.YOUTUBE, "https://source.unsplash.com/random/1000x500?sig=4");
    
    Splide slider = new Splide(Arrays.asList(imageSlide1, imageSlide2, imageSlide3, videoSlide1));
    slider.setId("simple-slider-demo");
    slider.setWidth("400px");
    slider.setHeight("300px");
    
    // end-source-example
    
    addCard("Simple demo", slider);
  }

}
