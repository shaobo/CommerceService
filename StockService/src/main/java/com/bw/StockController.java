package com.bw;


import com.bw.entity.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {

    @Autowired
    ProductRepository productRepository;

    @RequestMapping("/updateStock")//1)
    public boolean updateStock(@RequestParam int qty, @RequestParam long productId) {
        int count = productRepository.updateStock(qty, productId);
        return count==1;
    }
}
