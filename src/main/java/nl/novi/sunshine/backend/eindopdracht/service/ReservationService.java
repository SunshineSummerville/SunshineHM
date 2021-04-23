package nl.novi.sunshine.backend.eindopdracht.service;

import nl.novi.sunshine.backend.eindopdracht.domain.Reservation;
import nl.novi.sunshine.backend.eindopdracht.domain.User;
import nl.novi.sunshine.backend.eindopdracht.exception.ReservationNotFoundException;
import nl.novi.sunshine.backend.eindopdracht.exception.UserNotFoundException;
import nl.novi.sunshine.backend.eindopdracht.payload.request.ReservationRequest;
import nl.novi.sunshine.backend.eindopdracht.repository.ReservationRepository;
import nl.novi.sunshine.backend.eindopdracht.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;


@Service
public class ReservationService  {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public ReservationRepository reservationRepository;

    //@Override
    public Optional<Reservation> getReservationByNr (Long reservationNr) {
        return reservationRepository.findById(reservationNr);
    }

    public List<Reservation> getAllReservations(){
        List<Reservation> allReservations = reservationRepository.findAll();
        return allReservations;
    }

    public Reservation saveReservation(ReservationRequest reservationRequest, Principal principal) {
        // principal.getName() == 'handyman'
        User handyman = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new UserNotFoundException("Handyman Not Found: " + principal.getName()));

        User customer = userRepository.findByUsername(reservationRequest.getCustomer())
                .orElseThrow(() -> new UserNotFoundException("Customer Not Found: " + reservationRequest.getCustomer()));


        // /api/reservaction
        // Controller > Request (2 paramaters uit postman) + authorization token
        // Service > vertaalt de request naar een custom reservation
        // principal: daarmee haal je huidige ingelogde gebruiker op

        return reservationRepository.save(new Reservation(
                reservationRequest.getReservationDate(),
                handyman,
                customer
        ));
    }



    //@Override
//    public Reservation addResevation (Reservation newResevation) {
//        return reservationRepository.save(newResevation);
//    }

    //@Override
    public String deleteReservationByNr (Long reservatonNr) {
        Optional<Reservation> reservation = reservationRepository.findById(reservatonNr);
        if (reservation.isPresent()) {
            reservationRepository.deleteById(reservatonNr);
            return "Reservation " + reservation.get().getReservationNr() + " is verwijderd";
        }
        throw new ReservationNotFoundException( "Reservering bestaat niet.");
    }

    //@Override
    public Reservation updateReservationByNr(Long id, Reservation updatedReservation) {
        Optional<Reservation> reservationFromDb = reservationRepository.findById(id);

        if(reservationFromDb.isPresent()) { // check of reservering aanwezig is adhv nr
            Reservation oldReservation = reservationFromDb.get();
            oldReservation.setReservationDate(updatedReservation.getReservationDate());
            oldReservation.setCustomer(updatedReservation.getCustomer());

            return reservationRepository.save(oldReservation);
        }
        throw new ReservationNotFoundException(id);
    }





}
