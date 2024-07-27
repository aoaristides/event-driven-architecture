package br.com.microservices.orchestrated.inventoryservice.core.services;

import br.com.microservices.orchestrated.inventoryservice.core.dtos.Event;

public interface InventoryService {

    void updateInventory(final Event event);

    void rollbackInventory(final Event event);

}
