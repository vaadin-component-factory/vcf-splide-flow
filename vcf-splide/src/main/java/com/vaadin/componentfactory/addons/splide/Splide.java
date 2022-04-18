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
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.dom.ElementFactory;
import elemental.json.JsonValue;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 * Splide component definition. Splide uses splide library to display images and videos as a
 * carousel (see more at https://github.com/Splidejs/splide).
 */
@NpmPackage(value = "@splidejs/splide", version = "3.6.12")
@NpmPackage(value = "@splidejs/splide-extension-video", version = "0.6.8")
@JsModule("./src/vcf-splide.js")
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
  
  @Override
  protected void onDetach(DetachEvent detachEvent) {
    super.onDetach(detachEvent);
    this.getElement().removeAllChildren();
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
        liSlide.getElement().addEventListener(
          "click",
          e -> {
              JsonValue detail = e.getEventData().get("event.detail");
              if (detail.asNumber() > 1) {
                  // double click, do nothing, just ignore
              } else {
                this.getElement().executeJs("vcfsplide.showLightbox($0)", this);
              }
          }
        ).addEventData("event.detail");
        ulList.appendChild(liSlide.getElement());  
      } else {
        VideoSlide videoSlide = (VideoSlide)slide;
        ListItem liSlide = createVideoItem(videoSlide);
        ulList.appendChild(liSlide.getElement());  
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
        ListItem liSlide = createVideoItem(videoSlide);
        ulList.appendChild(liSlide.getElement());           
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
  
  private ListItem createVideoItem(VideoSlide videoSlide) {
    ListItem videoItem = new ListItem();
    videoItem.setClassName("splide__slide");
    
    switch (videoSlide.getType()) {
      case YOUTUBE:
        videoItem.getElement().setAttribute("data-splide-youtube", videoSlide.getUrl()); 
        break;
      case VIMEO:
        videoItem.getElement().setAttribute("data-splide-vimeo", videoSlide.getUrl()); 
        break;
      case HTML:
        videoItem.getElement().setAttribute("data-splide-html-video", videoSlide.getUrl()); 
        break;        
      default:
        break;
    }        
     
    if(StringUtils.isNotBlank(videoSlide.getSrc())) {
      Image image = new Image();
      image.setSrc(videoSlide.getSrc());      
      videoItem.add(image);
    }    
    return videoItem;
  }
  
  /**
   * Return the list of slides that are currently part of the splide component.
   * 
   * @return the list of the slides
   */
  public List<Slide> getSlides() {
    return slides;
  }

  /**
   * Set the list of the slides to be displayed by the splide component.
   * 
   * @param slides the list of slides to display
   */
  public void setSlides(List<Slide> slides) {
    if(this.isAttached()) {
      this.clearSlides();
      for(Slide slide : slides) {
        addSlideElement(slide);
      }
    }    
    this.slides = new ArrayList<>(slides);
  }
  
  /**
   * Add a new slide to the splide carousel.
   * 
   * @param slide the new slide to add
   */
  public void addSlide(Slide slide) {
    if(this.isAttached()) {         
      this.addSlideElement(slide); 
    } 
    this.slides.add(slide);    
  }
  
  private void addSlideElement(Slide slide) {
    ListItem liSlide = slide instanceof ImageSlide ? createImageItem((ImageSlide)slide) : createVideoItem((VideoSlide)slide);
    this.getElement().executeJs("vcfsplide.addSlide($0,$1)", this, liSlide.getElement().toString()); 
  }
  
  /**
   * Remove all current slides present in the carousel.
   */
  public void clearSlides() {
    if(isAttached()) {
      this.getElement().executeJs("vcfsplide.clearSlides($0)", this);
    }
    this.slides.clear();
  }
}
