package tpe.microservicioadmin.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "microservicio-user", url = "http://localhost:8003")
public interface UserFeignClient {

    @DeleteMapping("users/{id}")
    void delete(@PathVariable Long id);

    @PutMapping("/users/active/{id}")
    void inactive(@PathVariable Long id);

}
