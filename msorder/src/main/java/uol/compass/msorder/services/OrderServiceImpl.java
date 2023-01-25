package uol.compass.msorder.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uol.compass.msorder.infra.mqueue.OrderPublisher;
import uol.compass.msorder.model.dtos.request.OrderRequestDTO;
import uol.compass.msorder.model.dtos.request.OrderRequestUpdateDTO;
import uol.compass.msorder.model.dtos.response.OrderResponseDTO;
import uol.compass.msorder.model.dtos.response.OrderResponseParameters;
import uol.compass.msorder.model.entities.AddressEntity;
import uol.compass.msorder.model.entities.ItemEntity;
import uol.compass.msorder.model.entities.OrderEntity;
import uol.compass.msorder.model.entities.OrderPublisherData;
import uol.compass.msorder.model.exceptions.InvalidDateException;
import uol.compass.msorder.model.exceptions.InvalidItemValueException;
import uol.compass.msorder.model.exceptions.NullDateException;
import uol.compass.msorder.model.exceptions.OrderNotFoundException;
import uol.compass.msorder.repositories.AddressRepository;
import uol.compass.msorder.repositories.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderServiceImpl implements OrderService{

    private final OrderPublisher orderPublisher;
    private final OrderRepository orderRepository;
    private final AddressRepository addressRepository;
    private final ItemServiceImpl itemService;
    private final AddressService addressService;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public OrderResponseDTO create(OrderRequestDTO request) throws JsonProcessingException {
        validateItemValue(request.getItems());
        validateNullDate(request.getItems());
        validateDate(request.getItems());
        AddressEntity orderAddress = createAddress(request);

        OrderEntity orderToCreate = new OrderEntity();
        itemService.create(request.getItems());

        orderToCreate.setItems(request.getItems());
        orderToCreate.setCpf(request.getCpf());
        orderToCreate.setTotal(calculateTotal(request.getItems()));
        orderToCreate.setAddressEntity(orderAddress);

        OrderEntity newOrder = orderRepository.save(orderToCreate);
        OrderPublisherData orderData = createOrderPublisherData(newOrder);

        orderPublisher.publishOrder(orderData);
        log.info("Create Service Called");
        return modelMapper.map(newOrder, OrderResponseDTO.class);
    }

    @Override
    public OrderResponseParameters findAll(String cpf, Pageable pageable) {
        Page<OrderEntity> page = cpf == null ?
                orderRepository.findAll(pageable) :
                orderRepository.findAllByCpf(cpf, pageable);
        log.info("FindAll Service Called");
        return createOrderResponseParameters(page);
    }

    @Override
    public OrderResponseDTO findById(Long id) {
        OrderEntity order = orderRepository.findById(id)
                .orElseThrow(OrderNotFoundException::new);
        log.info("FindById Service Called");
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
        log.info("Update Service Called");
        return modelMapper.map(orderToUpdate, OrderResponseDTO.class);
    }

    @Override
    public void delete(Long id) {
        findById(id);
        log.info("Delete Service Called");
        orderRepository.deleteById(id);
    }

    public OrderPublisherData createOrderPublisherData(OrderEntity order){
        var orderData = new OrderPublisherData();
        orderData.setId(order.getId());
        orderData.setTotal(order.getTotal());
        log.info("Create OrderPublisherData Called");
        return orderData;
    }

    public List<ItemEntity> mapList(OrderRequestDTO request){
        log.info("List mapped to ItemEntity List");
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
    
    public double calculateTotal(List<ItemEntity> items){
        log.info("Calculate Order Total Called");
        return items.stream().mapToDouble(ItemEntity::getValue).sum();
    }

    public void validateDate(List<ItemEntity> itemList){
        for (ItemEntity item: itemList) {
            if((item.getCreationDate().isAfter(item.getValidationDate()))){
                log.warn("Validate Date Called - Throwed InvalidDateException");
                throw new InvalidDateException();
            }
        }
    }

    public void validateNullDate(List<ItemEntity> itemList){
        for (ItemEntity item: itemList) {
            if((item.getCreationDate() == null || item.getValidationDate() == null)){
                log.warn("Validate Date Called - Throwed NullDateException");
                throw new NullDateException();
            }
        }
    }

    public void validateItemValue(List<ItemEntity> itemList){
        for (ItemEntity item: itemList){
            if (item.getValue() <= 0){
                log.warn("Validate Date Called - Throwed InvalidItemValueException");
                throw new InvalidItemValueException();
            }
        }
    }

    public OrderResponseDTO createOrderResponse(OrderEntity order) {
        return modelMapper.map(order, OrderResponseDTO.class);
    }

    public AddressEntity createAddress(OrderRequestDTO requestDTO){
        AddressEntity address =
                addressService.findAddress(requestDTO.getCep());

        address.setNumber(requestDTO.getNumber());
        addressRepository.save(address);
        log.info("Create Address Service Called");
        return address;
    }

}
