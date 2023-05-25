package org.acme.service;

import org.acme.client.CostumerClient;
import org.acme.client.ProductClient;
import org.acme.dto.CostumerDTO;
import org.acme.dto.OrderDTO;
import org.acme.dto.ProductDTO;
import org.acme.entity.OrderEntity;
import org.acme.repository.OrderRepository;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;


@ApplicationScoped
public class OrderService {

    @Inject
    OrderRepository orderRepository;

    @Inject
    @RestClient
    CostumerClient costumerClient;

    @Inject
    @RestClient
    ProductClient productClient;
    public List<OrderDTO> getAllOrders(){

        List<OrderDTO> orders = new ArrayList<>();

        orderRepository.findAll().stream().forEach(item->{
            orders.add(mapEntityToDTO(item));
        });

        return orders;
    }


    public void saveNewOrder(OrderDTO orderDTO){

        CostumerDTO costumerDTO = costumerClient.getCostumerById(orderDTO.getCostumerId());

        if(costumerDTO.getName().equals(orderDTO.getCostumerName())
            && productClient.getProductById(orderDTO.getProductId()) != null){

            orderRepository.persist(mapDTOToEntity(orderDTO));
        }else{
            throw new NotFoundException();
        }

    }


    private OrderDTO mapEntityToDTO(OrderEntity item){

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setProductId(item.getProductId());
        orderDTO.setCostumerId(item.getCostumerId());
        orderDTO.setOrderValue(item.getOrderValue());
        orderDTO.setCostumerName(item.getCostumerName());

        return orderDTO;
    }

    private OrderEntity mapDTOToEntity(OrderDTO item){

        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setProductId(item.getProductId());
        orderEntity.setCostumerId(item.getCostumerId());
        orderEntity.setOrderValue(item.getOrderValue());
        orderEntity.setCostumerName(item.getCostumerName());

        return orderEntity;
    }


}
