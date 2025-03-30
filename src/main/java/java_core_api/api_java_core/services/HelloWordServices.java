package java_core_api.api_java_core.services;

import org.springframework.stereotype.Service;

@Service
public class HelloWordServices {

    public String hellorWord( String name)  {
        return  "Hello word " + name;
    }
}
