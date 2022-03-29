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
    slidesDiv.setAttribute("id", "main-slider");
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
    thumbnailsDiv.setAttribute("id", "thumbnails-slider");
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
