/*-
 * #%L
 * Splide
 * %%
 * Copyright (C) 2022 Vaadin Ltd
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
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
 * #L%
 */
package com.vaadin.componentfactory.addons.splide;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.dom.ElementFactory;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 * Splide component definition. Splide uses splide library to display images and videos as a
 * carousel (see more at https://github.com/Splidejs/splide).
 */
@NpmPackage(value = "@splidejs/splide", version = "^3.6.12")
@NpmPackage(value = "@splidejs/splide-extension-video", version = "^0.6.8")
@JsModule("src/vcf-splide.js")
@CssImport("@splidejs/splide/dist/css/splide.min.css")
@CssImport("@splidejs/splide-extension-video/dist/css/splide-extension-video.min.css")
@CssImport("./styles/splide.css")
public class Splide extends Div {
  
  private List<Slide> slides = new ArrayList<>();
    
  public Splide() {
    this.setId(String.valueOf(this.hashCode()));
    setClassName("vcfsplide");
  }
  
  public Splide(List<Slide> slides) {
    this();
    this.slides = new ArrayList<>(slides);
  }
  
  @Override
  protected void onAttach(AttachEvent attachEvent) {
    super.onAttach(attachEvent);  
    createSlider(slides);
  }
  
  private void createSlider(List<Slide> slides) {
    this.getElement().appendChild(createSlidesDom(slides));
    this.getElement().appendChild(createThumbnailsDom(slides));
    this.getElement().executeJs("vcfsplide.create($0)", this);
  }
  
  private Element createSlidesDom(List<Slide> slides) {       
    Element slidesDiv = ElementFactory.createDiv();
    slidesDiv.setAttribute("id", "main-slider-" + this.getId().get());
    slidesDiv.getClassList().add("splide");
    
    Element divTrack = ElementFactory.createDiv();
    divTrack.setAttribute("id", "slide-track");
    divTrack.getClassList().add("splide__track");
    
    slidesDiv.appendChild(divTrack);
    
    Element ulList = ElementFactory.createUnorderedList();
    ulList.setAttribute("id", "slide-list");
    ulList.getClassList().add("splide__list");
    
    divTrack.appendChild(ulList);
    
    for (Slide slide : slides) {
      
      if(slide instanceof ImageSlide) {
        ImageSlide imageSlide = (ImageSlide)slide;        
        ListItem liSlide = createImageItem(imageSlide);
        liSlide.addClickListener(e -> {
          this.getElement().executeJs("vcfsplide.showLightbox($0)", this);
        });
        ulList.appendChild(liSlide.getElement());  
      } else {
        VideoSlide videoSlide = (VideoSlide)slide;
        Element liSlide = createVideoItem(videoSlide);
        ulList.appendChild(liSlide);  
      }  
    }
    return slidesDiv;
  }
  
  private Element createThumbnailsDom(List<Slide> slides) {  
    Element thumbnailsDiv = ElementFactory.createDiv();
    thumbnailsDiv.setAttribute("id", "thumbnails-slider-" + this.getId().get());
    thumbnailsDiv.getClassList().add("splide");
    
    Element divTrack = ElementFactory.createDiv();
    divTrack.setAttribute("id", "thumbnails-track");
    divTrack.getClassList().add("splide__track");
    
    thumbnailsDiv.appendChild(divTrack);
    
    Element ulList = ElementFactory.createUnorderedList();
    ulList.setAttribute("id", "thumbnails-list");
    ulList.getClassList().add("splide__list");
    
    divTrack.appendChild(ulList);
    
    for (Slide slide : slides) {      
      if(slide instanceof ImageSlide) {
        ImageSlide imageSlide = (ImageSlide)slide;   
        ListItem liSlide = createImageItem(imageSlide);
        ulList.appendChild(liSlide.getElement());        
      } else {
        VideoSlide videoSlide = (VideoSlide)slide;
        Element liSlide = createVideoItem(videoSlide);
        ulList.appendChild(liSlide);           
      }
    }        
    return thumbnailsDiv;
  }
  
  private ListItem createImageItem(ImageSlide imageSlide) {
    ListItem imageItem = new ListItem();
    imageItem.setClassName("splide__slide");
    Image image = new Image();
    image.setSrc(imageSlide.getSrc());      
    imageItem.add(image);
    return imageItem;
  }
  
  private Element createVideoItem(VideoSlide videoSlide) {
    Element videoItem = ElementFactory.createListItem();
    videoItem.getClassList().add("splide__slide");
    
    switch (videoSlide.getType()) {
      case YOUTUBE:
        videoItem.setAttribute("data-splide-youtube", videoSlide.getUrl()); 
        break;
      case VIMEO:
        videoItem.setAttribute("data-splide-vimeo", videoSlide.getUrl()); 
        break;
      case HTML:
        videoItem.setAttribute("data-splide-html-video", videoSlide.getUrl()); 
        break;        
      default:
        break;
    }        
     
    if(StringUtils.isNotBlank(videoSlide.getSrc())) {
      Image image = new Image();
      image.setSrc(videoSlide.getSrc());      
      videoItem.appendChild(image.getElement());
    }
    
    return videoItem;
  }
  
  public List<Slide> getSlides() {
    return slides;
  }

  public void setSlides(List<Slide> slides) {
    this.slides = slides;
  }
   
}
