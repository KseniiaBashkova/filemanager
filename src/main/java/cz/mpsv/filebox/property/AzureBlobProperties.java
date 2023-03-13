package cz.mpsv.filebox.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration("azure.myblob")
public class AzureBlobProperties {

    private String url;
    private String container;
}
