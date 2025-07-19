package hsf302.agricultural_products_project.repository;

import hsf302.agricultural_products_project.model.Order;
import hsf302.agricultural_products_project.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findTopByUser_UserIdOrderByCreateAtDesc(Long userId);

    @Modifying
    @Transactional
    @Query("UPDATE Order o SET o.orderStatus = :status WHERE o.orderId = :orderId")
    void updateOrderStatus(@Param("orderId") Long orderId, @Param("status") OrderStatus status);

    List<Order> findByUser_UserIdAndOrderStatusIn(Long userId, List<OrderStatus> historyStatuses);
}

