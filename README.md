# Splide Addon for Vaadin Flow

Timeline component uses [Splide](https://github.com/Splidejs/splide) library to display images and videos as a carousel.

This component is part of Vaadin Component Factory.

## Description
Splide component allows to create a carousel/slider to display images and videos (youtube, vimeo or html) including a thumbnail control and full screen functionality.

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
    <groupId>com.vaadin.componentfactory</groupId>
    <artifactId>vcf-splide</artifactId>
    <version>${component.version}</version>
</dependency>
```
## Examples

See examples on [splide-demo](https://github.com/vaadin-component-factory/vcf-splide-flow/tree/main/vcf-splide-demo/src/main/java/com/vaadin/componentfactory/demo).

## Missing features or bugs

You can report any issue or missing feature on [GitHub](https://github.com/vaadin-component-factory/vcf-splide-flow/issues).

## Flow documentation
Documentation for flow can be found in [Flow documentation](https://vaadin.com/docs/v14/flow/overview).

## License

Apache Licence 2
