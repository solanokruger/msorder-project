package uol.compass.msorder.services;

import lombok.RequiredArgsConstructor;
import org.hibernate.Transaction;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uol.compass.msorder.model.dtos.request.OrderRequestDTO;
import uol.compass.msorder.model.dtos.response.OrderResponseDTO;
import uol.compass.msorder.model.dtos.response.OrderResponseParameters;
import uol.compass.msorder.model.entities.AddressEntity;
import uol.compass.msorder.model.entities.ItemEntity;
import uol.compass.msorder.model.entities.OrderEntity;
import uol.compass.msorder.model.exceptions.OrderNotFoundException;
import uol.compass.msorder.repositories.AddressRepository;
import uol.compass.msorder.repositories.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    private final AddressRepository addressRepository;
    private final ItemServiceImpl itemService;

    private final AddressService addressService;

    @Autowired
    private final ModelMapper modelMapper;


    @Override
    public OrderResponseDTO create(OrderRequestDTO request) {
        AddressEntity orderAddress = createAddress(request);

        OrderEntity orderToCreate = new OrderEntity();

        itemService.create(request.getItems());

        orderToCreate.setItems(request.getItems());
        orderToCreate.setCpf(request.getCpf());
        orderToCreate.setTotal(calculateTotal(request.getItems()));
        orderToCreate.setAddressEntity(orderAddress);

        OrderEntity newOrder = orderRepository.save(orderToCreate);

        return modelMapper.map(newOrder, OrderResponseDTO.class);
    }

    @Override
    public OrderResponseParameters findAll(String cpf, Pageable pageable) {
        Page<OrderEntity> page = cpf == null ?
                orderRepository.findAll(pageable) :
                orderRepository.findAllByCpf(cpf, pageable);
        return createOrderResponseParameters(page);
    }

    @Override
    public OrderResponseDTO findById(Long id) {
        OrderEntity order = orderRepository.findById(id)
                .orElseThrow(OrderNotFoundException::new);

        return modelMapper.map(order, OrderResponseDTO.class);
    }

    private OrderResponseParameters createOrderResponseParameters(Page<OrderEntity> page) {
        List<OrderResponseDTO> order = page.stream()
                .map(this::createOrderResponse)
                .collect(Collectors.toList());

        return OrderResponseParameters.builder()
                .numberOfElements(page.getNumberOfElements())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .orders(order)
                .build();
    }

//    public List<ItemEntity> validateItems(List<ItemEntity> requestItems){
//        List<ItemEntity> existentItems = itemService.findAll();
//
//        ItemEntity newItem = new ItemEntity();
//
//        for (ItemEntity item: requestItems) {
//            newItem.setValue(item.getValue());
//            newItem.setName(item.getName());
//        }
//
//        for (ItemEntity item: existentItems) {
//            if (item.getName().equals(newItem.getName()) && item.getValue() == newItem.getValue()){
//
//            }
//        }
//    }

    public double calculateTotal(List<ItemEntity> items){
        return items.stream().mapToDouble(ItemEntity::getValue).sum();
    }

    public OrderResponseDTO createOrderResponse(OrderEntity order) {
        return modelMapper.map(order, OrderResponseDTO.class);
    }

    public AddressEntity createAddress(OrderRequestDTO requestDTO){
        AddressEntity address =
                addressService.findAddress(requestDTO.getCep());

        address.setComplemento(requestDTO.getComplemento());
        addressRepository.save(address);

        return address;
    }

}
