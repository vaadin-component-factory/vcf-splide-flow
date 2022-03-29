package com.vaadin.componentfactory.addons.splide;

/**
 * Representation of a slide of type video.
 */
public class VideoSlide extends Slide {

  private String url;
  
  private VideoType type;
    
  public VideoSlide(String url, VideoType type, String src) {
    super(src);
    this.url = url;
    this.type = type;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public VideoType getType() {
    return type;
  }

  public void setType(VideoType type) {
    this.type = type;
  }
  
}
