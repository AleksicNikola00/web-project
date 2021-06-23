package repository.repos.orderRepo;

import java.util.UUID;

import beans.basic.Order;

public interface IReadOrderRepo extends repository.IReadRepo<UUID,Order> {
}