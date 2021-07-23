package br.com.moraes.shoppingapi.api.converter;

import java.util.stream.Collectors;

import br.com.moraes.dto.ItemDto;
import br.com.moraes.dto.ShopDto;
import br.com.moraes.shoppingapi.api.data.model.Item;
import br.com.moraes.shoppingapi.api.data.model.Shop;

public class DtoConverter {
	public static ItemDto convert(Item item) {
		ItemDto itemDTO = new ItemDto();
		itemDTO.setProductIdentifier(item.getProductIdentifier());
		itemDTO.setPrice(item.getPrice());
		return itemDTO;
	}

	public static ShopDto convert(Shop shop) {
		ShopDto shopDTO = new ShopDto();
		shopDTO.setUserIdentifier(shop.getUserIdentifier());
		shopDTO.setTotal(shop.getTotal());
		shopDTO.setDate(shop.getDate());
		shopDTO.setItems(shop.getItems().stream().map(DtoConverter::convert).collect(Collectors.toList()));
		return shopDTO;
	}
}
