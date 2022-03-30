package com.vaadin.componentfactory.demo;

import com.vaadin.componentfactory.addons.splide.ImageSlide;
import com.vaadin.componentfactory.addons.splide.Splide;
import com.vaadin.componentfactory.addons.splide.VideoSlide;
import com.vaadin.componentfactory.addons.splide.VideoType;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.demo.DemoView;
import com.vaadin.flow.router.Route;
import java.util.Arrays;

/**
 * View for {@link Splide} demo.
 */
@Route("")
public class SplideDemoView extends DemoView {

  @Override
  public void initView() {
    createImageSliderDemo();
    createImageAndVideoSliderDemo();
  }

  private void createImageSliderDemo() {

    // begin-source-example
    // source-example-heading: Images slider demo
    ImageSlide slide1 = new ImageSlide("images/slide_1.jpg");
    ImageSlide slide2 = new ImageSlide("images/slide_2.png");
    ImageSlide slide3 = new ImageSlide("https://source.unsplash.com/random/1000x1000?sig=3");

    Splide slider = new Splide(Arrays.asList(slide1, slide2, slide3));
    slider.setId("images-slider-demo");
    slider.setWidth("450px");
    slider.setHeight("300px");
    slider.getElement().getStyle().set("margin", "auto");

    // end-source-example

    addCard("Images slider demo", slider);
  }

  private void createImageAndVideoSliderDemo() {

    // begin-source-example
    // source-example-heading: Images and videos slider demo

    HorizontalLayout sliderLayoutContainer = new HorizontalLayout();
    sliderLayoutContainer.setSpacing(false);
    sliderLayoutContainer.setWidth("600px");
    sliderLayoutContainer.setHeight("400px");
    sliderLayoutContainer.getElement().getStyle().set("margin", "auto");

    ImageSlide slide1 = new ImageSlide("images/slide_1.png");
    ImageSlide slide2 = new ImageSlide("images/slide_2.png");
    ImageSlide slide3 = new ImageSlide("images/slide_3.png");
    VideoSlide slide4 = new VideoSlide("https://www.youtube.com/watch?v=C78LjVQhejI",
        VideoType.YOUTUBE, "images/slide_4.png");
    ImageSlide slide5 = new ImageSlide("images/slide_5.png");
    ImageSlide slide6 = new ImageSlide("images/slide_6.png");
    ImageSlide slide7 = new ImageSlide("images/slide_7.png");

    Splide slider =
        new Splide(Arrays.asList(slide1, slide2, slide3, slide4, slide5, slide6, slide7));
    slider.setId("images-videos-slider-demo");
    slider.setWidthFull();
    slider.setHeightFull();

    sliderLayoutContainer.add(slider);

    // end-source-example

    addCard("Images and videos slider demo", sliderLayoutContainer);
  }

}
