# Splide Addon for Vaadin Flow

Vaadin component that uses [Splide](https://github.com/Splidejs/splide) library to display images and videos as a carousel.

This component is part of Vaadin Component Factory.

## Description
Splide component allows to create a carousel/slider to display images and videos (youtube, vimeo or html) including a thumbnail control and full screen functionality.

## Compatibility
- Version 1.x.x supports Vaadin 14-24
- Version 2.x.x supports Vaadin 25

## Running the component demo
Run from the command line:
- `mvn  -pl vcf-splide-demo -Pwar install jetty:run`

Then navigate to `http://localhost:8080/`

## Installing the component
Run from the command line:
- `mvn clean install -DskipTests`

## Using the component in a Flow application
To use the component in an application using maven,
add the following dependency to your `pom.xml`:
```
<dependency>
    <groupId>org.vaadin.addons.componentfactory</groupId>
    <artifactId>vcf-splide</artifactId>
    <version>${component.version}</version>
</dependency>
```

## Examples
See examples on [splide-demo](https://github.com/vaadin-component-factory/vcf-splide-flow/tree/main/vcf-splide-demo/src/main/java/com/vaadin/componentfactory/demo).

## Demo online
You can check the demo here: https://vcf-demos.org/splide

## Missing features or bugs
You can report any issue or missing feature on [GitHub](https://github.com/vaadin-component-factory/vcf-splide-flow/issues).

## License
Distributed under Apache Licence 2.0.

### Sponsored development
Major pieces of development of this add-on has been sponsored by multiple customers of Vaadin. Read more about Expert on Demand at: [Support](https://vaadin.com/support) and [Pricing](https://vaadin.com/pricing).

