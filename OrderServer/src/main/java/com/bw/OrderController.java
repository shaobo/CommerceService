package com.bw;

import com.bw.entity.Order;
import com.bw.entity.OrderDetail;
import com.bw.entity.Product;
import com.bw.entity.ProductRepository;
import com.sun.media.jfxmedia.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@Slf4j
public class OrderController {

    @Autowired
    UpdateStockService updateStockService;

    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;

    //DTO:data transfer object 传输对象（没有id）  vs 实体对象 Entity （id）
    @PostMapping("/createOrder")
    public boolean createOrder(@RequestBody List<OrderDetailDTO> ods) {
        //订单总金额
        BigDecimal total = new BigDecimal(0);
        //订单对象
        Order order = new Order();
        List<OrderDetail> details = new ArrayList<>();

        //DTO用来接收前端的数据，随后转换为实体对象
        ods.forEach(
                dto -> {
                    Optional<Product> byId = productRepository.findById(dto.getId());
                    Product product = byId.get();
                    //实体
                    OrderDetail orderDetail = new OrderDetail(dto.getPrice(), dto.getQty(), order, product);

                    //计算每条明细的金额
                    BigDecimal currentAmount = dto.getPrice().multiply(BigDecimal.valueOf(dto.getQty()));
                    //注意BigDecimal为不可变对象
                    //将每条明细的金额，累加到订单总金额
                    BigDecimal orderTotal = order.getTotalAmount().add(currentAmount);
                    order.setTotalAmount(orderTotal);
                    details.add(orderDetail);

                    //扣库存
                    boolean success = updateStockService.updateStock(dto.getQty(), dto.getId());
                    if(!success){
                        log.debug("产品{} 扣库存{}个失败",dto.getId(),dto.getQty());
                    }
                });

        //批量保存，提高效率
        orderDetailRepository.saveAll(details);

        return true;
    }

    @RequestMapping("/listProduct")
    public List<Product> listProduct(){
        return productRepository.findAll();
    }
}
