package uol.compass.msorder.services;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import uol.compass.msorder.model.entities.AddressEntity;

@Service
@FeignClient(url= "https://viacep.com.br/ws/" , name = "viacep")
public interface AddressService {

    @GetMapping("{cep}/json")
    public AddressEntity findAddress(@PathVariable("cep") String cep);

}
