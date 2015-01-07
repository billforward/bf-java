package net.billforward.model;



/**
 * When creating a charge should it be aggregated to the next invoice or
 * automatically generate an invoice
 */
public enum InvoicingType {
	/**
	 * Generate invoice straight away with this charge applied
	 */
	Immediate,
	/**
	 * Add this charge to next invoice.s
	 */
	Aggregated
}