package de.diedavids.cuba.cedicpl.web.customer;

import com.haulmont.cuba.gui.screen.*;
import de.diedavids.cuba.cedicpl.entity.Customer;

@UiController("cedicpl_Customer.edit")
@UiDescriptor("customer-edit.xml")
@EditedEntityContainer("customerDc")
@LoadDataBeforeShow
public class CustomerEdit extends StandardEditor<Customer> {
}