package net.billforward.model;

import static org.junit.Assert.*;
import net.billforward.exception.BillforwardException;
import net.billforward.model.Product.ProductPeriod;
import net.billforward.model.Product.ProductType;

import org.junit.Test;

public class ProductTests extends TestBase {
	@Test
	public void GetProduct() throws BillforwardException {
		//--Get product by ID
		Product product = Product.getByID("D3E0F064-9E67-492E-8CFC-73E97B0B006A");

		System.out.println(product.toString());
		
		assertEquals("D3E0F064-9E67-492E-8CFC-73E97B0B006A", product.getID());
	}
	
	@Test
	public void GetAllProducts() throws BillforwardException {
		//--Get all Products
		Product[] products = Product.getAll();
		
		assertNotNull(products);
	}
	

	@Test
	public void UpdateProduct() throws BillforwardException {
		//--Get product by ID
		Product product = Product.getByID("D3E0F064-9E67-492E-8CFC-73E97B0B006A");

		System.out.println(product.toString());
			
		product.setDurationPeriod(ProductPeriod.years);
		
		// Update the product
		product = product.save();
		
		System.out.println(product.toString());
		
		assertEquals("D3E0F064-9E67-492E-8CFC-73E97B0B006A", product.getID());
	}
	

	@Test
	public void CreateProduct() throws BillforwardException {
		//--Create 3 Minute Recurring product
		Product product = new Product();
		product.setProductType(ProductType.recurring);
		product.setName("API Product");
		product.setDescription("API Desc");
		product.setDurationPeriod(ProductPeriod.minutes);
		product.setDuration(3);
		
		// create product from model, using API
		product = Product.create(product);

		System.out.println(product.toString());
		
		assertNotNull(product);		
	}
	

	@Test
	public void DeleteProduct() throws BillforwardException {
		//--Get product
		Product product = Product.getByID("D3E0F064-9E67-492E-8CFC-73E97B0B006A");
		
		// Retire product from model, using API
		product = product.retire();

		System.out.println(product.toString());
		
		assertNotNull(product);		
	}
}
