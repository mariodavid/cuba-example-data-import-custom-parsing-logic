package de.diedavids.cuba.cedicpl.web.customer;

import com.haulmont.cuba.gui.components.ButtonsPanel;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.ListComponent;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.screen.*;
import de.diedavids.cuba.cedicpl.entity.Customer;
import de.diedavids.cuba.dataimport.web.WithImportWizard;

import javax.inject.Inject;

@UiController("cedicpl_Customer.browse")
@UiDescriptor("customer-browse.xml")
@LookupComponent("customersTable")
@LoadDataBeforeShow
public class CustomerBrowse extends StandardLookup<Customer> implements WithImportWizard {

    @Inject
    protected GroupTable<Customer> customersTable;


    @Inject
    protected ButtonsPanel buttonsPanel;
    @Inject
    protected CollectionContainer<Customer> customersDc;

    @Override
    public ListComponent getListComponent() {
        return customersTable;
    }

    @Override
    public CollectionContainer getCollectionContainer() {
        return customersDc;
    }

    @Override
    public ButtonsPanel getButtonsPanel() {
        return buttonsPanel;
    }
}