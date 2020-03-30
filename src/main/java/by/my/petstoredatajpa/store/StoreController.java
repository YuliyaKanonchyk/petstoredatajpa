package by.my.petstoredatajpa.store;

import by.my.petstoredatajpa.pet.model.Pet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/store")
public class StoreController {
    private StoreRepository storeRepository;

    public StoreController(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @PostMapping(path = "/order")
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        Optional<Order> placedOrder = storeRepository.createOrder(order);
        if (placedOrder.isPresent()) {
            return new ResponseEntity<>(placedOrder.get(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @GetMapping(path = "/order/{orderID}")
    public ResponseEntity<Order> findOrderById(@PathVariable @Min(0) Long orderID) {
        Optional<Order> orderById = storeRepository.findOrderById(orderID);
        if (orderById.isPresent()) {
            return new ResponseEntity<>(orderById.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = "/order/{orderID}")
    // FIXME: 30.03.2020 CHECK!!!!!
    public ResponseEntity<Order> deleteOrderById(@PathVariable @Min(0) Long orderID) {
        if (storeRepository.deleteOrderById(orderID).isPresent()) {
            return new ResponseEntity<>(storeRepository.deleteOrderById(orderID).get(), HttpStatus.OK);
        } else if (!storeRepository.deleteOrderById(orderID).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/inventory")
    // FIXME: 30.03.2020 CHECK!!!!!
    public ResponseEntity<HashMap<String,Integer>> getInventory(){
        HashMap<String, Integer> orderMap = new HashMap<>();
        List<Order> all = storeRepository.findAll();
        for (Order order : all) {
            orderMap.put(String.valueOf(order.getStatus()), order.getQuantity());
        }
        return new ResponseEntity<>(orderMap, HttpStatus.OK);
    }
}
