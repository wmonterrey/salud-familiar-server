package ni.gob.minsa.hsf.auth.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages="ni.gob.minsa.hsf.auth.config", excludeFilters={ @Filter(Configuration.class)} )
public class ComponentConfig {

}
