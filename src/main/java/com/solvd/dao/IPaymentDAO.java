package com.solvd.dao;

import com.solvd.model.Payment;

import java.util.List;
import java.util.Optional;

public interface IPaymentDAO extends IBaseDAO<Payment> {

    Optional<Payment> getByTripId(int tripId);

    List<Payment> getByStatus(String status);
}
