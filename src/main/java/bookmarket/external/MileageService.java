
package bookmarket.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="mileage", url="${api.mileage.url}")
public interface MileageService {

    @RequestMapping(method= RequestMethod.POST, path="/mileages")
    public void payReq(@RequestBody Mileage mileage);

}