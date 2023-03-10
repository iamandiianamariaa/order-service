package com.example.order.repository;

import com.example.order.domain.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourierRepository extends JpaRepository<Courier, Long> {
    @Query(value = "SELECT * FROM couriers c WHERE c.assigned_county = :county AND c.assigned_city = :city AND c.assigned_country = :country " +
            "AND c.no_orders > 0 LIMIT 1", nativeQuery = true)
    Optional<Courier> findAvailableCouriers(@Param("city") String city, @Param("county") String county, @Param("country") String country);

    @Modifying
    @Query(value = "UPDATE couriers c " +
            "SET c.no_orders = c.no_orders - 1 WHERE c.id = :courierId AND c.no_orders > 0", nativeQuery = true)
    void setNoOrders(@Param("courierId") Long courierId);

    @Modifying
    @Query(value = "UPDATE couriers c " +
            "SET c.no_orders = c.no_orders + 1 WHERE c.id = :courierId", nativeQuery = true)
    void setNoOrdersPlus1(@Param("courierId") Long courierId);
}
