package com.ccread.bookeack.api;


import com.ccread.common.result.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "ccread-bookeack",fallback = BookeackFeignFallback.class)
public interface BookeackFeign {

    @PostMapping("/bookeack/createPurchase")
    R createPurchase(@RequestParam Long novelId);


}
