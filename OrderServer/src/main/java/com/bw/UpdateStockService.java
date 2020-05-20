package com.bw;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("STOCK-SERVICE")
public interface UpdateStockService {

    @RequestMapping("/updateStock")//1)
    boolean updateStock(@RequestParam int qty, @RequestParam long productId);
}
