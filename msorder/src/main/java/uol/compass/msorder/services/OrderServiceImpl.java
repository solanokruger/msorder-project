package uol.compass.msorder.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uol.compass.msorder.model.dtos.request.ItemRequestDTO;
import uol.compass.msorder.model.dtos.request.OrderRequestDTO;
import uol.compass.msorder.model.dtos.request.OrderRequestUpdateDTO;
import uol.compass.msorder.model.dtos.response.OrderResponseDTO;
import uol.compass.msorder.model.dtos.response.OrderResponseParameters;
import uol.compass.msorder.model.entities.AddressEntity;
import uol.compass.msorder.model.entities.ItemEntity;
import uol.compass.msorder.model.entities.OrderEntity;
import uol.compass.msorder.model.exceptions.InvalidDateException;
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
        validateDate(request.getItems());
        AddressEntity orderAddress = createAddress(request);

        OrderEntity orderToCreate = new OrderEntity();

        List<ItemEntity> items = mapList(request);

        itemService.create(items);

        orderToCreate.setItems(items);
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

    @Override
    public OrderResponseDTO update(Long id, OrderRequestUpdateDTO request) {
        OrderRequestDTO oldOrder = modelMapper.map(findById(id), OrderRequestDTO.class);
        OrderRequestDTO addressRequest = modelMapper.map(request, OrderRequestDTO.class);
        AddressEntity orderAddress = createAddress(addressRequest);

        OrderEntity orderToUpdate = new OrderEntity();

        List<ItemEntity> items = mapList(oldOrder);

        orderToUpdate.setCpf(request.getCpf());
        orderToUpdate.setAddressEntity(orderAddress);
        orderToUpdate.setItems(items);
        orderToUpdate.setTotal(oldOrder.getTotal());
        orderToUpdate.setId(id);

        orderRepository.save(orderToUpdate);

        return modelMapper.map(orderToUpdate, OrderResponseDTO.class);
    }

    @Override
    public void delete(Long id) {
        findById(id);
        orderRepository.deleteById(id);
    }

    public List<ItemEntity> mapList(OrderRequestDTO request){
        return request
                .getItems()
                .stream()
                .map(itemRequestDTO -> modelMapper.map(itemRequestDTO, ItemEntity.class))
                .collect(Collectors.toList());
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
    
    public double calculateTotal(List<ItemRequestDTO> items){
        return items.stream().mapToDouble(ItemRequestDTO::getValue).sum();
    }

    public void validateDate(List<ItemRequestDTO> itemList){

        for (ItemRequestDTO item: itemList) {
            if(!(item.getCreationDate().isBefore(item.getValidationDate()))){
                throw new InvalidDateException();
            }
        }


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
