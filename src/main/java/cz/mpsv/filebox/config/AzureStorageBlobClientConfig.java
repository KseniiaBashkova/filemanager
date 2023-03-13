package cz.mpsv.filebox.config;

import com.azure.storage.blob.BlobClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureStorageBlobClientConfig {

//    @Value("${blob.connection-string}")
    String connectionString = "DefaultEndpointsProtocol=https;AccountName=stfile;AccountKey=qKId629OJV9UACUnAxvll2cbOX0yyri8eBL7z08de5REyLsNex+REP3Dal7v++FtzJd1ZU8a+Vat+ASttg1rgw==;EndpointSuffix=core.windows.net";

//    @Value("${blob.container-name}")
    String containerName = "container";

    @Bean
    public BlobClientBuilder getClient() {
        BlobClientBuilder client = new BlobClientBuilder();
        client.connectionString(connectionString);
        client.containerName(containerName);
        return client;
    }
}
