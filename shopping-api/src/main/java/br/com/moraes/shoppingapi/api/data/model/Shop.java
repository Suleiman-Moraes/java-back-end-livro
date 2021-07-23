package br.com.moraes.shoppingapi.api.data.model;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import br.com.moraes.dto.ShopDto;
import br.com.moraes.interfaces.IModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "shop")
public class Shop implements IModel{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String userIdentifier;

	private Float total;

	private Date date;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "item", joinColumns = @JoinColumn(name = "shop_id"))
	private List<Item> items;

	public static Shop convert(ShopDto shopDTO) {
		Shop shop = new Shop();
		shop.setId(shopDTO.getId());
		shop.setUserIdentifier(shopDTO.getUserIdentifier());
		shop.setTotal(shopDTO.getTotal());
		shop.setDate(shopDTO.getDate());
		shop.setItems(shopDTO.getItems().stream().map(Item::convert).collect(Collectors.toList()));
		return shop;
	}
}
