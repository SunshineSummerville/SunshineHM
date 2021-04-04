package nl.novi.stuivenberg.springboot.example.security.repository;

import nl.novi.stuivenberg.springboot.example.security.domain.Reservation;
import nl.novi.stuivenberg.springboot.example.security.domain.User;
import nl.novi.stuivenberg.springboot.example.security.payload.request.ReservationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    void deleteByReservationNr(Long aLong);
    //Reservation addReservationToUserById(Long userid, ReservationRequest reservationRequest);
    //Optional<Reservation> findByReservation(Long reservationNr);
    //<Reservation> findAllReservations(Long reservationNr);
    //Boolean existsByReservationNr(String reservationNr);


}