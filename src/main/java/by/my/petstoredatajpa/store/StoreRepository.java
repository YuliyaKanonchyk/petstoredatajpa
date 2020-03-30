package by.my.petstoredatajpa.store;

import by.my.petstoredatajpa.pet.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Order, Long> {
    Optional<Order> createOrder(Order order);
    Optional<Order> findOrderById(Long orderID);
    Optional<Order> deleteOrderById(Long orderID);
}
