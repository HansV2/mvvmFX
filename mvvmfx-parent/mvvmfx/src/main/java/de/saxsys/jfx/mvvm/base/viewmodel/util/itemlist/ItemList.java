/*******************************************************************************
 * Copyright 2013 Alexander Casall
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package de.saxsys.jfx.mvvm.base.viewmodel.util.itemlist;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.WeakListChangeListener;
import javafx.scene.control.ListView;

/**
 * Element that you can use in a View Model to transform any list to a string
 * representation which can be bound to UI Elements like {@link ListView}.
 * <b>You should only expose the {@link #stringListProperty()} to the view,
 * otherwise you create a visibility of the view to the model. Create something
 * like this in your View Model:
 * 
 * <code>
 * public ObservableList<String> stringListProperty(){
 * 		return itemList.stringListProperty();
 * }
 * </code>
 * 
 * </b> You have to provide a {@link ModelToStringMapper} to define how to map
 * between the model type and a string and back. In addition you have properties
 * which represents the actual selection state of a list.
 * 
 * @author sialcasa
 * 
 * @param <ListType>
 *            type of the list elements which should be transformed to a string
 *            list
 */
public class ItemList<ListType> {
	// Converter
	private final ModelToStringMapper<ListType> modelToStringMapper;

	// The two lists - List which was provided and the String representation of
	// the list
	private ReadOnlyListWrapper<String> stringList = new ReadOnlyListWrapper<>(
			FXCollections.<String> observableArrayList());
	private ListProperty<ListType> modelList = new SimpleListProperty<>();

	// Reference to the listener to use it by a wrapped listchangelistener
	private ListChangeListener<ListType> listChangeListener;

	/**
	 * Creates a {@link ItemList} by a given list of items and a string
	 * converter.
	 * 
	 * @param modelList
	 *            which should be transformed for the UI
	 * @param modelToStringMapper
	 *            which is used for transformation
	 */
	public ItemList(ObservableList<ListType> modelList,
			final ModelToStringMapper<ListType> modelToStringMapper) {
		this.modelToStringMapper = modelToStringMapper;
		initListEvents();
		this.modelListProperty().set(modelList);
	}

	// If the list changed we want the recreate the string
	private void initListEvents() {
		this.listChangeListener = new ListChangeListener<ListType>() {
			@Override
			public void onChanged(
					javafx.collections.ListChangeListener.Change<? extends ListType> listEvent) {

				// We have to stage delete events, because if we process them
				// separatly, there will be unwanted Changeevents on the
				// stringlist
				List<String> deleteStaging = new ArrayList<>();

				while (listEvent.next()) {
					if (listEvent.wasUpdated()) {
						processReplaceEvent(listEvent);
					} else if (listEvent.wasReplaced()) {
						processReplaceEvent(listEvent);
					} else if (listEvent.wasAdded()) {
						processAddEvent(listEvent);
					} else if (listEvent.wasRemoved()) {
						processRemoveEvent(listEvent, deleteStaging);
					}
				}

				// Process the staged elements
				processStagingLists(deleteStaging);
			}
		};
		modelListProperty().addListener(
				new WeakListChangeListener<>(listChangeListener));

	}

	/**
	 * Maps an add event of the model list to new elements of the
	 * {@link #stringList}.
	 * 
	 * @param listEvent
	 *            to analyze
	 */
	private void processAddEvent(
			javafx.collections.ListChangeListener.Change<? extends ListType> listEvent) {
		for (int i = listEvent.getFrom(); i < listEvent.getTo(); i++) {
			ListType item = listEvent.getList().get(i);
			stringList.add(i, ItemList.this.modelToStringMapper.toString(item));
		}
	}

	/**
	 * Maps an remove event of the model list to new elements of the
	 * {@link #stringList}.
	 * 
	 * @param listEvent
	 *            to process
	 * @param deleteStaging
	 *            for staging the delete events
	 */
	private void processRemoveEvent(
			javafx.collections.ListChangeListener.Change<? extends ListType> listEvent,
			List<String> deleteStaging) {
		for (int i = 0; i < listEvent.getRemovedSize(); i++) {
			deleteStaging.add(stringList.get(listEvent.getFrom() + i));
		}
	}

	/**
	 * Maps an replace event of the model list to new elements of the
	 * {@link #stringList}.
	 * 
	 * @param listEvent
	 *            to process
	 */
	private void processReplaceEvent(
			javafx.collections.ListChangeListener.Change<? extends ListType> listEvent) {
		for (int i = listEvent.getFrom(); i < listEvent.getTo(); i++) {
			ListType item = listEvent.getList().get(i);
			stringList.set(i, ItemList.this.modelToStringMapper.toString(item));
		}
	}

	/**
	 * Process staging events.
	 * 
	 * @param deleteStaging
	 *            to process
	 */
	private void processStagingLists(List<String> deleteStaging) {
		stringList.removeAll(deleteStaging);
		deleteStaging.clear();
	}

	/**
	 * @return List of elements which should be transformed.
	 */
	public ListProperty<ListType> modelListProperty() {
		return modelList;
	}

	/**
	 * @return String representation of {@link #modelListProperty()}.
	 */
	public ReadOnlyListProperty<String> stringListProperty() {
		return stringList.getReadOnlyProperty();
	}

}
