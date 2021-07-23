package br.com.moraes.shoppingapi.api.data.model;

import javax.persistence.Embeddable;

import br.com.moraes.dto.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Item {

	private String productIdentifier;

	private Float price;

	public static Item convert(ItemDto itemDTO) {
		Item item = new Item();
		item.setProductIdentifier(itemDTO.getProductIdentifier());
		item.setPrice(itemDTO.getPrice());
		return item;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (productIdentifier == null) {
			if (other.productIdentifier != null)
				return false;
		} else if (!productIdentifier.equals(other.productIdentifier))
			return false;
		return true;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productIdentifier == null) ? 0 : productIdentifier.hashCode());
		return result;
	}
}
