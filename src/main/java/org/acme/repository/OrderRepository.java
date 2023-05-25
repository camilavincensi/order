package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;
import org.acme.entity.OrderEntity;

@ApplicationScoped
public class OrderRepository implements PanacheRepository<OrderEntity> {
}
