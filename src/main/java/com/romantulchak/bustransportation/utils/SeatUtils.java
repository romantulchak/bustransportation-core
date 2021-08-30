package com.romantulchak.bustransportation.utils;

import com.romantulchak.bustransportation.model.Route;
import com.romantulchak.bustransportation.model.Seat;

public class SeatUtils {

    private SeatUtils(){}

    public static boolean isSeatBooked(Route route, Seat seat) {
        return seat.getTickets()
                .stream()
                .anyMatch(ticket -> {
                    boolean ifRouteStartsBefore = route.getEntranceStop() < ticket.getRoute().getEntranceStop() &&
                            route.getExitStop() <= ticket.getRoute().getEntranceStop();

                    boolean ifRouteStartsAfter = route.getEntranceStop() >= ticket.getRoute().getExitStop() &&
                            route.getExitStop() != route.getEntranceStop();
                    return !ifRouteStartsBefore && !ifRouteStartsAfter;
                });
    }
}
