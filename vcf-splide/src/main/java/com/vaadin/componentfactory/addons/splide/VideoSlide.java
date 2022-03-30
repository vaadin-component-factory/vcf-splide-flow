/*-
 * #%L
 * Timeline
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
