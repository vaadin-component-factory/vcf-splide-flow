# Splide Addon for Vaadin Flow

Timeline component uses [Splide](https://github.com/Splidejs/splide) library to display images and videos as a carousel.

This component is part of Vaadin Component Factory

## Running the component demo
Run from the command line:
- `mvn  -pl vcf-splide-demo -Pwar install jetty:run`

Then navigate to `http://localhost:8080/`

## Installing the component
Run from the command line:
- `mvn clean install -DskipTests`

## Profiles
### Profile "directory"
This profile, when enabled, will create the zip file for uploading to Vaadin's directory

### Profile "production"
This profile, when enabled, will execute a production build for the demo

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

## Flow documentation
Documentation for flow can be found in [Flow documentation](https://vaadin.com/docs/v14/flow/overview).

## License

Apache Licence 2